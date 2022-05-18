package com.class24;

import java.util.LinkedList;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * <p>
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * <p>
 * 输出: 3
 */
public class GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int m = n << 1;
        int[] arr = new int[m];
//        获取长度为2n的数组，用于装gas-cost的值
        for (int i = 0; i < n; i++) {
            arr[i] = gas[i] - cost[i];
            arr[n + i] = gas[i] - cost[i];
        }
//        再将其累加
        for (int i = 1; i < m; i++) {
            arr[i] += arr[i - 1];
        }
//        0到n窗口最小值
        LinkedList<Integer> max = new LinkedList();
        for (int i = 0; i < n; i++) {
            while (!max.isEmpty() && arr[max.getLast()] >= arr[i]) {
                max.pollLast();
            }
            max.addLast(i);
        }
        for (int i = 0, l = 0, r = n; i < n; l = arr[i++]) {
            //如果当前窗口最小值减i前一位的值大于等于0则可以成功
            if (arr[max.getFirst()] - l >= 0) return i;
//            将窗口外的最小值做过期处理
            if (i == arr[max.getFirst()]) {
                max.pollFirst();
            }
//            将n到m窗口最小值
            while (!max.isEmpty() && arr[max.getLast()] >= arr[r + i]) {
                max.pollLast();
            }
            max.addLast(r + i);
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] gas = new int[]{2, 3, 4};
        int[] cost = new int[]{3, 4, 3};
        System.out.println(canCompleteCircuit(gas, cost));
    }
}
