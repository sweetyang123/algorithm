package com.class01;

import java.util.LinkedList;
import java.util.Stack;

public class TwoStackImpQueue {
    public static class StackToQueue {
        public Stack<Integer> pushStack;
        public Stack<Integer> popStack;

        /**
         *
         */
        public StackToQueue() {
            this.pushStack = new Stack();
            this.popStack = new Stack();
        }

        public void pushToPop() {
            //popStack为空才能将pushStack栈的数据导入到popStack中
            if (popStack.isEmpty()) {
                //当pop栈为空时，将push栈中的数据全部出栈到pop栈中
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }

        /**
         * 每次入栈到push栈中，
         * 当pop栈中无数据时将push栈的数据再出栈到pop栈中，
         * 否则不做操作，等他在push栈中
         */
        private void push(int num) {
            pushStack.push(num);
            pushToPop();
        }

        /**
         * 每次出栈从pop栈中出，
         * 出之前如果pop栈无数据则将push栈的数据放到pop栈中，
         * 否则不做操作，，直接从pop栈中获取
         */
        private int pop() {
            if (popStack.isEmpty() && pushStack.isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            pushToPop();
            return popStack.pop();
        }

        private int peek() {
            if (popStack.isEmpty() && pushStack.isEmpty()) {
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
