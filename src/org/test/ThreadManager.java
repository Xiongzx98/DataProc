package org.test;

import java.io.File;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;


public class ThreadManager extends Thread{


//    private long skip;                            //读文件skip的大小
//    private long seek;                            //写文件seek的大小
//    private String filename;                      //文件的名字
//    private String path;                          //落盘文件的名字
//    private ExecutorService pool;                 //总的线程池
//    private FilePosition position;                //记录文件读和写的位置，以及是否结束
//    private ArrayBlockingQueue<List> list;        //落盘的数据
//    private AtomicInteger num;
//
//
//
//    public ThreadManager(ExecutorService pool, String filename){
//        this.pool = pool;
//        this.filename =filename;
//        this.num = new AtomicInteger();
//        this.path = "/opt/Hanlp/data2/"+filename.replaceAll("(\\.[a-z]*)|(\\/.*\\/)","")+"_"+num.getAndIncrement()+".txt";
//        this.position = new FilePosition();
//        this.list = new ArrayBlockingQueue<List>(10);
//        System.out.println("---------begin read"+filename+"----------");
//    }
//
//    @Override
//    public void run() {
//        try {
//            System.out.println("***********" + filename + " begin read positon: " + skip + "*************");
//            ReadFileThread read = new ReadFileThread(filename, position, list);
//            read.run();
//            read.join();
//            WriteFileThread write = new WriteFileThread(list.take(), path, position);
//            write.run();
//            write.join();
//            while (this.position.getReadOver() == 1 || !list.isEmpty()){
//                if (position.getFileSkip() != skip && list.size() != 5) {
//                    skip = position.getFileSkip();
//                    System.out.println("***********" + filename + " begin read positon: " + skip + "*************");
//                    pool.execute(new ReadFileThread(filename, position, list));
//                }
//                if (!list.isEmpty() ){
//                    if( position.getFileSeek() != seek) {
//                        File temp = new File(path);
//                        if (temp.length() >= 1024 * 1024 * 1024 * 1) {
//                            this.path = "/opt/Hanlp/data2/" + filename.replaceAll("(\\.[a-z]*)|(\\/.*\\/)", "") + "_" + num.getAndIncrement() + ".txt";
//                            position.setFileSeek();
//                        }
//                        seek = position.getFileSeek();
//                        pool.execute(new WriteFileThread(list.take(), path, position));
//                    }
//                }
//            }
//            System.out.println("=============== "+filename+" ======== end ==============================");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
