package com.class17violenceRecur;

/**
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class MaxValuesAtBag {
    public static int maxValues(int[] weights,int[] values,int i,int bag){
        //背包满了后
        if (bag<0)return -1;
//        i大于数组长度后返回0
        if (weights.length==i)return 0;
        //不选取当前价值和选取当前价值，取最大值
        int p1 = maxValues(weights, values, i + 1, bag);
        int p = maxValues(weights, values, i + 1, bag - weights[i]);
        int p2=0;
        if (p!=-1)p2=values[i]+p;
        return Math.max(p1,p2);
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4,  7, 3,  1, 7 };
        int[] values =  { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxValues(weights,values,0,bag));
    }
}
