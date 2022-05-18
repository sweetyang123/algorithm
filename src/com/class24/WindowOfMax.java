package com.class24;

import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class WindowOfMax {
    public static int[] getMaxArr(int[] arr, int w) {
        if (arr.length == 0 || w <= 0) return new int[1];
        int n = arr.length;
//        int index=0;
        int[] res = new int[n - w + 1];
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int r = 0; r < n; r++) {
            //将窗口内的最大值从尾保留到队列中
            while (!list.isEmpty() && arr[list.getLast()] <= arr[r]) {
                list.pollLast();
            }
            list.addLast(r);
//            让过期数（不在窗口内的）从头出队
            if (list.peekFirst() < r - w + 1) {
                list.pollFirst();
            }
//            获取当前窗口的最大值
            if (r - w + 1 >= 0) {
                res[r - w + 1] = arr[list.peekFirst()];
                System.out.println(res[r - w + 1]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[1];
        int w = 0;
        getMaxArr(arr, w);
    }
}
