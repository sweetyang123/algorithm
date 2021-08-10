package com.class17violenceRecur;

/**
 * 每个皇后不同行，不同列，不同斜线
 */
public class NQueen {
    public static int queen(int n){
        int[] arr = new int[n];
       return process(arr,0);
    }
    private static int process(int[] arr,int i){
        if (i==arr.length)return 1;
        for (int j = i; j <arr.length ; j++) {
            if (isSelected(arr,i,j)){
                process(arr,i+1);
            }
        }
        return 0;
    }
//    判断是否可以在该行该列放皇后，对角线绝对值之差相等判断
    private static boolean isSelected(int[] arr, int i, int j) {
        if (arr[i]!=arr[j]&&Math.abs(i-j)!=Math.abs(arr[i]-arr[j]))return true;
        return false;
    }
}
