package com.class01;

public class ReverseList {
    static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    class DoubleNode {
        public int value;
        public DoubleNode next;
        public DoubleNode last;

        public DoubleNode(int data) {
            this.value = data;
        }
    }

    //单向链表头尾反转
    public static Node reverseList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
//             断链和组合
            next = head.next;
            head.next = pre;
//             下一个节点
            pre = head;
            head = next;
        }
        return pre;
    }

    //双向链表头尾反转
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node reverseBetween(Node head, int left, int right) {
        if (head == null || left >= right) return head;
        Node dummyNode = new Node(-1);
        dummyNode.next = head;
        Node pre = dummyNode, rightNode = null, leftNode = null;
//找到开始反转的左边节点与开始反转的节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        leftNode = pre.next;
        rightNode = pre;
//        找到反转的右边节点
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        // 第 3 步：切断出一个子链表（截取链表）
        Node curr = rightNode.next;
        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;
        //        进行反转
        reverseList(leftNode);
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        Node node = reverseBetween(head, 2, 4);
        System.out.println(node);
    }
}
