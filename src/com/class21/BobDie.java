package com.class21;

/**
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class BobDie {
    private static double die(int row,int col,int k,int N,int M){
        return (double) process(row,col,k,N,M)/Math.pow(4, k);
    }

    private static long process(int x, int y, int rest, int N, int M) {
        //跳出N,M就死亡，直接返回0
        if (x<0||x==N||y<0||y==M)return 0;
//        没有跳出格子，剩余步数为0则成功，算一种方法
        if (rest==0)return 1;
//        四个方向的尝试
        long way=process(x+1,y,rest-1,N,M);
        way+=process(x-1,y,rest-1,N,M);
        way+=process(x,y-1,rest-1,N,M);
        way+=process(x,y+1,rest-1,N,M);
        return way;
    }

    /**
     *
     * @param x
     * @param y 初始位置
     * @param k 剩余步数
     * @param N
     * @param M 整个区域的大小， 落在N,M上就算死
     * @return
     */
    private static double dp(int x, int y, int k, int N, int M) {
//        根据base case 在格子里且剩余步数为0则成功
        long[][][] dp = new long[N][M][k+1];
        for (int i = 0; i <N; i++) {
            for (int j = 0; j <M; j++) {
                dp[i][j][0]=1;
            }
        }
//        先循环剩余的步数，再去填满整个三维表
        for (int rest = 1; rest <=k ; rest++) {
            for (int j = 0; j <M; j++) {
                for (int i = 0; i <N; i++)  {
                    dp[i][j][rest] = pick(dp,i+1,j,rest-1,N,M);
                    dp[i][j][rest] += pick(dp,i-1,j,rest-1,N,M);
                    dp[i][j][rest] += pick(dp,i,j+1,rest-1,N,M);
                    dp[i][j][rest] += pick(dp,i,j-1,rest-1,N,M);
                }
            }
        }
        return (double) dp[x][y][k]/Math.pow(4, k);
    }
//    越界则返回0，防止越界
    private static long pick(long[][][] dp, int i, int j, int rest, int N, int M) {
        if (i<0||i==N||j<0||j==M)return 0;
        return dp[i][j][rest];
    }




    public static void main(String[] args) {
        System.out.println(die(6, 6, 10, 10, 10));
        System.out.println(dp(6, 6, 10, 10, 10));
    }
}
