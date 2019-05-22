package org.test;

import org.threads.ThreadPoolManager;

import java.io.File;
import java.util.ArrayList;

/**
 * @author xiong
 * @date 20190521  10:14
 */
public class Test5 {
    private static final int _1GB = 1024 * 1024 * 1024 * 1;
    private static ArrayList<String> files = new ArrayList();

    public static void main(String[] args) throws InterruptedException {

        String filename = "/opt/test";
        String path = filename+"/data";
        totalFile(filename);
        ThreadPoolManager manager = new ThreadPoolManager(path);
        String[] ss = {"/opt/test/asr（何鑫宇）/1000_re.txt"};
        manager.start2(ss);
//        for (String s:files)
//            System.out.println(s);
    }
    private static void totalFile(String temp) {
        try {
            File pfile = new File(temp);
            String[] s = pfile.list();
            for (int n = 0; n < s.length; n++) {
                String pathname = temp + "/" + s[n];          //文件的绝对地址
                File file = new File(pathname);
                if(file.isFile() && file.length() < _1GB) {
                   files.add(pathname);
                }
                if(file.isDirectory())
                    totalFile(pathname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
