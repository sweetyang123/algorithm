package com.class01;

public class DeleteGiveValue {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //移除链表中某个特定的数
    public static Node removeValue(Node head, int num) {
        //第一个位置为该数时
        while (head != null) {
            if (head.value != num) break;
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static int[] removeValue(Node head) {
//        if(head==null)return new int[]{};
        Node pre = null;
        Node next = null;
        int count = 0;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            count++;
        }

        int[] res = new int[count];
        for (int i = 0; i < count; i++) {
            res[i] = pre.value;
            pre = pre.next;
        }
        return res;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(3);
        head.next.next = new Node(2);
        removeValue(head);
    }
}
