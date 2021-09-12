package com.class21;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 *
 * 不用递归，直接动态规划得到，二维数组和一维数组两种方法实现
 */
public class MinPathSum {
    private static int minPathSum(int[][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        dp[0][0]=matrix[0][0];
        //第0横行只能是由右移得到，则从左到右直接累加
        for (int i = 1; i < N; i++) {
            dp[i][0]=dp[i-1][0]+matrix[i][0];
        }
        //第0竖行只能是由下移得到，则从上到下直接累加
        for (int j = 1; j < M; j++) {
            dp[0][j]=dp[0][j-1]+matrix[0][j];
        }
//        而中间的都可用左边和上边的最小累加得到
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+matrix[i][j];
            }
        }
        return dp[N-1][M-1];
    }

    /**
     * 从二维数组到一维数组的优化，
     * 由于除了第一横行和竖行，
     * 每行的值有依赖于他上面和左面的值
     * 所以每次比较后更新数组
     * @param matrix
     * @return
     */
    private static int minPathSum2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int[] arr= new int[N];
        arr[0]=matrix[0][0];
        //一维数组初始化，第一行累加
        for (int i = 1; i < N; i++) {
            arr[i]=arr[i-1]+matrix[i][0];
        }
        //每次更新一维数组，与之前的值作比较
        for (int i = 1; i < M; i++) {
            arr[0]=arr[0]+matrix[0][i];
            for (int j = 1; j < N ; j++) {
                arr[j]=Math.min(arr[j],arr[j-1])+matrix[j][i];
            }
        }
        return arr[N-1];
    }
    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum(m));
        System.out.println(minPathSum2(m));

    }
}
