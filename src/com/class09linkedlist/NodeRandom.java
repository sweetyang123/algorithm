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
        if (head==null)return null;
        Node cur = head;
        Node next;
        //①->①‘->②->②’。。。。
        while (cur!=null){
            next=cur.next;
            cur.next=new Node(cur.value);
            cur.next.next=next;
            cur=next;
        }
        cur=head;
        //找到复制节点的random指向
        while (cur!=null){
            cur.next.random=cur.random==null?null:cur.random.next;
            cur=cur.next;
        }
        cur=head;
        Node head1=cur.next;
//        只有一个节点时直接返回
        if(head1.next==null)return head1;
        while (head1.next.next!=null){
            cur=cur.next.next;
            head1=head1.next.next;
        }
        return head1;
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next= new Node(2);
        head.next.next= new Node(5);
        head.next.next.next= new Node(3);
        head.next.next.next.next= new Node(7);
        head.random=head.next.next;
        head.next.random= null;
        head.next.next.random= head;
        head.next.next.next.random= head.next;
        head.next.next.next.next.random= head.next.next;
        Node node01=copyRandomNode_01(head);
        Node node02=copyRandomNode_02(head);
        boolean flag=true;
        while (node01!=null){
            if (node01.value!=node02.value||node01.random.value!=node02.random.value){
                flag=false;
            }
            node01=node01.next;
            node02=node02.next;
        }
        if (flag) System.out.println("success");
    }
}
