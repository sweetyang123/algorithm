package com.class02;

/**
 * 求一个数组的（X，Y）逆序对，X>Y，且X的位置在Y的前面
 */
public class ReversePair {
    public static int reversePair(int[] arr) {
        if (arr.length < 2 || arr == null) return 0;
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (R == L) return 0;
        int M = L + ((R - L) >> 1);
        return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int N = help.length - 1;
        int i = N;
        int p1 = m, p2 = r;
        int sum = 0;
        while (p1 >= l && p2 > m) {
            if (arr[p1] > arr[p2]) {
                for (int j = m + 1; j <= p2; j++) {
//                    输出逆序对
                    System.out.print(arr[p1] + "---" + arr[j] + "；");
                }
            }
            //倒序判断累加
            sum += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return sum;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 1;
        int maxSize = 5;
        int maxValue = 10;
        boolean succed = true;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            printArray(arr1);
            System.out.println(reversePair(arr1) + "=======" + comparator(arr2));
//            if (reversePair(arr1) != comparator(arr2)) {
//                succed=false;
//                System.out.println("Oops!");
//                printArray(arr1);
//                printArray(arr2);
//                break;
//            }
        }
        System.out.println("测试结束" + (succed ? "Nice" : "Bad"));
    }
}
