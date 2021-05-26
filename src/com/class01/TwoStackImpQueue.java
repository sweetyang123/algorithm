package com.class01;

import java.util.Stack;

public class TwoStackImpQueue {
    public static class StackToQueue{
       public Stack<Integer> pushStack;
       public Stack<Integer> popStack;

        public StackToQueue() {
            this.pushStack = new Stack();
            this.popStack = new Stack();
        }
        public void pushToPop(){
            //popStack为空才能将popStack栈的数据导入到popStack中
            if (popStack.isEmpty()){
                while (!pushStack.isEmpty()){
                    popStack.push(pushStack.pop());
                }
            }
        }
        private void push(int num){
            pushStack.push(num);
            pushToPop();
        }
        private int pop(){
            if (popStack.isEmpty()&&pushStack.isEmpty()){
                throw new RuntimeException("队列为空");
            }
            pushToPop();
            return popStack.pop();
        }
        private int peek(){
            if (popStack.isEmpty()&&pushStack.isEmpty()){
                throw new RuntimeException("队列为空");
            }
            pushToPop();
            return popStack.peek();
        }
        public static void main(String[] args) {
            StackToQueue test = new StackToQueue();
            test.push(1);
            test.push(2);
            test.push(3);
           // System.out.println(test.peek());
            System.out.println(test.pop());
            //System.out.println(test.peek());
            System.out.println(test.pop());
            test.push(4);
            test.push(5);
            test.push(6);
           // System.out.println(test.peek());
            System.out.println(test.pop());
            System.out.println(test.pop());
            System.out.println(test.pop());
        }
    }
}
