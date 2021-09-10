package com.class20;

/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 */
public class PalindromeSubsequence {
    public static int subsequence(String str){
        if (str==null||str.length()==0)return 0;
        char[] chars = str.toCharArray();
        return process(chars,0,chars.length-1);
    }

    /**
     * 分四种情况：
     * ① 最长回文子序列的开始在原字符串的开始，但结尾不在
     * ② 最长回文子序列的结尾在原字符串的结尾，但开始不在
     * ③ 最长回文子序列的开始不在原字符串的开始，结尾也不在
     * ④ 最长回文子序列的开始和结尾都在原字符串的开始和结尾上
     * @param chars
     * @param L
     * @param R
     * @return
     */
    private static int process(char[] chars,int L, int R) {
//        只有一个字符时，则为1
        if (L==R){return 1;}
        //有两个字符时，相等则有2个，不等则只有一个，如1234321中间这个4
        if (L==R-1)return chars[L]==chars[R]? 2:1;
        int way=0;
        int p1=process(chars,L+1,R);
        int p2=process(chars,L,R-1);
        int p3=process(chars,L+1,R-1);
        int p4=chars[L]==chars[R]?(2+process(chars,L+1,R-1)):0;
        way=Math.max(p1,p2);
        way=Math.max(Math.max(p3,p4),way);
       return way;
    }
    private static int dp(char[] chars){
        int N = chars.length;
        int[][] dp = new int[N][N];
        dp[N-1][N-1]=1;
        for (int i = 0; i <N-1 ; i++) {
            dp[i][i]=1;
            dp[i][i+1]=chars[i]==chars[i+1]? 2:1;
        }
        for (int L=N-3;L>=0;L--){
            for (int R = L+2; R<N ; R++) {
                //在p1和p2的对比中就将p3pk掉了
//                int p3=dp[L+1][R-1];
                int p4=chars[L]==chars[R]?(2+dp[L+1][R-1]):0;
                dp[L][R]=Math.max(dp[L+1][R],dp[L][R-1]);
                dp[L][R]=Math.max(p4,dp[L][R]);
            }
        }
        return dp[0][N-1];
    }

    public static void main(String[] args) {
        String str="a12b3c43def2ghi1kpm";
        System.out.println(subsequence(str));
        System.out.println(dp(str.toCharArray()));
    }
}
