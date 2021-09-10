package com.class21;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 */
public class MinPathSum {
    private static int minPathSum(int[][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        return process(matrix,0,matrix[0].length-1);
    }

    private static int process(int[][] matrix, int x, int y) {
        if (x<0||x>matrix.length-1||y<0||y>matrix[0].length-1)return 0;
        if (x==matrix.length-1&&y==0)return matrix[x][y];
//        在x轴上则只能向左移动
        int p1;
        if (y==0)p1=process(matrix,x-1,0)+matrix[x][0];
//        在x轴上则只能向下移动
        if (x==matrix.length-1)return matrix[matrix.length-1][y];

        process(matrix,x+1,y);
        process(matrix,x,y-1);

        return matrix[x][y];
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
//        System.out.println(minPathSum1(m));

    }
}
