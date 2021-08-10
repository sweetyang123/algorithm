package com.class17violenceRecur;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，
 * 不能申请额外的数据结构，
 * 只能使用递归函数。 如何实现?
 */
public class ReverseStack {
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = reverseStack(stack);
        reverse(stack);
        stack.push(i);
    }
    /**
     * 栈底元素移除掉
     * 上面的元素盖下来
     * 返回移除掉的栈底元素
     * @param stack
     * @return
     */
    private static int reverseStack(Stack<Integer> stack){
        int cur = stack.pop();
        if (stack.isEmpty()){
            return cur;
        }else {
            int last = reverseStack(stack);
            stack.push(cur);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reverse(stack);
        while (!stack.empty()){
            System.out.println(stack.pop());
        }
    }
}
