package com.class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 贪心算法
 * 给定一个字符串组成的数组strs，
 * 拼接起来，要求拼接结果的字典序要是最小的
 */
public class MinDictionaryOrder {
    private static String getDo(String[] strs){
        if (strs.length==0||strs==null)return "";
        return process(strs).first();
    }
    //1、所有字符串全排列
    private static TreeSet<String> process(String[] strs){
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length==0){
            ans.add("");
            return ans;
        }
        for (int i = 0; i <strs.length ; i++) {
            String first=strs[i];
            String[] nexts=removeIndexString(strs,i);
            TreeSet<String> next = process(nexts);
            for (String cur:next) {
                ans.add(first+cur);
            }
        }
        return ans;
    }

    private static String[] removeIndexString(String[] strs, int i) {
        int N = strs.length;
        String[] next = new String[N-1];
        int index = 0;
        for (int j = 0; j <N ; j++) {
            if (i!=j){
                next[index++]=strs[j];
            }
        }
        return next;
    }
    //贪心算法，排序，比较
    public static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }
    private static String process1(String[] strs){
        if (strs.length==0||strs==null)return "";
        //根据自定义的比较器排序
        Arrays.sort(strs,new MyComparator());
        String res="";
        for (int i = 0; i <strs.length ; i++) {
            res+=strs[i];
        }
        return res;
    }
    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!process1(arr1).equals(getDo(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
