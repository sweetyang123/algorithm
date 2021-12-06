package com.class24;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 *
 *
 * 解：准备两个双向队列，一个装最大值，一个装最小值；
 * x~y满足条件，则x+1~y，x+2~y.。。。之间的子序列也满足条件
 */
public class WindowSubSequence {
    public static int getSubSeq(int[] arr,int num){
        if (arr.length==0)return 0;
        int n = arr.length;int r = 0;
        LinkedList<Integer> maxWindow = new LinkedList<Integer>();
        LinkedList<Integer> minWindow = new LinkedList<Integer>();
        int count = 0;
        for (int i = 0; i<n ; i++) {
            //最大值和最小值队列
            while (r<n){
                while (!maxWindow.isEmpty()&&arr[maxWindow.getLast()]<=arr[r]){
                    maxWindow.pollLast();
                }
                maxWindow.addLast(r);
                while (!minWindow.isEmpty()&&arr[minWindow.getLast()]>=arr[r]){
                    minWindow.pollLast();
                }
                minWindow.addLast(r);
                //直到sub中最大值 – sub中最小值>num就跳出计算子序列个数
                if (arr[maxWindow.getFirst()]-arr[minWindow.getFirst()]>num){
                    break;
                }else {
                    r++;
                }
            }
//            当0到n满足条件，则子序列1到n ,2到n....都满足条件
             count+=r-i;
//            当队列里的头小于i时，将过期数出队
            if (i==maxWindow.peekFirst())maxWindow.pollFirst();
            if (i==minWindow.peekFirst())minWindow.pollFirst();
//
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,3,5,4,3,3,6,7};
        int w =3;
        System.out.println(getSubSeq(arr,w));
    }
}
