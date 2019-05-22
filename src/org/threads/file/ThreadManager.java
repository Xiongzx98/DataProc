package org.threads.file;

import org.apache.log4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class ThreadManager extends Thread{
    private static Logger logger = Logger.getLogger(ThreadManager.class);

    private String filename;                      //文件的名字
    private String path;                          //落盘文件的名字
    private ExecutorService pool;                 //总的线程池
    private CountDownLatch lock;
    private LinkedBlockingDeque<TempData> wlist;             //写队列
    private BlockingQueue<TempData> rlist;                             //读队列
    private static AtomicInteger rNum = null;
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public ThreadManager(ExecutorService pool, String filename, String path, CountDownLatch lock){
        this.pool = pool;
        this.path = path;
        this.lock = lock;
        this.filename =filename;
        this.rNum = new AtomicInteger();
        this.wlist = new LinkedBlockingDeque();
        this.rlist = new LinkedBlockingQueue();

    }

    @Override
    public void run() {
        try {
            String pathName = path +"/"+filename.replaceAll("(/.*/)|(\\..*)","");
            TempData rObj = new TempData(filename, null, 0);
            TempData wObj = new TempData(pathName, null, 0);
            TempData temp=null;
            rlist.put(rObj);
            wlist.putFirst(new TempData(null,null,0));
            wlist.putLast(wObj);
            do{
                if(rlist.peek() != null && rNum.get() < 6) {
                    pool.execute(new ReadFileThread(rlist.take(), rlist, wlist, rNum));
                    rNum.getAndIncrement();
                }
                reentrantLock.lock();
                if((temp = wlist.peekFirst())!= null && temp.getData() != null && temp.getFilename() != null){
                    wlist.remove(temp);
                    pool.execute(new WriteFileThread(temp, wlist));
                }
                reentrantLock.unlock();
            }while (rNum.get() > 0 || (wlist.peekFirst() != null && wlist.peekFirst().getFilename() != null));
            logger.info(" "+filename+" is over");
            lock.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
