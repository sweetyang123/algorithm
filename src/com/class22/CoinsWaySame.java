package com.class22;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class CoinsWaySame {
    public static int coinsWaySame(int[] arr,int aim){
        if (arr==null||arr.length==0)return 0;
        return process(arr,0,aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (index==arr.length){
            //遍历完整个数组看是否凑齐了目标
            return rest==0?1:0;
        }
        int way=0;
        //数组中的每个值可以用多遍
        for (int zhang = 0; zhang*arr[index] <=rest ; zhang++) {
           way+= process(arr,index+1,rest-zhang*arr[index]);
        }
        return way;
    }
    private static int dpWaySame(int[] arr, int aim) {
        int N = arr.length;
        if (arr==null|| N ==0)return 0;
        if (aim==0)return 1;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0]=1;
        for (int index = N-1; index >=0 ; index--) {
             for (int rest = 0; rest<=aim ; rest++) {
                 int way=0;
                for (int zhang = 0; zhang*arr[index] <=rest ; zhang++) {
                    way+= dp[index+1][rest-zhang*arr[index]];
                }
                dp[index][rest]=way;
            }
                }

        return dp[0][aim];
    }
    public static void main(String[] args) {
        int[] arr = new int[]{2,2,4};
        System.out.println(coinsWaySame(arr,4));
        System.out.println(dpWaySame(arr,4));
    }


}
