package com.ldy.util;


import org.apache.commons.lang.StringUtils;

/**
 * @className:Levenshtein.java
 * @classDescription:Levenshtein Distance 算法实现
 * 可以使用的地方：DNA分析 　　拼字检查 　　语音辨识 　　抄袭侦测
 * @author:donghai.wan
 * @createTime:2012-1-12
 */
public class Levenshtein {

    public static void main(String[] args) {
        //要比较的两个字符串
        String str1 = "Hello&@ it is+ me";
        String str2 = "hello it, is? +me'";

        str1 = str1.replaceAll("[^a-zA-Z0-9\\ \\_\\u4e00-\\u9fa5]", "").trim().toLowerCase();
        str2 = str2.replaceAll("[^a-zA-Z0-9\\ \\_\\u4e00-\\u9fa5]", "").trim().toLowerCase();
        System.out.println(str1.trim()+"  "+str2.trim());
        levenshteinStr(str1,str2);
        //计算差异步骤
        System.out.println(1-(float)StringUtils.getLevenshteinDistance(str1,str2)/(Math.max(str1.length(),str2.length())));

    }

    /**
     * 　　DNA分析 　　拼字检查 　　语音辨识 　　抄袭侦测
     *
     * @createTime 2012-1-12
     */
    public static void levenshtein(String str1,String str2) {
        //计算两个字符串的长度。
        int len1 = str1.length();
        int len2 = str2.length();
        //建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        //赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        //计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
        System.out.println("字符串\""+str1+"\"与\""+str2+"\"的比较");
        //取数组右下角的值，同样不同位置代表不同字符串的比较
        System.out.println("差异步骤："+dif[len1][len2]);
        //计算相似度
        float similarity =1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
        System.out.println("相似度："+similarity);
    }

    //得到最小值
    private static int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

    /**
     * 　　针对Englsh DNA分析 　　拼字检查 　　语音辨识 　　抄袭侦测
     *
     * @createTime
     */
    public static void levenshteinStr(String str1,String str2) {
        //将特殊符号转换
        str1 = str1.replaceAll("[^a-zA-Z0-9\\ \\_\\u4e00-\\u9fa5]", "").trim().toLowerCase();
        str2 = str2.replaceAll("[^a-zA-Z0-9\\ \\_\\u4e00-\\u9fa5]", "").trim().toLowerCase();
        //计算两个字符串的长度。
        String[] strArr1=str1.split(" ");
        String[] strArr2=str2.split(" ");
        int len1 = strArr1.length;
        int len2 = strArr2.length;
        //建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        //赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        //计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (strArr1[i - 1].equals(strArr2[j - 1])) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
        System.out.println("字符串\""+str1+"\"与\""+str2+"\"的比较");
        //取数组右下角的值，同样不同位置代表不同字符串的比较
        System.out.println("差异步骤："+dif[len1][len2]);
        System.out.println("字符长度:"+Math.max(len1,len2));
        //计算相似度
        float similarity =1 - (float) dif[len1][len2] / Math.max(len1,len2);
        System.out.println("相似度："+similarity);
    }

}