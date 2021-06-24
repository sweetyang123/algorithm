package com.class05trie;


import java.util.Arrays;

/**
 * 0-200
 * 计数排序：先拿到数组中的最大值，再以最大值+1为长度创建一个数组
 * 循环原数组，将原数组中的值作为新数组的下标，并++，则将原默认的0变为1了
 * 最后循环新数组，值为1的放到数组中
 */
public class CountSort {
    private static void sort(int[] arr){
        if (arr==null||arr.length<2)return;
       int max = Integer.MIN_VALUE;
       //拿到数组中的最大值
        for (int i = 0; i <arr.length ; i++) {
            max = Math.max(arr[i],max);
        }
        int[] bucket = new int[max+1];
        for (int i = 0; i <arr.length ; i++) {
            bucket[arr[i]]++;
        }
        int index=0;
        for (int i = 0; i <bucket.length ; i++) {
            while (bucket[i]-->0){
                arr[index++]=i;
            }
        }
    }

    public static void main(String[] args) {
        int testTime=50000;
        int maxValue=100;
        int maxSize=100;
        for (int i = 0; i <testTime ; i++) {
            int[] arr1=randomArr(maxValue,maxSize);
            int[] arr2=copyArr(arr1);
            sort(arr1);
            comparator(arr2);
            if (!isEqual(arr1,arr2)){
                print(arr1);
                print(arr2);
                return;
            }
        }
        System.out.println("success");

    }
    private static boolean isEqual(int[] arr1,int[] arr2){
        if ((arr1==null&&arr2!=null)||(arr1!=null&&arr2==null))return false;
        if (arr1.length!=arr2.length)return false;
        if (arr1==null&&arr2==null)return true;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i]!=arr2[i])return false;
        }
        return true;
    }
    private static int[] randomArr(int maxValue, int maxSize) {
        int size = (int)(Math.random()*maxSize+1);
        int[] arr=new int[size];
        for (int i = 0; i <size ; i++) {
            arr[i]=(int)(Math.random()*maxValue+1);
        }
        return arr;
    }
    private static void print(int[] arr){
        if (arr==null)return;
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println(" ");
    }
    private static int[] copyArr(int[] arr){
        if (arr==null)return null;
        int[] arr1 = new int[arr.length];
        for (int i = 0; i <arr.length ; i++) {
           arr1[i]=arr[i];
        }
        return arr;
    }
    private static void comparator(int[] arr){
        Arrays.sort(arr);
    }
}
