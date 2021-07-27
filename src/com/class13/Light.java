package com.class13;

import java.util.HashSet;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Light {
    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }
    /**
     * str[index....]位置，自由选择放灯还是不放灯
     * str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
     * 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
     * @param str
     * @param index
     * @param lights
     * @return
     */
    public static int process(char[] str, int index, HashSet<Integer> lights){
        //结束时
        if (index==str.length){
            for (int i = 0; i <str.length ; i++) {
                if (str[i]=='.'){
                    if (!lights.contains(i-1)&&!lights.contains(i)&&!lights.contains(i+1))return
                            Integer.MAX_VALUE;
                }
            }
            return lights.size();
        }else {
            //还未结束时
            int no= process(str,index+1,lights);
            int yes = Integer.MAX_VALUE;
            if (str[index]=='.'){
                lights.add(index);
                yes=process(str,index+1,lights);
                lights.remove(index);
            }
            return Math.min(yes,no);
        }
    }
    /**
     * 判断i位置是X还是.，如果是X则不管，如果是.再判断i+1是X还是.。
     * @param str
     * @return
     */
    private static int process(String str){
        if (str==null)return 0;
        char[] strings = str.toCharArray();
        int light=0,i=0;
        while (i<strings.length){
            //当i为X时，不放灯，到i+1去做决定
            if (strings[i]=='X'){
                i++;
            }else {
//                如果i为.时则放灯
                light++;
                if (i+1==str.length())break;
//                当i+1为X时，在i位置放灯，去i+2做决定
                else  {
                    if (strings[i+1]=='X'){i=i+2;}
//                当i+1为.时，在i+1处放灯，不管i+2是什么，直接去i+3做决定
                    else if (strings[i+1]=='.')i=i+3;
                }
            }
        }
        return light;
    }
    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = process(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
