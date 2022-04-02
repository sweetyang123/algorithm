package com.class09linkedlist;

import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * 是否是回文
 * 笔试直接栈实现
 *
 */
public class IsPalindromeList {
   public  static class Node{
       int value;
       Node next;

       public Node(int value) {
           this.value = value;
       }
   }

    /**
     * 栈实现，先全部入栈，再出栈，并与链表想比较
     * @param head
     * @return
     * n extra space
     */
    private static boolean stackImpl(Node head){
       if (head==null||head.next==null) return true;
        Stack<Node> stack = new Stack();
        Node cur = head;
        while (cur!=null){
           stack.push(cur);
            cur=cur.next;
       }
        while (head!=null){
            if (head.value!=stack.pop().value)return false;
            head = head.next;
        }
        return true;
    }

    /**
     * 入一半到栈中，再将与之比较
     * @param head
     * @return
     * n/2 extra space
     */
    private  static  boolean halfStack(Node head){
        if (head==null||head.next==null)return true;
        Node cur = head.next;
        Node fast = head;
        while (fast.next!=null&&fast.next.next!=null){
            cur=cur.next;
            fast=fast.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (cur!=null){
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()){
            if (head.value!=stack.pop().value)return false;
            head = head.next;
        }
        return true;
    }

    /**
     * 将一半后面的值倒序，再从头与倒序的头（也就是尾）一一比较
     * @param head
     * @return
     * O(1) extra space
     */

    private  static boolean revesseIsPalind(Node head){
        if (head==null||head.next==null)return true;
        Node cur = head.next;
        Node fast = head;
        while (fast.next!=null&&fast.next.next!=null){
            cur=cur.next;
            fast=fast.next.next;
        }
        Node temp = cur;
        Node pre = null;
        Node next = null;
        //中间后面的倒序
        while (temp!=null){
            next = temp.next;
            temp.next=pre;
            pre=temp;
            temp=next;
        }
        cur.next=null;
        //将倒序的头与原来的头一一比较
        while (pre!=null&&head!=null){
            if (pre.value!=head.value)return false;
            pre=pre.next;
            head=head.next;
        }
        return true;
    }
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix==null||matrix.length==0)return false;
        int j=matrix[0].length-1;
        int i=0;
        while(i<matrix.length&&j>=0){
            // 从左下角开始
            if(matrix[i][j]==target){
                return true;
                // 小于则向右走，i++
            }else if(matrix[i][j]<target){
                i++;
                // 大于则向上走，j--
            }else if(matrix[i][j]>target){
                j--;
            }
        }
        return false;
    }
    public static void main(String[] args) {

        Node head = new Node(1);
        head.next= new Node(2);
        head.next.next= new Node(2);
        head.next.next.next= new Node(1);
//        head.next.next.next.next= new Node(1);
//        head.next.next.next.next.next= new Node(1);
        System.out.println(stackImpl(head));
        System.out.println(halfStack(head));
        System.out.println(revesseIsPalind(head));
    }
}
