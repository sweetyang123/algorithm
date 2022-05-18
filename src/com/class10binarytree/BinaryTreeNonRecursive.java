package com.class10binarytree;

import java.util.Stack;

/**
 * 二叉树的
 * 先序：头左右；
 * 中序：左头右；
 * 后序：左右头
 * 非递归实现：压栈
 */
public class BinaryTreeNonRecursive {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //先序遍历
    private void pre(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.empty()) {
                //先将栈顶的数压栈，并将其右节点，左节点依次压栈
                Node cur = stack.pop();
                System.out.print(cur.value + " ");
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
            }
        }
        System.out.println(" ");
    }

    /**
     * 中序遍历
     * 先全部左边压栈，为空时弹出；再右边压栈
     */
    private void in(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            Node cur = head;
            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                    System.out.print(cur.value + " ");
                    cur = cur.right;
                }
            }
        }
        System.out.println(" ");

    }

    /**
     * 后序遍历
     * 两个栈
     */

    private void post(Node head) {
        if (head != null) {
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            stack1.push(head);
            while (!stack1.isEmpty()) {
                Node cur = stack1.pop();
                stack2.push(cur);
                if (cur.left != null) {
                    stack1.push(cur.left);
                }
                if (cur.right != null) {
                    stack1.push(cur.right);
                }
            }
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().value + " ");
            }
            System.out.println(" ");
        }
    }

    private void post2(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            //标记栈顶节点
            Node cur = null;
            stack.push(head);
            while (!stack.isEmpty()) {
                cur = stack.peek();
                if (cur.left != null && head != cur.left && head != cur.right) {
                    stack.push(cur.left);
                } else if (cur.right != null && head != cur.right) {
                    stack.push(cur.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = cur;
                }
            }
            System.out.println(" ");
        }
    }

    public static void main(String[] args) {
        BinaryTreeNonRecursive bt = new BinaryTreeNonRecursive();
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
//        head.left.left.left=new Node(8);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        bt.pre(head);
        bt.in(head);
        bt.post(head);
        bt.post2(head);
    }
}
