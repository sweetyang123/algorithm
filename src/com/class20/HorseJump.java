package com.class20;

/**
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 */
public class HorseJump {
    private static int jump(int x,int y, int k){
        if (k==0)return 0;
       return procss(0,0,x,y,k);
    }

    /**
     * 马的跳动可以有8个方向的尝试，相加起来就是总的方法数
     * @param i
     * @param j 起始位置
     * @param x
     * @param y 目标位置
     * @param k 剩余步数
     * @return
     */
    private static int procss(int i, int j,int x, int y, int k) {
        if (i<0||i>9||j<0||j>8)return 0;
        if (k==0)return (i==x&&j==y)?1:0;
        int way=0;
        way+=procss(i+1,j-2,x,y,k-1);
        way+=procss(i+1,j+2,x,y,k-1);
        way+=procss(i+2,j-1,x,y,k-1);
        way+=procss(i+2,j+1,x,y,k-1);
        way+=procss(i-1,j-2,x,y,k-1);
        way+=procss(i-1,j+2,x,y,k-1);
        way+=procss(i-2,j+1,x,y,k-1);
        way+=procss(i-2,j-1,x,y,k-1);
        return way;
    }

    private static int dp(int x, int y, int k){
        int[][][] dp = new int[10][9][k+1];
        dp[x][y][0]=1;
        for (int rest = 1; rest <=k; rest++) {
            for (int i  = 0; i <10 ; i++) {
                for (int j = 0; j <9 ; j++) {
                    int way=pick(dp,i+1,j-2,rest - 1);
                    way+=pick(dp, i+1,j+2, rest - 1);
                    way+=pick(dp, i+2,j-1, rest - 1);
                    way+=pick(dp, i+2,j+1, rest - 1);
                    way+=pick(dp, i-1,j+2, rest - 1);
                    way+=pick(dp, i-1,j-2, rest - 1);
                    way+=pick(dp, i-2,j+1, rest - 1);
                    way+=pick(dp, i-2,j-1, rest - 1);
                    dp[i][j][rest]=way;
                }
            }
        }
        return dp[0][0][k];
    }
    //防止数组越界
    private static int pick(int[][][] dp, int x, int y, int k) {
        if (x<0||x>9||y<0||y>8)return 0;
        return dp[x][y][k];
    }


    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
//        System.out.println(ways(x, y, step));
        System.out.println(dp(x, y, step));

        System.out.println(jump(x, y, step));
    }
}
