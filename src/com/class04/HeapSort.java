package com.class04;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 堆结构的push，pop
 */
public class HeapSort {
        private int[] heap;
        private final int limit;
        private int heapsize;
        public HeapSort(int limit) {
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
        public  void sort(int[] arr){
            if (arr==null||arr.length<2)return ;
//            入堆，保证根为最大值
//            for (int i = 0; i <arr.length ; i++) {//O(N)
//                insertHeap(arr,i);//O(logN)
//            }
            for (int i = arr.length-1; i >=0 ; i--) {//O(N)
                heapify(arr,i,arr.length);
            }
            int heapSize=arr.length;
            sweep(arr, 0, --heapSize);

            //每次将最大值放到最后，使其有序
            while (heapSize>0) {//O(N)
                heapify(arr,0,heapSize);//O(logN)
                sweep(arr,0,--heapSize);//O(1)
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
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
        public static void main(String[] args) {
          int[] arr=  new int[]{4,6,7,8,3,2,9,1};
            new HeapSort(arr.length).sort(arr);
        }
    }


