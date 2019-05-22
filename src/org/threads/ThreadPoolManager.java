package org.threads;

import org.threads.file.ThreadManager;
import org.utils.SplitFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {

    private String path;                                                //落盘文件夹
    private List files;
    private List bigFile;
    private String filename;                                            //读取文件
    private ExecutorService executor;
    private static final int _1GB = 1024 * 1024 * 1024 * 1;
    private static final int THREAD_NUM = Runtime.getRuntime().availableProcessors() * 2 + 1;   //线程数= CPU核数 * 2 + 1
    private static CountDownLatch cdl = null;

    public ThreadPoolManager(String filename, String path){
        this.filename = filename;
        this.path = path;
        this.executor = Executors.newFixedThreadPool(THREAD_NUM);
        this.files = new ArrayList<String>();
        this.bigFile = new ArrayList<String>();
    }
    public ThreadPoolManager(String path){
        this.path=path;
        this.executor = Executors.newFixedThreadPool(THREAD_NUM);
    }

    private void totalFile(String temp) {
        try {
            File pfile = new File(temp);
            String[] s = pfile.list();
            for (int n = 0; n < s.length; n++) {
                String pathname = temp + "/" + s[n];          //文件的绝对地址
                File file = new File(pathname);
                if(file.isFile()) {
                    if (file.length() >= _1GB && !bigFile.contains(pathname)) {
                        bigFile.add(pathname);
                    }
                    else
                        files.add(pathname);
                }
                if(file.isDirectory())
                    totalFile(pathname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void split() throws InterruptedException {
        int size = bigFile.size();
        if (size != 0){
            for(Object str : bigFile){
                cdl = new CountDownLatch(1);
                executor.execute(new SplitFile(str.toString(), cdl));
                cdl.await();
            }
        }
        else
            cdl = null;
    }

    private void isExists(){
        File file = new File(path);
        if (!file.exists())
            file.mkdir();
    }

    public void start() {
        try {
            isExists();
            totalFile(filename);
            split();
            cdl.await();
            for (Object str : bigFile)
                totalFile(str.toString().replaceAll("(\\..*)", "_temp"));
            for (Object str : files){
                cdl = new CountDownLatch(1);
                executor.execute(new ThreadManager(executor, str.toString(), path, cdl));
                cdl.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    //测试用的
    public void start2(Object[] ss) throws InterruptedException {
        for(Object s:ss){
            cdl = new CountDownLatch(1);
            executor.execute(new ThreadManager(executor, s.toString(), path, cdl));
            cdl.await();
        }
        executor.shutdown();
    }

}
