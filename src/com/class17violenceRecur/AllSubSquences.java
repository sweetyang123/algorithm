package com.class17violenceRecur;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部子序列
 * 只能往后拼接
 */
public class AllSubSquences {
    private static List<String> allSubSquences(String str){
        char[] chars = str.toCharArray();
        String path="";
        List<String> ans = new ArrayList<>();
        process(chars,0,path,ans);
        return ans;
    }

    private static void process(char[] chars, int index, String path, List<String> ans) {
        if (index==chars.length){
           if (!path.equals(""))ans.add(path);
            return;
        }
        //未要index上的字符
        process(chars,index+1,path,ans);
//        要了index上的字符
        process(chars,index+1,path+chars[index],ans);
    }

    public static void main(String[] args) {
        List<String> list=allSubSquences("abc");
        for (String s:list) {
            System.out.println(s);
        }
    }
}
