package org.utils;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.regex.Pattern;

public class Utils {

    private final static String num_zh[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private final static String unit[] = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};

    public static String isWhat(String num){
        if(isNum(num))
            return numToZh(num);
        if(isMon(num))
            return monToZh(num);
        if (isDou(num))
            return douToZh(num);
//        if(isPrice(num))
//            return prToZh(num);
        return num;
    }

    private static boolean isPrice(String num){
        Boolean isPrice = num.matches("([0-9]{1,4})万([0-9])|([0-9]*)\\.([0-9]*)万|([0-9]{1,4})万|([0-9]*)\\.([0-9]*)万([0-9])");
        if (isPrice)
            return true;
        return false;
    }

    private static boolean isDou(String num){
        Boolean isDou = num.matches("([0-9]*)\\.([0-9]*)");
        if (isDou)
            return true;
        return false;
    }

    private static boolean isNum(String num){
        Boolean isNum = num.matches("([0-9]*)");
        if(isNum)
            return true;
        return false;
    }
    private static boolean isBigNum(String num){
        if (num.length() >=10)
            return true;
        return false;
    }
    private static boolean isMon(String num){
        Boolean isMon = num.matches("([0-9]{1,2})月");
        if (isMon)
            return true;
        return false;
    }

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
        return dst.replaceAll("零[千百十]", "零").replaceAll("零+万", "万")
                .replaceAll("零+亿", "亿").replaceAll("亿万", "亿零")
                .replaceAll("零+", "零").replaceAll("零$", "");
    }

    public static String numToPrec(String str){
        if (str.equals("") || str.equals(" "))
            return null;
        String temp = str.substring(0, str.length()-2)+" 百分之"+str.substring(str.length()-2,str.length());
        return  temp;
    }

    private static String bignumToZh(String num){
        String dst = "";
        String dst2 = "";
        String num1 = num.substring(0,num.length()/2);
        String num2 = num.substring(num.length()/2,num.length());
        if (isBigNum(num1) || isBigNum(num2)){
            dst=bignumToZh(num1);
            dst2=bignumToZh(num2);
            return dst+dst2;
        }
        int src1 = Integer.valueOf(num1);
        int src2 = Integer.valueOf(num2);
        int count=0;
        while (src1 > 0){
            if(src2<100000 && count != 1) {
                dst = num_zh[0];
                count++;
                continue;
            }else if(count != 1){
                dst2 = num_zh[src2 % 10] + dst2;
                src2 /= 10;
                count++;
            }
            dst = num_zh[src1 % 10] + dst;
            dst2 = num_zh[src2 % 10] + dst2;
            src2 /= 10;
            src1 /= 10;
        }
        return dst+dst2;
    }
    private static String monToZh(String num){
        num = num.substring(0,num.length()-1);
        if (isNum(num))
            return numToZh(num)+"月";
        return num;
    }
    private static String douToZh(String num){
        String[] nums = num.split("\\.");
        String dst="";
        String dst1="";
        if (nums.length != 0 && isNum(nums[0]))
            dst=numToZh(nums[0]);
        if (nums.length ==2 && isNum(nums[1])) {
            dst1 = numToZh(nums[1]);
        }
        return dst+"点"+dst1;
    }

//    private static String prToZh(String num){
//        String[] nums = num.split("万");
//        String str = "";
//        String str2 = "";
//        if(nums.length != 0){
//            if(isDou(nums[0])){
//                str = douToZh(nums[0]);
//            }else
//                str = numToZh(nums[0]);
//            if(nums.length == 2)
//                str2 = numToZh(nums[1]);
//        }
//        return str+"万"+str2;
//    }

//    public static boolean isEn(String str){
//        Pattern p = Pattern.compile("([a-zA-Z]{1,})");
//        return p.matcher(str).find();
//    }
//
//    public static boolean isKrn(String str){
//        Pattern p = Pattern.compile("([\\uAC00-\\uD7A3]*)");
//        return p.matcher(str).find();
//
//    }
//    public static boolean isJap(String str){
//        Pattern p = Pattern.compile("[ァ-ヶー]*");
//        return p.matcher(str).find();
//    }

    public static boolean isZhOrNum(String str){
        Pattern p = Pattern.compile("[^\\u4e00-\\u9fa5\\d\\pP\\pZ\\pS]");
        return p.matcher(str).find();
    }

    public static void main(String[] args) {
        Pattern p = Pattern.compile("[ァ-ヶー]*");
        String str = "爱的112 12 .。,?";
        String str1 = "고슴도치의123";
        System.out.println(isZhOrNum(str));
    }

}
