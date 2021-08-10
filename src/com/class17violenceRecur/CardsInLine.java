package com.class17violenceRecur;

import java.util.Stack;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数
 */
public class CardsInLine {
    public static int first(int[] arr,int l,int r){
        if (l==r)return arr[l];
        //当先手拿的是最左的则后手只能在arr[l+1...r]上选
        //当先手拿的是最右的则后手只能在arr[l...r-1]上选
        //最后选最大的分数
        return Math.max(arr[l]+second(arr,l+1,r),arr[r]+second(arr,l,r-1));
    }

    private static int second(int[] arr, int l, int r) {
        if (l==r)return 0;
        //先手拿走了l或r上的，由于在一个数组里后手只能拿剩下的小的
        return Math.min(first(arr,l+1,r),first(arr,l,r-1));
    }

    public static void main(String[] args) {
        int[] arr=new int[]{4,13,23,11,24};
        System.out.println(first(arr,0,arr.length-1));
        System.out.println(second(arr,0,arr.length-1));
    }
}
