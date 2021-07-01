package com.class09linkedlist;

import java.util.ArrayList;

/**
 * 快，慢指针
 * ①输入链表头结点，奇数长度返回中点，偶数长度返回上中点；
 * ②输入链表头结点，奇数长度返回中点，偶数长度返回下中点；
 * ③输入链表头结点，奇数长度返回中点前一个，偶数长度返回上中点前一个；
 * ④输入链表头结点，奇数长度返回中点前一个，偶数长度返回下中点前一个；
 *
 * 笔试：直接用数组实现
 * 面试：快慢指针
 * */
public class LinkedListMid {
   public static class Node{
        int value;
        Node next;
       public Node(int value) {
           this.value = value;
       }
   }
    private static Node midOrUpMidNode(Node head){
       if (head==null||head.next==null||head.next.next==null)return head;
       Node slow=head.next;
       Node fast=head.next.next;
       while (fast.next!=null&&fast.next.next!=null){
           slow=slow.next;
           fast= fast.next.next;
       }
       return slow;
    }
    private static Node midOrDownMidNode(Node head){
        if (head==null||head.next==null)return head;
        Node slow=head.next;
        Node fast=head.next;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast= fast.next.next;
        }
        return slow;
    }
    private static Node beforeMidOrUpMidNode(Node head){
        if (head==null||head.next==null||head.next.next==null)return head;
        Node slow=head;
        Node fast=head.next.next;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast= fast.next.next;
        }
        return slow;
    }
    private static Node beforeMidOrDownMidNode(Node head){
        if (head==null||head.next==null||head.next.next==null)return head;
        Node slow=head;
        Node fast=head.next;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast= fast.next.next;
        }
        return slow;
    }
    public static void main(String[] args) {
        int testTime = 100000;
        int testSize = 100;
        int testValue=100;
        boolean success =true;
        for (int i = 0; i <testTime ; i++) {
            Node node=getRadomLinkedList(testSize,testValue);
            Node arr1Node = arrrayToimpl_01(node);
            Node sf1Node = midOrUpMidNode(node);
            if (arr1Node.value!=sf1Node.value){
                System.out.print("method1：");
                printN(node);
                System.out.println(arr1Node.value);
                System.out.println(sf1Node.value);
                success=false;
            }
                Node arr2Node = arrrayToimpl_02(node);
                Node sf2Node = midOrDownMidNode(node);
            if (arr2Node.value!=sf2Node.value){
                System.out.print("method2：");

                printN(node);
                System.out.println(arr2Node.value);
                System.out.println(sf2Node.value);
                success=false;
            }
            Node arr3Node = arrrayToimpl_03(node);
            Node sf3Node = beforeMidOrUpMidNode(node);
            if (arr3Node.value!=sf3Node.value){
                System.out.print("method3：");

                printN(node);
                System.out.println(arr3Node.value);
                System.out.println(sf3Node.value);
                success=false;
            }
            Node arr4Node = arrrayToimpl_04(node);
            Node sf4Node = beforeMidOrDownMidNode(node);
            if (arr4Node.value!=sf4Node.value){
                System.out.print("method4：");

                printN(node);
                System.out.println(arr4Node.value);
                System.out.println(sf4Node.value);
                success=false;
            }
        }
        if (success) System.out.println("success");


    }

    private static void printN(Node node) {
       int count = 0;
       while (node!=null){
           System.out.print(node.value+" ,");
           node=node.next;
           count++;
       }
        System.out.println(" ==="+count);
    }
//①输入链表头结点，奇数长度返回中点，偶数长度返回上中点；
    private static Node arrrayToimpl_01(Node head) {
       if (head==null) return null;
        ArrayList<Node> nodes = new ArrayList<>();
       while (head!=null){
           nodes.add(head);
           head=head.next;
       }
       return nodes.get((nodes.size()-1)/2);
    }
//②输入链表头结点，奇数长度返回中点，偶数长度返回下中点；
    private static Node arrrayToimpl_02(Node head) {
        if (head==null) return null;
        ArrayList<Node> nodes = new ArrayList<>();
        while (head!=null){
            nodes.add(head);
            head=head.next;
        }
        return nodes.get((nodes.size())/2);
    }
//③输入链表头结点，奇数长度返回中点前一个，偶数长度返回上中点前一个；
    private static Node arrrayToimpl_03(Node head) {
        if (head==null) return null;
        ArrayList<Node> nodes = new ArrayList<>();
        while (head!=null){
            nodes.add(head);
            head=head.next;
        }
        if (nodes.size()<=3)return nodes.get(0);
        return nodes.get(((nodes.size()-3)/2));
    }
//④输入链表头结点，奇数长度返回中点前一个，偶数长度返回下中点前一个；
    private static Node arrrayToimpl_04(Node head) {
        if (head==null) return null;
        ArrayList<Node> nodes = new ArrayList<>();
        while (head!=null){
            nodes.add(head);
            head=head.next;
        }
        if (nodes.size()<=2)return nodes.get(0);
        return nodes.get((nodes.size()-2)/2);
    }
    private static Node getRadomLinkedList(int testSize, int testValue) {
       Node head=new Node((int)(Math.random()*testValue));
       Node node = head;
       int size=(int)(Math.random()*testSize)+1;
        for (int i = 0; i <size ; i++) {
            head.next=new Node((int)(Math.random()*testValue));
            head=head.next;
        }
       return node;
    }
}
