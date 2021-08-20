package com.class17violenceRecur;

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
    private static int dpWays(int[] arr){
        if (arr==null||arr.length==0)return 0;
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        //根据base case将对角线上的数填上
        for (int i = 0; i <N ; i++) {
            f[i][i]=arr[i];
        }
        //每条对角线可由上一条对角线推导出来
//        （0，startCol）是每条对角线的开始
        for (int startCol = 1; startCol <N ; startCol++) {
            int l=0;int r=startCol;
            while (r<N){
                f[l][r]= Math.max(arr[l]+s[l+1][r],arr[r]+s[l][r-1]);
                s[l][r]= Math.min(f[l+1][r],f[l][r-1]);
//                每条对角线上的位置，如(0,1),(1,2),(2,3)
                l++;r++;
            }
        }
        return Math.max(f[0][N-1],s[0][N-1]);
    }

    public static void main(String[] args) {
        int[] arr=new int[]{4,13,23,11,24};
        System.out.println(Math.max(first(arr,0,arr.length-1),second(arr,0,arr.length-1)));
        System.out.println(dpWays(arr));
    }
}
