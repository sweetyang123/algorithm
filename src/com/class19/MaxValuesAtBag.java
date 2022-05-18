package com.class19;

/**
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class MaxValuesAtBag {
    public static int maxValues(int[] weights, int[] values, int i, int bag) {
        //背包满了后
        if (bag < 0) return -1;
//        i大于数组长度后返回0
        if (weights.length == i) return 0;
        //不选取当前价值和选取当前价值，取最大值
        int p1 = maxValues(weights, values, i + 1, bag);
        int p = maxValues(weights, values, i + 1, bag - weights[i]);
        int p2 = 0;
        if (p != -1) p2 = values[i] + p;
        return Math.max(p1, p2);
    }

    /**
     * 动态规划：
     * ①、先看暴力递归中调用函数，构建以N为行，bag为列的二维数组；
     * 最终是要拿到（0，bag）位置上的数；
     * ②、从base case中可以得到bag小于0的无效；第N行上的值全为0；
     * ③、根据递归可以看出第index行的值可以由index+1拿到；整个二维空间是从下到上，从左到右构建的
     * ④、根据递归填满二维表
     *
     * @param weights
     * @param values
     * @param bag
     * @return
     */
    public static int maxValuesdp(int[] weights, int[] values, int bag) {
        int N = weights.length;
        int[][] dp = new int[N + 1][bag + 1];
//        第N行都为0，已经确定好了，现从N-1向上找
        for (int index = N - 1; index >= 0; index--) {
            //bag则从左往右找，最后要拿到0，bag位置上的值
            for (int rest = 0; rest <= bag; rest++) {
//                dp[index][rest]=?
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                if (rest - weights[index] >= 0) p2 = values[index] + dp[index + 1][rest - weights[index]];
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValues(weights, values, 0, bag));
        System.out.println(maxValuesdp(weights, values, bag));
    }
}
