package org.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * @author xiong
 * @date 2019-05-17  15:17
 */
public class FileLine {


    public static int getTotalLines(File file) throws IOException {
        long startTime = System.currentTimeMillis();
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        reader.skip(file.length());
        int lines = reader.getLineNumber();
        reader.close();
        long endTime = System.currentTimeMillis();

        System.out.println("统计文件行数运行时间： " + (endTime - startTime) + "ms");
        return lines;
    }

    public static void main(String[] args) throws IOException {
        String filepath = "/opt/test/fs_call_replay.txt";
        File file = new File(filepath);
        System.out.println(getTotalLines(file));
    }

}
