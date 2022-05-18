package com.test;

import java.util.concurrent.locks.ReentrantLock;

public class testVar {
    public static void main(String[] args) {
        testVar t = new testVar();
        System.out.println(t.reverseWords("Let's take LeetCode contest"));
    }

    public  String reverseWords(String s) {

        String[] strs = s.split(" ");
        String sq = "";
        for (int i = 0; i < strs.length; i++) {
            sq += reverse(strs[i].toCharArray()) + " ";
        }
        return sq.substring(0, sq.length() - 1);
    }

    public String reverse(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(s);
    }
}
