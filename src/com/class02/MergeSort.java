package com.class02;


public class MergeSort {
    // 递归归并
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    //使数组有序
    public static void process(int[] arr, int L, int R) {
        if (R == L) return;
        int M = L + ((R - L) >> 1);
//       int M=L+(R-L)>>1;报堆栈错误
        //将数组左边进行排序
        process(arr, L, M);
        //将数组右边进行排序
        process(arr, M + 1, R);
        //最后再将左右两边进行排序
        merge(arr, L, M, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int p1 = L, p2 = M + 1, i = 0;
        int[] help = new int[R - L + 1];
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) help[i++] = arr[p1++];
        while (p2 <= R) help[i++] = arr[p2++];
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }

    //非递归归并排序
    public static void mergeSort02(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int mergeSize = 1;
        int N = arr.length;
        while (mergeSize < N) {
            int L = 0;
            int M, R;
            while (L < N) {
                if (mergeSize >= N - L) {
                    break;
                }
                M = L + mergeSize - 1;
                R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) break;
            mergeSize <<= 1;
        }
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
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort02(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
