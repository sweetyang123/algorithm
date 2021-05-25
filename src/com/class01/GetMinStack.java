package com.class01;

import java.util.Stack;

public class GetMinStack {
    /**
     * 数据栈小于最小栈时，两个栈都压入数据，两个栈不等高
     */
    public static class MyStack1{
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public MyStack1() {
            this.dataStack = new Stack<Integer>();
            this.minStack = new Stack<Integer>();
        }

        public void push(int num){
            if (this.dataStack.isEmpty()){
                this.minStack.push(num);
            }else if (num<=getMin()){
                this.minStack.push(num);
            }
            this.dataStack.push(num);
        }
        public int pop(){
            if (this.dataStack.isEmpty()){
                throw new RuntimeException("栈为空");
            }
            int num=this.dataStack.pop();
            if (num==getMin())this.minStack.pop();
            return num;
        }
        public int getMin(){
            if (this.minStack.isEmpty())throw new RuntimeException("栈为空");
            //返回栈顶
            return this.minStack.peek();
        }
    }
    /**
     * 数字大于最小值时，两个栈都压入数据，数据栈压入输入值，最小栈压入其栈顶值，两个栈等高
     */
    public static class MyStack2{
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public MyStack2() {
            this.dataStack = new Stack();
            this.dataStack = new Stack();
        }

        public void push(int num){
            if (this.dataStack.isEmpty()){
                this.minStack.push(num);
            }else if (num<getMin()){
                this.minStack.push(num);
            }else {
                this.minStack.push(getMin());
            }
            this.dataStack.push(num);
        }
        public int pop(){
            if (this.dataStack.isEmpty()){
                throw new RuntimeException("栈为空");
            }
            this.minStack.pop();
            return this.dataStack.pop();
        }
        public int getMin(){
            if (this.minStack.isEmpty())throw new RuntimeException("栈为空");
            //返回栈顶
            return this.minStack.peek();
        }
    }
    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");

        MyStack1 stack2 = new MyStack1();
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }
}
