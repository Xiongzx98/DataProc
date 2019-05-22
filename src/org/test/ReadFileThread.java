package org.test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.utils.Utils;
import org.utils.Utils2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;


public class ReadFileThread extends Thread{

//    private final static int _1MB= 1024 * 1024;
//    private final static long SIZE = _1MB * 1;
//
//    private String pathname;
//    private FilePosition position;
//    private ArrayBlockingQueue list;
//
//
//    public ReadFileThread(String pathname, FilePosition position, ArrayBlockingQueue list){
//        this.pathname = pathname;
//        this.position = position;
//        this.list = list;
//    }
//
//    @Override
//    public void run() {
//        try {
//            FileInputStream fip = new FileInputStream(pathname);
//            InputStreamReader reader = null;
//            reader = new InputStreamReader(fip, "UTF-8");
//            reader.skip(position.getFileSkip());
//            BufferedReader br = new BufferedReader(reader);
//            StringBuffer sb = new StringBuffer();
//            String temp;
//            long size =0;
//            while (sb.length() <= SIZE && (null != (temp = br.readLine()))){   //设置一个读取的最大值
//                if (temp.length() != 0 && !Utils2.isZhOrNum(temp)) {
//                    if(Utils2.toZh(temp) != null){
//                        temp = Utils2.toZh(temp);
//                        sb.append(temp + "\n");
//                    }else
//                        size += temp.length()+1;
//                }else if(temp.length() != 0){
//                    size += temp.length()+1;
//                }
//            }
//            br.close();
//            reader.close();
//
//            List<Term> termList = HanLP.segment(sb.toString());
//            List<String> strList = new ArrayList<>();
//
//            String str="";
//            for(int i=0; i < termList.size(); i++){
//                temp = termList.get(i).toString().replaceAll("\\/([a-z]|[A-Z])+|\\d|[\\pP\\pS\\pZ]","");
//                if(!temp.equals("\n")){
//                    if(temp.equals("%")) {
//                        str = Utils2.numToPrec(str);
//                    }else if (!temp.equals(" "))
//                        str += temp+" ";
//                }else{
//                    str += temp;
//                    strList.add(str.replaceAll(" +"," "));
//                    str="";
//                }
//            }
////            System.out.println(strList.toString());
////            System.out.println("------------------- sb.length: "+ sb.length()+"   "+"size: "+size);
//            position.addFileSkip(sb.length()+size);
//            list.put(strList);
//            if(sb.length() == 0) {
//                System.out.println("******* "+pathname+" ******over****************");
//                this.position.setReadOver(0);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
