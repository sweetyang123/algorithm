package com.class01;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 双向链表实现栈、队列
 */
public class LinklistToStackAndQueue {
    public static  class Node<T>{
        public T value;
        public Node<T> next;
        public Node<T> last;

        public Node(T data) {
            this.value = data;
        }

        public static class DoubleToStackAndQueue<T>{
            public Node<T> head;
            public Node<T> tail;
            //从双向链表的头部加入
            public void addFromHead(T value) {
                Node<T> cur=new Node<>(value);
                if (head==null){
                    head=cur;
                    tail=cur;
                }else {
                    cur.next=head;
                    head.last=cur;
                    head=cur;
                }
            }
            //从双向链表的尾部加入
            public void addFromBottom(T value){
                Node<T> cur= new Node<T>(value);
                if (head==null){
                    head=cur;
                    tail=cur;
                }else {
                    cur.last=tail;
                    tail.next=cur;
                    tail=cur;
                }
            }
            //从双向链表的头部弹出
            public T popFromHead(){
                if (head==null)return null;
                Node<T> cur = head;
                if (head==tail){
                    head=null;
                    tail=null;
                }else {
                    head=head.next;
                    head.last=null;
                    cur.next=null;
                }
                return cur.value;
            }
            //从双向链表的尾部弹出
            public T popFromBottom(){
                if (head==null)return null;
                Node<T> cur=tail;
                if (head==tail){
                    head=null;
                    tail=null;
                }else {
                    tail=tail.last;
                    tail.next=null;
                    cur.last=null;
                }
                return cur.value;
            }
        }
        //双向链表实现栈，先进后出，可头部进头部出或尾部进尾部出
        public static class MyStack<T>{
            private DoubleToStackAndQueue<T> stackAndQueue;
            public MyStack() {
                stackAndQueue=new DoubleToStackAndQueue<>();
            }
            public void push(T value){
                stackAndQueue.addFromHead(value);
            }
            public T pop(){
               return stackAndQueue.popFromHead();
            }
        }
        //双向链表实现队列，先进先出 ，可头部进尾部出或尾部进头部出
        public static class MyQueue<T>{
            private DoubleToStackAndQueue<T> stackAndQueue;

            public MyQueue() {
                 stackAndQueue=new DoubleToStackAndQueue<>();
            }
            public void push(T value){
                stackAndQueue.addFromHead(value);
            }
            public T pop(){
              return   stackAndQueue.popFromBottom();
            }
        }

        public static boolean isEqual(Integer o1, Integer o2) {
            if (o1 == null && o2 != null) {
                return false;
            }
            if (o1 != null && o2 == null) {
                return false;
            }
            if (o1 == null && o2 == null) {
                return true;
            }
            return o1.equals(o2);
        }
        public static void main(String[] args) {
            int oneTestDataNum = 100;
            int value = 10000;
            int testTimes = 100000;
            for (int i = 0; i < testTimes; i++) {
                MyStack<Integer> myStack = new MyStack<>();
                MyQueue<Integer> myQueue = new MyQueue<>();
                Stack<Integer> stack = new Stack<>();
                Queue<Integer> queue = new LinkedList<>();
                for (int j = 0; j < oneTestDataNum; j++) {
                    int nums = (int) (Math.random() * value);
                    if (stack.isEmpty()) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (Math.random() < 0.5) {
                            myStack.push(nums);
                            stack.push(nums);
                        } else {
                            if (!isEqual(myStack.pop(), stack.pop())) {
                                System.out.println("oops!");
                            }
                        }
                    }
                    int numq = (int) (Math.random() * value);
                    if (stack.isEmpty()) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (Math.random() < 0.5) {
                            myQueue.push(numq);
                            queue.offer(numq);
                        } else {
                            if (!isEqual(myQueue.pop(), queue.poll())) {
                                System.out.println("oops!");
                            }
                        }
                    }
                }
            }
            System.out.println("finish!");
        }
    }
}
