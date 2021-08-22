package com.class19;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class ConvertToString {
    public static int convert(String arr){
        char[] chars = arr.toCharArray();
        return process(chars,0);
    }

    private static int process(char[] chars,int index) {
        //直到数组末尾都是成功的则返回1
        if (index==chars.length)return 1;
//        如果是字符0则没有与之对应的字母
        if (chars[index]=='0')return 0;
//        只有一个字符转化，直接去index+1做决定
       int way= process(chars,index+1);
//       当满足index和index+1之和小于27时，则两个字符做转化，再到第index+2上做决定
        if (index+1<chars.length&&(chars[index]-'0')*10+(chars[index+1]-'0')<27){
            way+=process(chars,index+2);
        }
        return way;
    }

    /**
     * 动态规划，根据只有一个可变参数，则傻缓存用一维数组
     * @param str
     * @return
     */
    private static int dpWays(String str){
        if (str==null||str.length()==0)return 0;
        char[] arr = str.toCharArray();
        int N = arr.length;
        int[] dp = new int[N+1];
        //根据base case if (index==chars.length)return 1获得
        dp[N]=1;
//        根据递归获取dp[index]的值
        for (int index = N-1; index >=0 ; index--) {
            if (arr[index]=='0')dp[index]=0;
            int way=dp[index+1];
            if (index+1<N&&(arr[index]-'0')*10+(arr[index+1]-'0')<27){
                way+=dp[index+2];
            }
            dp[index]=way;
        }
     return dp[0];
    }
    public static void main(String[] args) {
        String str ="12312";
        System.out.println(convert(str));
        System.out.println(dpWays(str));
    }
}
