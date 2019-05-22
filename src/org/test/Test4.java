package org.test;

/**
 * @author xiong
 * @date 2019-05-20  13:39
 */
public class Test4 {

    public static void main(String[] args) throws InterruptedException {
        String str = "/opt/test/NLPCC_TRAIN_DATA（赵磊）/news_title.txt";
        Thread thread = new SplitFile2(str);
        thread.start();
        thread.join();
    }
}
