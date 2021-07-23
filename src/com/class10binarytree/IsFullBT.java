package com.class10binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 是否是满二叉树
 * 2^h-1=N N为节点数，h为树的高度
 */
public class IsFullBT {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    static int height=0;
    private void process1(Node head, List list){
        if (head==null){height=0; return;}
        list.add(head);
        if (head.left!=null) {
            list.add(head.left);
            process1(head.left, list);
            list.remove(head.left);
        }
        if (head.right!=null){
            height++;
            list.add(head.right);
            process1(head.right,list);
            list.remove(head.right);
        }

    }
    private boolean isFullBT1(Node head){
        if (head==null)return true;
        List list = new ArrayList();
        process1(head,list);
        if ((2<<(height-1))-1==list.size())return true;
        return false;
    }
    static class Info{
        int height;
        int size;

        public Info(int height, int size) {
            this.height = height;
            this.size = size;
        }
    }

    /**
     * 向左右子树要高度和节点数
     * @param head
     * @return
     */
    private Info process(Node head){
        if (head==null)return new Info(0,0);
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height=Math.max(leftInfo.height,rightInfo.height)+1;
        int size=leftInfo.size+rightInfo.size+1;
        return new Info(height,size);
    }

    /**
     * 2^height-1=size size为节点数，height为树的高度
     * @param head
     * @return
     */
    private boolean isFullBT(Node head){
        Info info = process(head);
        if ((2<<(info.height-1))-1==info.size)return true;
        return false;
    }
    public static void main(String[] args) {
        IsFullBT ft = new IsFullBT();
        Node head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.left.right = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        System.out.println(ft.isFullBT(head));
    }
}
