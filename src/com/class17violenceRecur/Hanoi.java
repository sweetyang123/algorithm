package com.class17violenceRecur;

/**
 * 打印n层汉诺塔从最左边移动到最右边的全部过程（圆盘在任何时候都需要保持从小到大的顺序）
 * 将n-1层移动到中间柱子；再直接将第n个盘子放到最右边的柱子；
 * 最后再将中间的n-1的放到后边柱子；以此类推
 */
public class Hanoi {
    /**
     * 从左边到右边，分为n-1从左边到中间，n从左边到右边，n-1从中间到右边；
     * 在细分
     * @param n
     */
    private static void leftToRight(int n){
        if (n==1) {System.out.println(n+"  from left to right");return;}
        leftToMid(n-1);
        System.out.println(n+"  from left to right");
        midToRight(n-1);
    }

    private static void midToRight(int n) {
        if (n==1) {System.out.println(n+"  from mid to right");return;}
        midToLeft(n-1);
        System.out.println(n+"  from mid to right");
        leftToRight(n-1);
    }

    private static void midToLeft(int n) {
        if (n==1) {System.out.println(n+"  from mid to left");return;}
        midToRight(n-1);
        System.out.println(n+"  from mid to left");
        rightToLeft(n-1);
    }

    private static void rightToLeft(int n) {
        if (n==1) {System.out.println(n+"  from right to left");return;}
        rightToMid(n-1);
        System.out.println(n+"  from right to left");
        midToLeft(n-1);
    }

    private static void rightToMid(int n) {
        if (n==1) {System.out.println(n+"  from right to mid");return;}
        rightToLeft(n-1);
        System.out.println(n+"  from right to mid");
        leftToMid(n-1);
    }


    private static void leftToMid(int n){
        if (n==1) {System.out.println(n+"  from left to mid");return;}
        leftToRight(n-1);
        System.out.println(n+"  from left to mid");
        rightToMid(n-1);
 }

    /**
     * 暴力递归
     * @param n
     * @param left
     * @param mid
     * @param right
     */
    private static void leftToRight(int n,String left,String mid,String right){
        if (n==1) {System.out.println(n+"  from "+left+" to "+right);return;}
//        将n-1从左移到中间
        leftToRight(n-1,left,right,mid);
//        将n从左边移到右边
        System.out.println(n+"  from "+left+" to "+right);
//        将n-1从中间移到右边
        leftToRight(n-1,mid,left,right);
    }

    public static void main(String[] args) {
        leftToRight(3);
        System.out.println("------------");
        leftToRight(3,"left","mid","right");
    }
}
