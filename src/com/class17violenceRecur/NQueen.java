package com.class17violenceRecur;

/**
 * 每个皇后不同行，不同列，不同斜线
 */
public class NQueen {
    public static int queen(int n) {
        if (n < 1) return 0;
        int[] arr = new int[n];
        return process(arr, 0, n);
    }

    private static int process(int[] arr, int i, int n) {
        if (i == n) return 1;
        int res = 0;
        for (int j = 0; j < arr.length; j++) {
            if (isSelected(arr, i, j)) {
                arr[i] = j;
                res += process(arr, i + 1, n);
            }
        }
        return res;
    }

    //    判断是否可以在该行该列放皇后，对角线绝对值之差相等判断
    private static boolean isSelected(int[] arr, int i, int j) {
        //之前已经占了位置的地方都要判断
        for (int k = 0; k < i; k++) {
//            现在是判断第i行的j位置是否可以放皇后
            if (j == arr[k] || Math.abs(i - k) == Math.abs(arr[k] - j)) return false;
        }
        return true;
    }

    public static int queen1(int n) {
        //不超过32皇后
        if (n < 0 || n > 32) return 0;
        //1向左移n位减1得到后n位都为1，前面全是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process1(limit, 0, 0, 0);
    }

    /**
     * 位运算实现，速度快很多
     *
     * @param limit      要达到的最终效果，前面都是0，后面n位是1
     * @param collimit   列限制
     * @param leftlimit  左斜线限制
     * @param rightlimit 右斜线限制
     * @return
     */
    private static int process1(int limit, int collimit, int leftlimit, int rightlimit) {
        //列限制完全排满时达到最终效果则返回，说明此种尝试成功
        if (limit == collimit) return 1;
//        pos中为1的位置是还可以尝试的
        int pos = limit & (~(collimit | leftlimit | rightlimit));
        int mostRightPos;
        int res = 0;
        while (pos != 0) {
//            遍历pos，获取所有可尝试的位置，从右边的1开始
            mostRightPos = pos & (~pos + 1);
            pos = pos - mostRightPos;
            res += process1(limit, collimit | mostRightPos,
                    (leftlimit | mostRightPos) << 1, (rightlimit | mostRightPos) >> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(queen(15));
        //35681
        System.out.println(System.currentTimeMillis() - start);

        long start1 = System.currentTimeMillis();
        System.out.println(queen1(15));
        //1417
        System.out.println(System.currentTimeMillis() - start1);


    }
}
