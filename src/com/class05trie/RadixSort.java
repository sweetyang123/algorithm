package com.class05trie;

/**
 * 基数排序
 */
public class RadixSort {
    //获取数组中最大数的位数
    private static int getSize(int[] arr){
        if (arr==null)return 0;
        int max=Integer.MIN_VALUE;
        for (int i = 0; i <arr.length ; i++) {
            max=Math.max(arr[i],max);
        }
        int count = 0;
        while (max!=0){
            count++;
            max=max/10;
        }
        return count;
    }
    //获取一个数的第n位是几
    private static int getDigit(int num,int d){
        return (num/(int)Math.pow(10,d-1))%10;
    }
    private static void radixSort(int[] arr,int l,int r,int digit){
        if (arr==null||arr.length<2)return;
        int[] help = new int[r-l+1];
        //最大数的位数，有几位循环几次，分别从个位，十位。。。排序
        for (int i = 1; i <=digit ; i++) {
            int[] count = new int[10];
//            分别得到个位相同的数的个数。。。
            for (int j = l; j < r; j++) {
                int digit1 = getDigit(arr[j], i);
                count[digit1]++;
            }
//            从前往后累加，拿到<=0,<=1,<=2。。。的个数
            for (int j = 1; j <=count.length; j++) {
                count[j]=count[j]+count[j-1];
            }
//            将数组从右到左反向将值在count辅助下个位有序。。。的放到help中
            for (int j = r-1; j >=l; j++) {
                int digit1 = getDigit(arr[j], i);
                help[count[digit1]-1]=arr[j];
                count[digit1]--;
            }
            for (int j = 0; j <help.length ; j++) {
                arr[j]=help[j];
            }
        }
    }
}

