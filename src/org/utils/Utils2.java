package org.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiong
 * @date 2019-05-07  10:48
 */
public class Utils2 {

    private final static String num_zh[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    private static String numToZh(String num) {
        if (isBigNum(num))
            return bignumToZh(num);
        String dst = "";
        int count = 0;
        int src = Integer.parseInt(num);
        while(src > 0) {
            dst = (num_zh[src % 10])+ dst;
            src = src / 10;
            count++;
        }
        return dst;
    }

    private static String bignumToZh(String num){
        String dst;
        String dst2;
        String num1 = num.substring(0,num.length()/2);
        String num2 = num.substring(num.length()/2,num.length());
        if (isBigNum(num1)){
            dst=bignumToZh(num1);
        }
        if(isBigNum(num2))
            dst2=bignumToZh(num2);
        dst = numToZh(num1);
        dst2 = numToZh(num2);
        return dst+dst2;
    }

    private static boolean isBigNum(String num){
        if (num.length() >=10)
            return true;
        return false;
    }

    public static String toZh(String str){
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(str);
        String tempNum;
        while (matcher.find()){
            tempNum = numToZh(matcher.group());
            if (matcher.end()+1 <= str.length() && str.substring(matcher.end(),matcher.end()+1).equals(".")) {

                str = str.substring(0, matcher.start()) + tempNum + "点" + str.substring(matcher.end() + 1, str.length());
            }
            if (matcher.end() > str.length()) {
                return null;
            }
            else
                str = str.substring(0, matcher.start()) + tempNum + str.substring(matcher.end(), str.length());
        }
        return str;
    }

    public static boolean isZhOrNum(String str){
        Pattern p = Pattern.compile("[^\\u4e00-\\u9fa5\\d\\pP\\pZ\\pS]");
        return p.matcher(str).find();
    }

    public static String numToPrec(String str){
        if (str.equals("") || str.equals(" "))
            return null;
        String temp = str.substring(0, str.length()-2)+" 百分之"+str.substring(str.length()-2,str.length());
        return  temp;
    }

    public static void main(String[] args) {
        Pattern p = Pattern.compile("[ァ-ヶー]*");
        String str = "爱的112234241341341  1241351625.622451345161 .。,?";
        String str1 = "고슴도치의123";
        String str2 = "打发34";
        System.out.println(isZhOrNum(str));
        str = toZh(str);
        System.out.println(str);
        str2 = numToPrec(str2);
        System.out.println(str2);
    }

}
