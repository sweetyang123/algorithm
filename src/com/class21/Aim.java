package com.class21;

import java.util.ArrayList;
import java.util.List;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class Aim {
    public static void aim(int[] arr,int aim){
        if (arr==null||arr.length==0)return ;
        List<Integer> list = new ArrayList();
         process(arr,0,aim,list);
        for (Integer list1:list) {
            System.out.println(list1);
        }
    }

    private static int process(int[] arr, int index, int rest, List ans) {
        if (index==arr.length){
            //遍历完整个数组看是否凑齐了目标
           if (rest==0){
               ans.add(arr[index-1]);
               return 1;
           }
            return 0;
        }
        int way=0;
        //数组中的每个值可以用多遍
        for (int zhang = 0; zhang*arr[index] <=rest ; zhang++) {
            way=Math.min(process(arr,index+1,rest-zhang*arr[index],ans),process(arr,index+1,rest,ans));
        }
        return way;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{2,2,4};
        aim(arr,4);
    }
}


