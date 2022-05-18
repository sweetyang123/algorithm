package com.class22;

/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
public class SplitNumber {
    public static int splitNumber(int num) {
        if (num < 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return process(num, 1);
    }

    private static int process(int rest, int pre) {
        if (rest == 0) return 1;
        //后面的数不能比前面的数小
        if (pre > rest) return 0;
        int way = 0;
        for (int index = pre; index <= rest; index++) {
            way += process(rest - index, index);
        }
        return way;
    }

    private static int dp(int rest, int pre) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 1) {
            return 1;
        }
        int[][] dp = new int[rest + 1][rest + 1];
        for (int i = 1; i <= rest; i++) {
            dp[0][i] = 1;
            dp[i][i] = 1;
        }
        for (int i = 1; i <= rest; i++) {
            for (int j = 0; j <= rest; j++) {
                int way = 0;
                for (int index = pre; index <= rest; index++) {
                    way += process(i - index, index);
                }
                dp[i][j] = way;
            }
        }

        return dp[rest][1];
    }


    public static void main(String[] args) {
        int test = 39;
        System.out.println(splitNumber(test));
//        System.out.println(dp1(test));
//        System.out.println(dp2(test));
    }
}
