package com.class19;

/**
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 *
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 */
public class LongestCommonSubsequence {
    /**
     * 一共可分以下几种情况找到最长公共子序列：
     * ①、i-1，j-1
     * ②、i-1，j
     * ③、i，j-1
     * ④、i，j
     * @param str1
     * @param str2
     * @return
     */
    public static int lcsq(String str1,String str2){
        if (str1==null||str1.length()==0||str2==null||str2.length()==0)return 0;
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[][] dp = new int[s1.length][s2.length];
        //每个字符串的第一个位置可以对比确定
        dp[0][0]=s1[0]==s2[0]?1:0;
        for (int i = 1; i < s1.length; i++) {
            dp[i][0]=Math.max(dp[i-1][0],s1[i]==s2[0]?1:0);
        }
        for (int i = 1; i < s2.length; i++) {
            dp[0][i]=Math.max(dp[0][i-1],s2[i]==s1[0]?1:0);
        }
        for (int i = 1; i <s1.length ; i++) {
            for (int j = 1; j <s2.length ; j++) {

                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                if (s1[i]==s2[j]){
                    dp[i][j]=Math.max(dp[i][j],dp[i-1][j-1]+1);
                }
            }
        }
        return dp[s1.length-1][s2.length-1];
    }

    public static void main(String[] args) {
        System.out.println(lcsq("a123b4jj","df1j34g"));
    }
}
