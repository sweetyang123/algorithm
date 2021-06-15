package com.class04;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 堆结构的push，pop
 */
public class Heap {
    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapsize;
        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapsize = 0;
        }
        public boolean isEmpty() {
            return heapsize == 0;
        }

        public boolean isFull() {
            return heapsize == limit;
        }
        private void push(int value) {
            if (heapsize == limit) throw new RuntimeException("heap is full");
            heap[heapsize] = value;
            insertHeap(heap, heapsize++);
        }

        private int pop() {
            if (heapsize == 0) throw new RuntimeException("heap is null");
            int value = heap[0];
            //将第一个值与最后一个值交换
            sweep(heap, 0, --heapsize);
            //整理完全二叉树，使其变为大根堆
            heapify(heap, 0, heapsize);
            return value;
        }

        //向下看，左右子树中最大值是否大于当前值
        private void heapify(int[] heap, int index, int heapsize) {
            int left = index * 2 + 1;
            //左子树下标小于最大下标，则说明i节点有左儿子
            while (left < heapsize) {
                //取左右子树中值最大的下标
                int largest = left + 1 < heapsize && heap[left + 1] > heap[left] ? left + 1 : left;
                //子树中最大值与节点值比较，节点值小则交换值，下沉
                largest = heap[largest] > heap[index] ? largest : index;
                //子节点最大值就是当前节点则跳出程序
                if (index == largest) break;
                sweep(heap, index, largest);
                index = largest;
                left = index * 2 + 1;
            }
        }

        private void insertHeap(int[] heap, int index) {
            //向上看，当某节点的父节点小于该节点时，互相交换值
            while (heap[(index - 1) / 2] < heap[index]) {
                sweep(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void sweep(int[] heap, int index, int i) {
            int temp = heap[index];
            heap[index] = heap[i];
            heap[i] = temp;
        }
    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    public static void main(String[] args) {
//        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        //  5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while(!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

//        MyMaxHeap heap1 = new MyMaxHeap(10);
//        heap1.push(4);
//        heap1.push(6);
//        heap1.push(7);
//        heap1.push(8);
//        heap1.push(3);
//        heap1.push(2);
//        heap1.push(9);
//        while (heap1.heapsize>0){
//            System.out.println(heap1.pop());
//        }

        int value = 1000;
        int limit = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}
