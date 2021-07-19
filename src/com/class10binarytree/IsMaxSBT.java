package com.class10binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉树的头节点head，
 * 返回这颗二叉树中最大的二叉搜索子树的大小
 * 搜索二叉树，左子树比头节点小，右子树比头节点大
 */
public class IsMaxSBT {
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
        int size;//搜索树的大小
        int max;//树的最大值
        int min;//树的最小值
        public Info(boolean isS, int size, int max, int min) {
            this.isS = isS;
            this.size = size;
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
        int size = 1;
//        获取max，min的值
        if (leftInfo!=null){
            max=Math.max(max,leftInfo.max);
            min=Math.min(min,leftInfo.min);
            size = Math.max(size,leftInfo.size);
        }
        if (rightInfo!=null){
            max=Math.max(max,rightInfo.max);
            min=Math.min(min,rightInfo.min);
            size = Math.max(size,rightInfo.size);
        }
        boolean isS = false;
//        判断是否子树是否是搜索二叉树，也就是isS是否是true
        if ((leftInfo==null?true:leftInfo.isS)
                &&(rightInfo==null?true:rightInfo.isS)
                &&(leftInfo==null?true:leftInfo.max<head.value)
                &&(rightInfo==null?true:rightInfo.min>head.value)){
            isS=true;
//            如果整棵树都是搜索二叉树，则最大为左子树的size加上右子树的size再加上本身的节点
            size=(leftInfo==null?0:leftInfo.size)+(rightInfo==null?0:rightInfo.size)+1;
        }
        return new Info(isS,size,max,min);
    }
    private int process01(Node head){
        if (head==null)return 0;
        return processIsSBT(head).size;
    }
    private int process(Node head) {
        if (head==null)return 0;
        List<Node> list=new ArrayList();
        in(head,list);
        for (int i = 1; i <list.size() ; i++) {
            //前一个节点数如果大于后一个节点的数则不是搜索二叉树
            //??有问题
            if (list.get(i).value<=list.get(i-1).value){
                return i;
            }
        }
        return list.size();
    }

    private void in(Node head,List list) {
        if (head==null)return;
        if (head.left!=null)in(head.left,list);
        list.add(head);
        if (head.right!=null)in(head.right,list);
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
        IsMaxSBT is= new IsMaxSBT();
//        Node head = new Node(81);
//        head.left= new Node(29);
////        head.right= new Node(29);
//        head.left.left= new Node(86);
//        head.left.left.left= new Node(6);
////        head.right.right= new Node(81);
//        System.out.println(is.process01(head));
//        System.out.println(is.process(head));
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (is.process(head) != is.process01(head)) {
//                is.print(head);
                System.out.println(is.process(head)+"--------"+is.process01(head));
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
    private void print(Node head){
        if(head==null)return;
       if (head.left!=null)print(head.left);
        System.out.print(head.value+" ");
       if (head.right!=null)print(head.right);
    }
}
