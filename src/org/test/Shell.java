package org.test;


/**
 * @author xiong
 * @date 2019-05-17  14:49
 */
public class Shell {

    public static void main(String[] args) throws Exception {
//        String shpath = "/opt/Hanlp/wc.sh";
        String command = "split -l 10000000 /opt/test/fs_call_replay.txt /opt/test/temp/call";
        Process ps = Runtime.getRuntime().exec(command);
        ps.waitFor();

    }

}
