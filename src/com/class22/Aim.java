package com.class22;

import java.util.ArrayList;
import java.util.List;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class Aim {
    public static int aim(int[] arr,int aim){
        return process(arr,0,aim);
    }

    private static int process(int[] arr, int index, int rest) {
        //循环完数组后，如果凑齐了目标则返回0，否则怎么也搞不定
        if (index==arr.length){
            return rest==0?0:Integer.MAX_VALUE;
        }{
            int way=Integer.MAX_VALUE;
            //数组中的每个值可以用多遍
            for (int zhang = 0; zhang*arr[index] <=rest ; zhang++) {
                int next = process(arr, index + 1, rest - zhang * arr[index]);
                //能搞定则返回0，则将之前的张数加上
                if (next!=Integer.MAX_VALUE){
                    way=Math.min(way,next+zhang);
                }
            }
            return way;
        }
    }
    private static int dp(int[] arr, int rest) {
        if (rest==0)return 0;
        int N = arr.length;
        int[][] dp = new int[N+1][rest+1];
        dp[N][0]=0;
        //循环完数组后，如果凑齐了目标则返回0，否则怎么也搞不定
        for (int i = 1; i <=rest; i++) {
            dp[N][i]=Integer.MAX_VALUE;
        }
        for (int index = N-1; index >=0 ; index--) {
            for (int j = 0; j <=rest ; j++) {
                int way=Integer.MAX_VALUE;
                //数组中的每个值可以用多遍
                for (int zhang = 0; zhang*arr[index] <=j ; zhang++) {
                    int next = dp[index + 1][j - zhang * arr[index]];
                    //能搞定则返回0，则将之前的张数加上
                    if (next!=Integer.MAX_VALUE){
                        way=Math.min(way,next+zhang);
                    }
                }
                dp[index][j]=way;
            }
        }
        return dp[0][rest];
    }

    /**
     *todo 优化枚举
     */
    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = aim(arr, aim);
            int ans2 = dp(arr, aim);
//            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 ) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }
}


