package com.class10binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一颗二叉树是否是搜索二叉树
 * 搜索二叉树，左子树比头节点小，右子树比头节点大
 */
public class IsSBT {
    static  class Node{

        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    static class Info{
        boolean isS;//是否是搜索树
        int max;//树的最大值
        int min;//树的最小值

        public Info(boolean isS, int max, int min) {
            this.isS = isS;
            this.max = max;
            this.min = min;
        }
    }
    private  Info processIsSBT(Node head){
        if (head==null)return null;
        Info leftInfo = processIsSBT(head.left);
        Info rightInfo = processIsSBT(head.right);
        int max=head.value;
        int min=head.value;
        if (leftInfo!=null){
            max=Math.max(max,leftInfo.max);
            min=Math.min(min,leftInfo.min);
        }
        if (rightInfo!=null){
            max=Math.max(max,rightInfo.max);
            min=Math.min(min,rightInfo.min);
        }
        boolean isS = true;
        if (leftInfo!=null&&!leftInfo.isS)isS=false;
        if (rightInfo!=null&&!rightInfo.isS)isS=false;
        if (leftInfo!=null&&leftInfo.max>=head.value)isS=false;
        if (rightInfo!=null&&rightInfo.min<=head.value)isS=false;
        return new Info(isS,max,min);
    }
    private Boolean process01(Node head){
        if (head==null)return true;
        return processIsSBT(head).isS;
    }
    private Boolean process(Node head) {
        if (head==null)return true;
        List<Node> list=new ArrayList();
        fromWeight(head,list);
        for (int i = 1; i <list.size() ; i++) {
            //前一个节点数如果大于后一个节点的数则不是搜索二叉树
            if (list.get(i).value<=list.get(i-1).value)return false;
        }
        return true;
    }

    private void fromWeight(Node head,List list) {
        if (head==null)return;
        if (head.left!=null)fromWeight(head.left,list);
        list.add(head);
        if (head.right!=null)fromWeight(head.right,list);
    }
    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        IsSBT is= new IsSBT();
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (is.process(head) != is.process01(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
