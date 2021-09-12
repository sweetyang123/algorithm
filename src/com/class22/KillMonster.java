package com.class22;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class KillMonster {
    private static double killMonster(int N,int M,int K){
        if (N<1||M<1||K<1)return 0;
        return (double) ((double) process(N,M,K)/(double) Math.pow(M+1,K));
    }

    /**
     *
     * @param n 有n滴血
     * @param m 每次砍m滴血
     * @param k 还剩k刀
     * @return
     */
    private static double process(int n, int m, int k) {
        if (k==0)return n<=0?1:0;
        //血量流完后还剩k刀，则剩下的都满足
        if (n<=0)return Math.pow(m+1,k);
        int way=0;
        for (int i = 0; i <= m; i++) {
            way+=process(n-i,m,k-1);
        }
        return way;
    }
    private static double dp(int n, int m, int k){
        if (n<1||m<1||k<1)return 0;
        double[][] dp = new double[n+1][k+1];
        dp[0][0]=1;
        for (int j = 1; j <=k; j++) {
            dp[0][j]= Math.pow(m+1,k);
            for (int i = 0; i <= n; i++) {
                int way=0;
                for (int l = 0; l <=m; l++) {
                    if (i-l>=0){
                        way+=dp[i-l][j-1];
                    }else {
//                        最后一次将血砍完后，剩下的刀数-1
                        way+=Math.pow(m+1,j-1);
                    }

                }
                dp[i][j]=way;
            }
        }
        return (double) ((double)dp[n][k]/(double) Math.pow(m+1,k));
    }

    /**
     * 优化中间的循环枚举，
     * 相邻两个格子的值依赖的格子有重复的内容
     * @param n
     * @param m
     * @param k
     * @return
     */
    private static double dp2(int n, int m, int k){
        if (n<1||m<1||k<1)return 0;
        double[][] dp = new double[n+1][k+1];
        dp[0][0]=1;
        for (int j = 1; j <=k; j++) {
            dp[0][j]= Math.pow(m+1,k);
            for (int i = 1; i <= n; i++) {
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
                if (i-m-1>=0){
                    dp[i][j]-=dp[i-m-1][j-1];
                }else {
                    dp[i][j]-=Math.pow(m+1,j-1);
                }
            }
        }
        return (double) ((double)dp[n][k]/(double) Math.pow(m+1,k));
    }
    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = killMonster(N, M, K);
            double ans2 = dp(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 &&ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
