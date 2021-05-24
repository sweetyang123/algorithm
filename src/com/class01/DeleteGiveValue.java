package com.class01;

public class DeleteGiveValue {
    public static class Node{
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    //移除链表中某个特定的数
    public static Node removeValue(Node head,int num){
        //第一个位置为该数时
        while (head!=null){
            if (head.value!=num)break;
            head=head.next;
        }
        Node pre=head;
        Node cur=head;
        while (cur!=null){
            if (cur.value==num){
                pre.next=cur.next;
            }else {
                pre=cur;
            }
            cur=cur.next;
        }
        return head;
    }

}
