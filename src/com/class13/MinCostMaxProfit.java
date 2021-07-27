package com.class13;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 */
public class MinCostMaxProfit {
    public static class Program{
        int c;
        int p;

        public Program(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }

    /**
     * ①先将所有的项目按花费顺序放到小根堆里，
     * ②再将花费小于m（成本）的项目出队放到大根堆里（按利润排序）；
     * 先完成大根堆堆顶的项目，再按上面的方式循环k次（可提前结束，当大根堆为空时）
     * ③累加每次大根堆出来的项目的利润就得到最大利润
     *
     * Profits，Cost等长
     * @param m
     * @param k
     * @return 最大利润
     */
    private static int process(int m,int k, int[] Profits, int[] Cost){
        PriorityQueue<Program> minCost = new PriorityQueue(new CostComparator());
        PriorityQueue<Program> maxProfit = new PriorityQueue(new ProfitComparator());
        //将项目放到小根堆里
        for (int i = 0; i <Cost.length ; i++) {
            minCost.add(new Program(Cost[i],Profits[i]));
        }
        for (int i = 0; i <k ; i++) {
            //将小根堆里小于初始资金m的项目放到大根堆里
            while (!minCost.isEmpty()&&minCost.peek().c<=m){
                maxProfit.add(minCost.poll());
            }
            //大根堆为空可提前结束
            if (maxProfit.isEmpty())return m;
            m+=maxProfit.poll().p;
        }
        return m;
    }
    public static class CostComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.c-o2.c;
        }
    }
    public static class ProfitComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.p-o2.p;
        }
    }
}
