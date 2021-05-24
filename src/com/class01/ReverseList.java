package com.class01;

public class ReverseList {
     class Node{
        public int value;
        public Node next;

         public Node(int data) {
             this.value = data;
         }
     }
    class DoubleNode{
        public int value;
        public DoubleNode next;
        public DoubleNode last;

        public DoubleNode(int data) {
            this.value = data;
        }
    }
    //单向链表头尾反转
    public static Node reverseList(Node head){
         Node pre=null;
         Node next=null;
         while (head!=null){
             next=head.next;
             head.next=pre;
             pre=head;
             head=next;
         }
         return pre;
    }
    //双向链表头尾反转
    public static DoubleNode reverseDoubleList(DoubleNode head){
        DoubleNode pre=null;
        DoubleNode next=null;
        while (head!=null){
            next=head.next;
            head.next=pre;

            head.last=pre;

            pre=head;
            head=next;
        }
        return pre;
    }
}
