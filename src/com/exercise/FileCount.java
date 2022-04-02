package com.exercise;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tian.yang
 * @description
 * @date 2022/4/2 14:53
 */
public class FileCount {
    public static void main(String[] args) {
        System.out.println(process("D:\\tian.yang\\资料\\笔记"));
    }

    /**
     * 给定一个文件目录的路径,写一个函数统计这个目录下所有的文件数并返回
     * @param path
     * @return
     */
    public static int process(String path){
        File file = new File(path);
//        不是文件，文件夹直接返回
        if (!file.isDirectory()&&!file.isFile()){return 0;}
//        根为文件则直接返回1
        if (file.isFile())return 1;
        Queue<File> queue = new LinkedList();
        queue.add(file);
        int count=0;
        while (!queue.isEmpty()){
            File cur = queue.poll();
//            将队列中的文件夹进行遍历，是文件的加1，为文件夹的入队，直到队列为空
            for (File temp:cur.listFiles()) {
                if (temp.isFile()){
                    count++;
                }else if (temp.isDirectory()){
                    queue.add(temp);
                }
            }
        }
        return count;
    }
}
