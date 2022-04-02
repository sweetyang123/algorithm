package com.class09linkedlist;

import java.util.HashMap;

/**
 * Node结构中有value，next，random（随意指向某个节点）
 * 给定以Node节点组成的无环链表的头节点head，复制链表并返回头节点
 * 要求时间复杂度为O（N），额外空间复杂度为O（1）
 * 1、哈希表实现
 * 2、一个节点一个节点的复制，如①的next为①‘，①’的next为②
 */

public class NodeRandom {
    public static class Node{
        int value;
        Node next;
        Node random;
        public Node(int value) {
            this.value = value;
        }

    }
    /**
     * 哈希表实现
     * @param head
     * @return
     */
    private static Node copyRandomNode_01(Node head){
        if (head==null)return null;
        Node cur=head;
        HashMap<Node,Node> map = new HashMap();
        //将链表的节点对象和值放到哈希表里
        while (cur!=null){
            map.put(cur,new Node(cur.value) );
            cur= cur.next;
        }
        cur=head;
        //根据节点对象在哈希表中找到next节点和random节点
        while (cur!=null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur= cur.next;
        }
        return map.get(head);
    }
    /**
     * @param head
     * @return
     */
    private static Node copyRandomNode_02(Node head){
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // copy node and link to every node
        // 1 -> 2
        // 1 -> 1' -> 2-> 2'
        while (cur != null) {
            // cur 老 next 老的下一个
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        // set copy node rand
        // 1 -> 1' -> 2 -> 2'
        while (cur != null) {
            // cur 老
            // cur.next 新 copy
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        // head head.next
        cur = head;
        Node res=cur.next;
        // split
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
    public static int search(int[] nums) {
        int n=nums.length-1;
        int i=0;
        while(i<=n){
            if(nums[i]!=i){
                return i;
            }
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] s = new int[]{0};
        System.out.println(search(s));
    }
    public static void main1(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyRandomNode_01(head);
        printRandLinkedList(res1);
        res2 = copyRandomNode_02(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.random = head.next.next.next.next.next; // 1 -> 6
        head.next.random = head.next.next.next.next.next; // 2 -> 6
        head.next.next.random = head.next.next.next.next; // 3 -> 5
        head.next.next.next.random = head.next.next; // 4 -> 3
        head.next.next.next.next.random = null; // 5 -> null
        head.next.next.next.next.next.random= head.next.next.next; // 6 -> 4

        System.out.println("原始链表：");
        printRandLinkedList(head);
        System.out.println("=========================");
        res1 = copyRandomNode_01(head);
        System.out.println("方法一的拷贝链表：");
        printRandLinkedList(res1);
        System.out.println("=========================");
        res2 = copyRandomNode_02(head);
        System.out.println("方法二的拷贝链表：");
        printRandLinkedList(res2);
        System.out.println("=========================");
        System.out.println("经历方法二拷贝之后的原始链表：");
        printRandLinkedList(head);
        System.out.println("=========================");
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.random == null ? "- " : cur.random.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}
