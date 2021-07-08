package com.class09linkedlist;

import jdk.internal.org.objectweb.asm.Handle;

import java.util.HashMap;

/**
 * 给定两个可能有环，可能无环的单链表的头节点head1，head2，
 * 查找两个链表是否相交，相交则返回相交的第一个节点，不交则返回null
 * 要求时间复制度O（N），额外空间复杂度O（1）
 * 1、哈希表实现，地址相同则相交
 * 2、看两个链表是否有环（快慢指针）
 * 3、①两个都无环的相交；② 都有环（共用环的情况）
 */

public class FindFirstIntersectNode {
    public static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    //先查看链表是否有环并输出第一个成环节点:哈希表实现，在表里有值了就代表成环了
    private static Node isIntersect_01(Node head){
        Node cur=head;
        HashMap<Node,Node> map = new HashMap<>();
        while (cur!=null){
            if (!map.containsKey(cur)){
                map.put(cur,cur);
            }else return cur;
            cur=cur.next;
        }
        return null;
    }

    /**
     * 查看链表是否有环并输出第一个成环节点:快慢指针实现
     *先当快慢指针第一次相遇时，将慢指针指向头，快指针继续前行，再次相遇时就是相交的第一个节点
     * @param head
     * @return
     */
    private static Node isIntersect_02(Node head){
        if (head==null||head.next==null||head.next.next==null)return null;
        Node slow=head.next;
        Node fast=head.next.next;
        //第一次相遇
        while (slow!=fast){
            if (fast.next==null||fast.next.next==null)return null;
            slow=slow.next;
            fast=fast.next.next;
        }
//        第二次相遇
        slow=head;
        while (slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }

    /**
     * 无环相交
     * 这个相交只有是两个共用一个节点或一段链表，Y字形的
     * 则可以计算出两个链表相差节点的长度，然后让多的一个链表先走相差数，
     * 第一次相遇的节点就是第一个相交节点
     */
    private static Node acyliIntersect(Node head1,Node head2){
        Node cur1=head1;
        Node cur2=head2;
        int count=0;
        while (cur1!=null){
            count++;
            cur1=cur1.next;
        }
        while (cur2!=null){
            count--;
            cur2=cur2.next;
        }
        cur1=count>0?head1:head2;
        cur2=cur1==head1?head2:head1;
        count = Math.abs(count);
        while (count-->0){
            cur1=cur1.next;
        }
//        cur1=head1;
//        cur2=head2;
//        if (count >= 0) {
//            while (count-->0){
//                cur1=cur1.next;
//            }
//        }else {
//            count = Math.abs(count);
//            while (count-->0){
//                cur2=cur2.next;
//            }
//        }
        while (cur1!=null){
            if (cur1.value==cur2.value)return cur1;
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return null;
    }
    /**
     * 有环相交（都有环）
     */
    public static Node cyliIntersect(Node head1,Node head2,Node loop1,Node loop2) {
        Node cur1=head1;
        Node cur2=head2;
        //相交节点相同，则是Y字形共用节点共用环相交
        if (loop1==loop2){
            int count=0;
            while (cur1!=loop1){
                count++;
                cur1=cur1.next;
            }
            while (cur2!=loop2){
                count--;
                cur2=cur2.next;
            }
            cur1=count>0?head1:head2;
            cur2=cur1==head1?head2:head1;
            count=Math.abs(count);
            while (count-->0){
                cur1=cur1.next;
            }
            while (cur1!=null){
                if (cur1.value==cur2.value)return cur1;
                cur1=cur1.next;
                cur2=cur2.next;
            }
        }else {//共用一个环加两横的形式
            cur1=loop1.next;
            while (cur1!=loop1){
                if (cur1==loop2)return loop2;
                cur1=cur1.next;
            }
        }
          return null;
    }
    private static Node firstIntersect(Node head1,Node head2){
        if (head1==null||head2 ==null)return null;
        Node loop1 = isIntersect_02(head1);
        Node loop2 = isIntersect_02(head2);
        if (loop1==null&&loop2==null){return acyliIntersect(head1,head2);}
        else if (loop1!=null&&loop2!=null){return cyliIntersect(head1,head2,loop1,loop2);}
        return null;
    }
    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(firstIntersect(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(firstIntersect(head1, head2).value);
//
//        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(firstIntersect(head1, head2).value);
    }
}
