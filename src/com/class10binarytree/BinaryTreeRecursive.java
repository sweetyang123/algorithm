package com.class10binarytree;

/**
 * 二叉树的
 * 先序：头左右；
 * 中序：左头右；
 * 后序：左右头
 * 递归实现，递归时每个节点都会到达三次，所以各种顺序很好实现
 */
public class BinaryTreeRecursive {
    private static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }
    //先序遍历
   private void pre(Node head){
        if (head==null)return;
       System.out.print(head.value+" ");
       if (head.left!=null)pre(head.left);
       if (head.right!=null)pre(head.right);
    }
    //中序遍历
    private void in(Node head){
        if (head==null)return;
        if (head.left!=null)in(head.left);
        System.out.print(head.value+" ");
        if (head.right!=null)in(head.right);
    }
    //后序遍历
    private void post(Node head){
        if (head==null)return;
        if (head.left!=null)post(head.left);
        if (head.right!=null)post(head.right);
        System.out.print(head.value+" ");
    }

    public static void main(String[] args) {
        BinaryTreeRecursive bt = new BinaryTreeRecursive();
        Node head = new Node(1);
        head.left=new Node(2);
        head.right=new Node(3);
        head.left.left=new Node(4);
        head.left.right=new Node(5);
        head.right.left=new Node(6);
        head.right.right=new Node(7);
        bt.post(head);
    }
}
