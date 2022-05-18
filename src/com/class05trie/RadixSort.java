package com.class05trie;

import java.util.Arrays;

/**
 * 基数排序
 * 思想：
 * （给10个桶，最先找到数组中最大数，并算出最大数的位数i（需循环进桶的次数），
 * 先将个位相同的数放到同一个桶里，个位数按序依次放入桶（队列，先进先出的）中，再根据顺序出桶放到帮助数组help中，
 * 再将十位进出桶，百位，千位。。。。知直到最大位i）
 * 实现：
 * 数组实现，给一个大小为10的数组count，先分别算出个位的数，如count[0]的值为个位为0的个数。。。。；
 * 再将count数组中的值依次往后累加，如count[1]=count[0]+count[1]；
 * 最后再将数组从右到左方向根据count数组放入新数组中，得到的新数组则就为个位有序的了；
 * 依次再将十位，百位。。。的数这样操作
 */
public class RadixSort {
    //获取数组中最大数的位数
    private static int getSize(int[] arr) {
        if (arr == null) return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int count = 0;
        while (max != 0) {
            count++;
            max = max / 10;
        }
        return count;
    }

    //获取一个数的第n位是几
    private static int getDigit(int num, int d) {
        return (num / (int) Math.pow(10, d - 1)) % 10;
    }

    /**
     * @param arr
     * @param l
     * @param r
     * @param digit 最大值的位数
     */
    private static void radixSort(int[] arr, int l, int r, int digit) {
        if (arr == null || arr.length < 2) return;
        int[] help = new int[r - l + 1];
        //最大数的位数，有几位循环几次，分别从个位，十位。。。排序
        for (int i = 1; i <= digit; i++) {
            int[] count = new int[10];
//            分别得到个位相同的数的个数。。。
            for (int j = l; j <= r; j++) {
                int digit1 = getDigit(arr[j], i);
                count[digit1]++;
            }
//            从前往后累加，拿到<=0,<=1,<=2。。。的个数
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }
//            将数组从右到左反向将值在count辅助下个位有序。。。的放到help中
            for (int j = r; j >= l; j--) {
                int digit1 = getDigit(arr[j], i);
                help[count[digit1] - 1] = arr[j];
                count[digit1]--;
            }
            for (int j = 0; j < help.length; j++) {
                arr[j] = help[j];
            }
        }
    }

    //test
    private static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) return false;
        if (arr1.length != arr2.length) return false;
        if (arr1 == null && arr2 == null) return true;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    private static int[] randomArr(int maxValue, int maxSize) {
        int size = (int) (Math.random() * maxSize + 1);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxValue + 1);
        }
        return arr;
    }

    private static void print(int[] arr) {
        if (arr == null) return;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(" ");
    }

    private static int[] copyArr(int[] arr) {
        if (arr == null) return null;
        int[] arr1 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = arr[i];
        }
        return arr;
    }

    private static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
        int testTime = 50000;
        int maxValue = 100;
        int maxSize = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = randomArr(maxValue, maxSize);
            int[] arr2 = copyArr(arr1);
            radixSort(arr1, 0, arr1.length - 1, getSize(arr1));
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                print(arr1);
                print(arr2);
                return;
            }
        }
        System.out.println("success");
    }
}

