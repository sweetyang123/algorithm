package com.class10binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 是否是完全二叉树：
 * ①宽度遍历中加入判断：任何节点有右无左，返回false；一旦遇到左右孩子不双全，后续节点必为叶节点
 * ② 递归套路：为满二叉树，无缺口；
 *             左孩子为完全二叉树，右孩子为满二叉树，且左边高度=右边高度+1；
 *             左孩子为满二叉树，右孩子为满二叉树，且左边高度=右边高度+1；
 *             左孩子为满二叉树，右孩子为完全二叉树，且左边高度=右边高度
 */
public class IsCBT {
    static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }


    }
    /**
     * 宽度遍历中加入判断：任何节点有右无左，返回false；一旦遇到左右孩子不双全，后续节点必为叶节点
     * @param head
     * @return
     */
    private boolean process(Node head){
        if (head==null)return false;
        Queue<Node> queue = new LinkedList();
        boolean leaf=false;
        queue.add(head);
        while (!queue.isEmpty()){
            Node cur=queue.poll();
            //如果当前节点为叶节点，则如果有左右子树则不为完全二叉树
            if ((leaf&&!(cur.left==null&&cur.right==null))
                    //右子树不为空，但左子树为空则不为完全二叉树
                    ||(cur.right!=null&&cur.left==null))return false;
            //左右节点都为空，则是叶节点
            if (cur.left==null||cur.right==null)leaf=true;
            if (cur.left!=null)queue.add(cur.left);
            if (cur.right!=null)queue.add(cur.right);
        }
        return true;
    }
    static  class Info{
        boolean isFull;//是否是满二叉树
        boolean isCom;//是否是完全二叉树
        int height;//树的高度

        public Info(boolean isFull, boolean isCom, int height) {
            this.isFull = isFull;
            this.isCom = isCom;
            this.height = height;
        }
    }

    /**
     * 递归套路：为满二叉树，无缺口；
     *  *             左孩子为完全二叉树，右孩子为满二叉树，且左边高度=右边高度+1；
     *  *             左孩子为满二叉树，右孩子为满二叉树，且左边高度=右边高度+1；
     *  *             左孩子为满二叉树，右孩子为完全二叉树，且左边高度=右边高度
     * @param head
     * @return
     */
    private Info  processRecu(Node head){
        if (head==null)return new Info(true,true,0);
        Info leftInfo = processRecu(head.left);
        Info rightInfo = processRecu(head.right);
        int height=Math.max(leftInfo.height,rightInfo.height)+1;
        boolean isFull=leftInfo.isFull&&rightInfo.isFull&&leftInfo.height==rightInfo.height;
        boolean isCom=false;//是否是完全二叉树
        if (((leftInfo.isCom||rightInfo.isFull)&&rightInfo.isFull&&leftInfo.height==rightInfo.height+1)||
                (leftInfo.isFull&&rightInfo.isCom&&leftInfo.height==rightInfo.height))isCom=true;
        return new Info(isFull,isCom,height);

    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.left.right = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        IsCBT cbt = new IsCBT();
        System.out.println(cbt.process(head));
    }

}
