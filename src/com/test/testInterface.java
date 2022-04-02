package com.test;/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class testInterface {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) return new ArrayList(0);
        int curLevel =1;
        Map<TreeNode,Integer> curMap= new HashMap<>();
        curMap.put(root,curLevel);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        while(!queue.isEmpty()){
            TreeNode cur= queue.poll();
            int tempLevel = curMap.get(cur);
            if(cur.left!=null){
                queue.add(cur.left);
                curMap.put(cur.left,tempLevel+1);
            }
            if(cur.right!=null){
                queue.add(cur.right);
                curMap.put(cur.right,tempLevel+1);
            }
            if(curLevel==tempLevel){
                list.add(cur.val);
                System.out.print(cur.val+",");

            }else{
                res.add(list);
                list.clear();
                curLevel++;
                System.out.println("-----------");
            }
        }
        return res;
    }
}
 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
 class MainClass {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main1(String[] args) throws IOException {
            String str = "a good   example";

        String[] split = str.split(" ");
        for (int i = 0; i < split.length; i++) {
            System.out.println("----"+split[i]+"=======");
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            List<List<Integer>> ret = new testInterface().levelOrder(root);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }

     public static void main(String[] args) {

         System.out.println(getLeastNumbers(new int[]{0,0,1,2,4,2,2,3,1,4},8));
     }
     public static int[] getLeastNumbers(int[] arr, int k) {
         if(arr==null||arr.length==0||k==0)return new int[0];
         int[] buket = new int[10001];
         for(int i=0;i<arr.length;i++){
             buket[arr[i]]++;
         }
         int[] res = new int[k];int count=0;
         for(int i=0;i<buket.length;i++){
                 while(buket[i]>0){
                     res[count++]=i;
                     if(--k==0)return res;
                     buket[i]--;
                 }
         }
         return res;
     }

     public static boolean isStraight(int[] nums) {
         int[] buket = new int[14];
         for(int i=0;i<nums.length;i++){
             if(nums[i]==0)continue;//遇到大小王就跳过
             if(buket[nums[i]]>0)return false; //有重复的就直接返回
             buket[nums[i]]++;
         }
         int left=1,right=13;
         while(buket[left]==0)left++;//获取最小值
         while(buket[right]==0)right--;//获取最大值
         return right-left<5?true:false;//最大值，最小值之差小于5则为顺子
     }
     public static int movingCount(int m, int n, int k) {
        boolean[][] flag=new boolean[m][n];
         return process(0,0,m,n,k,flag);
     }
     static int  process2(int m,int n,int k){
        int count=1;
        boolean[][] dp =new boolean[m][n];
         dp[0][0]=true;
         for (int i = 0; i <m ; i++) {
             for (int j = 0; j <n ; j++) {
//                 if (((i>0&&j==0&&dp[i-1][j])||(j>0&&i==0&&dp[i][j-1])||(i>0&&j>0&&(dp[i-1][j]||dp[i][j-1])))&&getSum(i)+getSum(j)<=k){
                     if (i>0&&j==0&&dp[i-1][j]&&getSum(i)+getSum(j)<=k){
                     count++;
                     dp[i][j]=true;
                 }else if (j>0&&i==0&&dp[i][j-1]&&getSum(i)+getSum(j)<=k){
                     count++;
                     dp[i][j]=true;
                 }else if (i>0&&j>0&&(dp[i-1][j]||dp[i][j-1])&&getSum(i)+getSum(j)<=k){
                    count++;
                    dp[i][j]=true;
                }
             }
         }
         return count;
     }
    static int  process(int i,int j,int m,int n,int k,boolean[][] flag){
        int sum=getSum(i)+getSum(j);
        if(i<0||j<0||i>=m||j>=n||sum>k||flag[i][j])return 0;
        flag[i][j]=true;
//        成功加1
        return process(i + 1, j, m, n, k,flag)+process(i - 1, j, m, n, k,flag)+process(i,j + 1, m, n, k,flag)+process(i,j - 1, m, n, k,flag)+1;
     }
    static int getSum(int n){
         if(n<10)return n;
         int sum = 0;
         while(n>0){
             sum+=n%10;
             n=n/10;
         }
         return sum;
     }
}