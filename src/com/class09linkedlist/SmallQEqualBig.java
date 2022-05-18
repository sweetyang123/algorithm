package com.class09linkedlist;

/**
 * 给定头节点的单向链表，按某值划分成左边小、中间相等、右边大的形式
 * 笔试：①partition方式
 * 面试：②6个常量
 */
public class SmallQEqualBig {
    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private static Node partition01(Node head, int num) {
        if (head == null || head.next == null) return head;
        int i = 0;
        Node cur = head;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodes = new Node[i];
        cur = head;
        for (int j = 0; cur != null; j++) {
            nodes[j] = cur;
            cur = cur.next;
        }
        addPartition(nodes, num);
        for (int j = 0; j < nodes.length; j++) {
            head = nodes[j];
            head = head.next;
        }
//        for (int j = 1; j !=nodes.length ; j++) {
//            nodes[j-1].next=nodes[j];
//        }
//        nodes[i-1].next=null;
//        return nodes[0];
        return head;
    }

    /**
     * 用6个常量解决分别给每个区分配一个head，tail
     *
     * @param head
     * @param num
     * @return
     */
    private static Node samllEqualBig(Node head, int num) {
        if (head == null || head.next == null) return head;
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        Node cur = head;
        Node temp;
        //将大于区，小于区，等于区，区分出来，如sH=1，sT=1->2->3
        while (cur != null) {
            //先将next节点记录下来，避免找不到后续节点
            temp = cur.next;
            cur.next = null;
            if (cur.value < num) {
                if (sH == null) {
                    sH = cur;
                    sT = cur;
                } else {
                    sT.next = cur;
                    sT = cur;
                }
            } else if (cur.value == num) {
                if (eH == null) {
                    eH = cur;
                    eT = cur;
                } else {
                    eT.next = cur;
                    eT = cur;
                }
            } else {
                if (bH == null) {
                    bH = cur;
                    bT = cur;
                } else {
                    bT.next = cur;
                    bT = cur;
                }
            }
            cur = temp;
        }
        //判断每个区是否都有数据
        if (sT != null) {
            sT.next = eH == null ? bH : eH;
        }
        if (eT != null) {
            eT.next = bH == null ? null : bH;
        }
        return sH == null ? (eH == null ? bH : eH) : sH;
    }

    private static void addPartition(Node[] nodes, int num) {
        int l = -1;
        int index = 0;
        int r = nodes.length;
        while (index != r) {
            if (nodes[index].value < num) {
                swap(nodes, ++l, index++);
            } else if (nodes[index].value == num) {
                index++;
            } else {
                swap(nodes, --r, index);
            }
        }
    }

    private static void swap(Node[] nodes, int l, int r) {
        int temp = nodes[l].value;
        nodes[l].value = nodes[r].value;
        nodes[r].value = temp;
    }

    public static void main(String[] args) {
        int testSize = 100;
        int testValue = 100;
        int testTime = 100000;
        boolean flag = true;
        int num = 0;
        for (int i = 0; i < testTime; i++) {
            num++;
            Node node = getRadomLinkedList(testSize, testValue);
            Node node1 = node;
            Node node01 = partition01(node1, num);
            Node node02 = samllEqualBig(node, num);
            while (node01 != null) {
                if (node01.value != node02.value) {
                    flag = false;
                    printN(node);
                    printN(node01);
                    printN(node02);
                }
                node01 = node01.next;
                node02 = node02.next;
            }
            if (num > testValue) num = 0;
        }
        if (flag) System.out.println("success");

    }

    private static Node getRadomLinkedList(int testSize, int testValue) {
        Node head = new Node((int) (Math.random() * testValue));
        Node node = head;
        int size = (int) (Math.random() * testSize) + 1;
        for (int i = 0; i < size; i++) {
            head.next = new Node((int) (Math.random() * testValue));
            head = head.next;
        }
        return node;
    }

    private static void printN(Node node) {
        while (node != null) {
            System.out.print(node.value + " ,");
            node = node.next;
        }
        System.out.println(" ");
    }
}
