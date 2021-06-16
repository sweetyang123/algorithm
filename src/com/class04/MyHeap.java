package com.class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MyHeap<T> {
    private static ArrayList<Integer> heap;
    private final int limit;
    private static HashMap indexMap;
    private  static int heapsize;
    public MyHeap(int limit) {
        heap = new ArrayList();
        indexMap = new HashMap();
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
        heap.add(value);
        indexMap.put(value,heapsize);
        insertHeap(heap, heapsize++);
    }

    private int pop() {
        if (heapsize == 0) throw new RuntimeException("heap is null");
        int value = heap.get(0);
        indexMap.remove(value);
        //将第一个值与最后一个值交换
        sweep( 0, --heapsize);
        //整理完全二叉树，使其变为大根堆
        heapify(heap, 0, heapsize);
        return value;
    }
    //向下看，左右子树中最大值是否大于当前值
    private static void heapify(List<Integer> heap, int index, int heapsize) {
        Comparator com= new Heap.MyComparator();
        int left = index * 2 + 1;
        //左子树下标小于最大下标，则说明i节点有左儿子
        while (left < heapsize) {
            //取左右子树中值最大的下标
            int largest = left + 1 < heapsize && com.compare(heap.get(left + 1),heap.get(left))==1 ?
                    left + 1 : left;
            //子树中最大值与节点值比较，节点值小则交换值，下沉
            largest = heap.get(largest) > heap.get(index) ? largest : index;
            //子节点最大值就是当前节点则跳出程序
            if (index == largest) break;
            sweep(index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private void insertHeap(List heap, int index) {
        Comparator comparator = new HeapComparator();
        //向上看，当某节点的父节点小于该节点时，互相交换值
        while (comparator.compare(heap.get((index - 1) / 2),heap.get(index))==1) {
            sweep(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void sweep(int j, int i) {
        int temp = (int) heap.get(j);
        heap.set(j,heap.get(i));
        heap.set(i,temp);
        //索引表中的值也要换位置
        indexMap.put(i,heap.get(j));
        indexMap.put(j,heap.get(i));
    }
    class  HeapComparator<Integer> implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {

            return ((int)o1-(int)o2);
        }
    }
}
