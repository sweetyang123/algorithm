package com.class17violenceRecur;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 * 每个字符串包含所有字符
 */
public class AllPermutation {
    private static List<String> allPerm(String str) {
        char[] chars = str.toCharArray();
        List<String> ans = new ArrayList<>();
        process(chars, 0, ans);
        return ans;
    }

    private static void process(char[] chars, int index, List<String> ans) {
//            str[0...index-1]的位置已经确定了
        if (index == chars.length) {
            ans.add(String.valueOf(chars));
        }
        //来确定str[index。。。]以后的位置，
        boolean[] visited = new boolean[26];
        for (int i = index; i < chars.length; i++) {
            //分支限界
            if (!visited[chars[i] - 'a']) {
                visited[chars[i] - 'a'] = true;
                swap(chars, i, index);
                process(chars, index + 1, ans);
                swap(chars, index, i);
            }
        }
    }

    private static void swap(char[] chars, int i, int index) {
        char temp = chars[i];
        chars[i] = chars[index];
        chars[index] = temp;
    }

    public static void main(String[] args) {
        List<String> res = allPerm("aac");
        for (String str : res) {
            System.out.println(str);
        }
    }
}
