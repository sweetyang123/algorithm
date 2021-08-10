package com.class17violenceRecur;

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

    public static void main(String[] args) {
        String str ="111";
        System.out.println(convert(str));
    }
}
