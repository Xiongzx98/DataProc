package org.test;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author xiong
 * @date 2019-05-20  13:31
 */
public class SplitFile2 extends Thread{

    private static Logger logger = Logger.getLogger(SplitFile2.class);
    private static CountDownLatch cdl;

    String filepath;

    public SplitFile2(String filepath, CountDownLatch cdl){
        this.filepath = filepath;
        this.cdl = cdl;
    }

    public SplitFile2(String filepath){
        this.filepath = filepath;
    }

    @Override
    public void run() {
        try {
            String fromPath = filepath;
            String temp = filepath.replaceAll("(\\.txt)", "_temp");
            String toPath = temp+"/data1.txt";
            String toPath2 = temp + "/data2.txt";
            File tempDir = new File(temp);
            tempDir.mkdir();



//            RandomAccessFile fromFile = new RandomAccessFile(fromPath, "rw");
//            FileChannel fromChannel = fromFile.getChannel();
//            RandomAccessFile toFile = new RandomAccessFile(toPath, "rw");
//            RandomAccessFile toFile2 = new RandomAccessFile(toPath2, "rw");
//            FileChannel toChannel = toFile.getChannel();
//            FileChannel toChannel2 = toFile2.getChannel();
//            long position = 0;
//            long count = fromChannel.size() >> 1;
//            long end = fromChannel.size();
//            System.out.println("position: "+position+"  count: "+ count);
//            System.out.println("count: "+count+"  end: "+ end);
//            toChannel.transferFrom(fromChannel, position, count);
//            toChannel2.transferFrom(fromChannel,count, end);
//            toChannel.transferFrom(fromChannel, position, count);
//            fromChannel.transferTo(count, end, toChannel2);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //    @Override
//    public void run() {
//        try {
//            File file = new File(filepath);
//            String temp = filepath.replaceAll("(\\.txt)", "_temp");
//            File tempDir = new File(temp);
//            tempDir.mkdir();
//            long lines = spilt(file);
//            logger.info(filepath + "total lines: " + lines);
//            String command = "split -l " + lines / 18 + " " + filepath + " " + temp + "/data";
//            logger.info(filepath + " start splitFile: " + command);
//            Process ps = Runtime.getRuntime().exec(command);
//            ps.waitFor();
//            cdl.countDown();
//            logger.info("count down");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private int spilt(File file){
//        int lines=0;
//        try (FileReader in = new FileReader(file)){
//            LineNumberReader reader = new LineNumberReader(in);
//            reader.skip(file.length());
//            lines = reader.getLineNumber();
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return lines;
//    }

}
