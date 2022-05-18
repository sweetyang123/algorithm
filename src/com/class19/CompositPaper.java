package com.class19;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 */
public class CompositPaper {
    public static int paper(String str, String[] arr) {
        if (arr == null || arr.length == 0 || str == null || str.length() == 0) return 0;
        return process(arr, str);
    }

    private static int process(String[] arr, String rest) {
        if (rest.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            String aim = getRest(rest, arr[i]);
            //用了当前字符串
            if (aim.length() != rest.length()) {
                min = Math.min(min, process(arr, aim));
            }
        }

        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String getRest(String rest, String target) {
        int[] rests = new int[26];
        //将字符串映射到数组中，如有两个b则为rests[2]=2
        for (char c : rest.toCharArray()) {
            rests[c - 'a'] = rests[c - 'a'] == 0 ? 1 : (rests[c - 'a'] + 1);
        }
        //将arr[index] 字符串在目标字符串中清除
        for (char str : target.toCharArray()) {
            if (rests[str - 'a'] > 0) rests[str - 'a']--;
        }
//        获取清除后的目标字符串
        StringBuffer aim = new StringBuffer();
        for (int i = 0; i < rests.length; i++) {
            if (rests[i] > 0) {
                for (int j = 0; j < rests[i]; j++) {
                    aim.append((char) ('a' + i));
                }
            }

        }
        return aim.toString();
    }

    public static void main(String[] args) {
        String s = "babac";
        String[] c = new String[]{"ba", "c", "abcd"};
        System.out.println(paper(s, c));
    }
}
