package org.threads.file;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.apache.log4j.Logger;
import org.utils.Utils2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;


public class ReadFileThread extends Thread{

    private static Logger logger = Logger.getLogger(ReadFileThread.class);

    private final static int _1MB= 1024 * 1024;
    private final static long SIZE = _1MB * 1;

    private String filename;
    private TempData data;
    private BlockingQueue rlist;
    private LinkedBlockingDeque wlist;
    private AtomicInteger num;


    public ReadFileThread(TempData data, BlockingQueue rlist, LinkedBlockingDeque wlist, AtomicInteger num){
        this.filename = data.getFilename();
        this.data = data;
        this.rlist = rlist;
        this.wlist = wlist;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            long position = data.getPosition();
            FileInputStream fip = new FileInputStream(filename);
            InputStreamReader reader = null;
            reader = new InputStreamReader(fip, "UTF-8");
            reader.skip(position);
            BufferedReader br = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            String temp;
            long size = 0;
            while (sb.length() <= SIZE && (null != (temp = br.readLine()))){   //设置一个读取的最大值
                if (temp.length() != 0 && !Utils2.isZhOrNum(temp)) {
                    if(Utils2.toZh(temp) != null){
                        temp = Utils2.toZh(temp);
                        sb.append(temp + "\n");
                    }else
                        size += temp.length()+1;
                }else if(temp.length() != 0){
                    size += temp.length()+1;
                }
            }
            TempData rtd;
            if(sb.length() > 0) {
                position += sb.length() + size;
                rtd = new TempData(filename, null, position );
                rlist.put(rtd);
                logger.info(" "+filename+" position: "+position);
            }
            br.close();
            reader.close();

            List<Term> termList = HanLP.segment(sb.toString());
            List<String> strList = new ArrayList<>();

            String str="";
            for(int i=0; i < termList.size(); i++){
                temp = termList.get(i).toString().replaceAll("\\/([a-z]|[A-Z])+|\\d|[\\pP\\pS\\pZ]","");
                if(!temp.equals("\n")){
                    if(temp.equals("%")) {
                        str = Utils2.numToPrec(str);
                    }else if (!temp.equals(" "))
                        str += temp+" ";
                }else{
                    str += temp;
                    strList.add(str.replaceAll("( +)"," "));
                    str="";
                }
            }
            TempData tempdata;
            for(;;){
                tempdata = (TempData)wlist.peekLast();
                if(tempdata.getFilename() == null)
                    continue;
                wlist.remove(tempdata);
                tempdata.setData(strList);
                wlist.putFirst(tempdata);
                break;
            }
            num.getAndDecrement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
