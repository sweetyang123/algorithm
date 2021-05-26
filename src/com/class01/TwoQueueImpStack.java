package com.class01;
import	java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class TwoQueueImpStack {
    public static class QueueToStack<T>{
        public Queue<T> queue;
        public Queue<T> help;

        public QueueToStack() {
            this.queue=new LinkedList<T>();
            this.help=new LinkedList<T>();
        }
        private void push(T num){
            queue.offer(num);
        }
        private T pop(){
           while (queue.size()>1){
               help.offer(queue.poll());
           }
          T value= queue.poll();
           Queue<T> temp = queue;
           queue=help;
           help=temp;
           return value;
        }
        private T peek(){
            while (queue.size()>1){
                help.offer(queue.poll());
            }
            T value= queue.poll();
            //将要弹出的数据压入栈顶
            help.offer(value);
            Queue<T> temp = queue;
            queue=help;
            help=temp;
            return value;
        }
        public boolean isEmpty() {
            return queue.isEmpty();
        }
        public static void main(String[] args) {
            System.out.println("test begin");
            QueueToStack<Integer> myStack = new QueueToStack<>();
            myStack.push(1);
            myStack.push(2);
            myStack.push(3);
            myStack.push(6);
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
            System.out.println(myStack.peek());


            System.out.println("test finish!");

        }
    }
}
