package com.class20;

/**
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[] arr、int N，int a、int b
 */
public class CoffceEvent {
    public static int coffce(int[] arr,int a,int b){
        return process(arr,a,b,0,0);
    }

    /**
     *
     * @param arr 每个人喝完的时间
     * @param a 洗杯子的时间
     * @param b 自然挥发的时间
     * @param index
     * @param washtime 洗的机器什么时候可以用
     * @return
     */
    private static int process(int[] arr, int a, int b, int index, int washtime) {
        if (a>b)return 0;
        int N = arr.length;
        if (N -1==index){
//            最后一个人喝完分自然挥发还是机洗
           return Math.min(arr[index]+b, Math.max(washtime,arr[index])+a);
        }
        //不是最后一杯则分当前杯是机洗还是自然挥发；
//        其中还要index和index后面的都要洗完
        //index机洗时间
        int wash=Math.max(washtime,arr[index])+a;
        //index....机洗时间
        int next1=process(arr,a,b,index+1,wash);
//        取最大是因为最长时间为全部都洗完
        int p1 = Math.max(wash,next1);
        //挥发
        int next2 = process(arr,a,b,index+1,washtime);
        int p2=Math.max(arr[index]+b, next2);
        return Math.min(p2,p1);
    }

    private static int dp(int[] arr, int a, int b){
        if (a>b)return 0;
        int N = arr.length;
        int limit =0;
        for (int i = 0; i < N; i++) {
            limit=Math.max(arr[i],limit)+a;
        }
        int[][] dp = new int[N][limit+1];
        for (int washtime = 0; washtime <= limit; washtime++) {
            dp[N-1][washtime]=Math.min(arr[N-1]+b, Math.max(washtime,arr[N-1])+a);
        }
        for (int index = N-2; index >=0 ; index--) {
            for (int washtime = 0; washtime <=limit ; washtime++) {
                int p1=Integer.MAX_VALUE;
                int wash=Math.max(washtime,arr[index])+a;
                //当index的时间小于最大limit时才能走机洗这种情况
                if (wash<=limit){
                    //index....机洗时间
                    int next1=dp[index+1][wash];
//        取最大是因为最长时间为全部都洗完
                    p1 = Math.max(wash,next1);
                }
                //挥发
                int next2 = dp[index+1][washtime];
                int p2=Math.max(arr[index]+b, next2);
                dp[index][washtime]=Math.min(p2,p1);
            }
        }
       return dp[0][0];
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4};
        System.out.println(coffce(arr,3,10));
        System.out.println(dp(arr,3,10));
    }
}
