package org.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author xiong
 * @date 2019-05-17  15:54
 */
public class SplitFile extends Thread{

    private static Logger logger = Logger.getLogger(SplitFile.class);
    private static CountDownLatch cdl;

    String filepath;

    public SplitFile(String filepath, CountDownLatch cdl){
        this.filepath = filepath;
        this.cdl = cdl;
    }

    public SplitFile(String filepath){
        this.filepath = filepath;
    }

    @Override
    public void run() {
        try {
            File file = new File(filepath);
            String temp = filepath.replaceAll("(\\..*)", "_temp");
            File tempDir = new File(temp);
            tempDir.mkdir();
            long lines = spilt(file);
            logger.info(filepath + " total lines: " + lines);
            String command = "split -l " + lines / 18 + " " + filepath + " " + temp +"/"+ filepath.replaceAll("(/.*/)|(\\.txt)","");
            logger.info(filepath + " start splitFile: " + command);
            Process ps = Runtime.getRuntime().exec(command);
            ps.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            cdl.countDown();
            logger.info("count down");
        }
    }

    private int spilt(File file) {
        logger.info("start total lines");
        int lines = 0;
        LineNumberReader reader = null;
        try (FileReader in = new FileReader(file)) {
            reader = new LineNumberReader(in);
            reader.skip(file.length());
            lines = reader.getLineNumber();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }
}
