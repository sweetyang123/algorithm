package com.class17violenceRecur;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class RobotWalk {
    /**
     *
     * @param N
     * @param cur 初始位置
     * @param rest 步数
     * @param aim 最终位置
     * @return
     */
 public static int walk(int N,int cur,int rest,int aim){
     //剩余步数为0时，到了目标说明这种尝试成功了，否则失败
    if (rest==0){
       return cur==aim?1:0;
    }
//    在第一个位置时只能右移
    if (cur==1)return walk(N,2,rest-1,aim);
//    在最后一个位置只能左移
    if (cur==N)return walk(N,N-1,rest-1,aim);
    return walk(N,cur+1,rest-1,aim)+walk(N,cur-1,rest-1,aim);
  }
    public static int dpWalk(int N,int cur,int rest,int aim){
        if (N < 2 || cur < 1 || cur > N || aim < 1 || aim > N || rest < 1) {
            return -1;
        }
     int[][] dp = new int[N+1][rest+1];
        //到达目标且rest=0
     dp[aim][0]=1;
//     循环剩余步数
        for (int i = 1; i <=rest ; i++) {
//            将第一步和最后一步填好
                dp[1][i]=dp[2][i-1];
                dp[N][i]=dp[N-1][i-1];
//                将2到N之间的填好
            for (int j = 2; j <N; j++) {
                dp[j][i]=dp[j+1][i-1]+dp[j-1][i-1];
            }
        }
        return dp[cur][rest];
    }

  public static void main(String[] args) {
   System.out.println(walk(5,2,6,4));
   System.out.println(dpWalk(5,2,6,4));
  }
 }