package com.class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MyHeap1 {
    public static class MyHeap<T> {
        private ArrayList<T> heap;
        private Comparator<? super T> comparator;
        private HashMap<T, Integer> indexMap;
        private int heapsize;

        public MyHeap(Comparator<? super T> com) {
            heap = new ArrayList();
            indexMap = new HashMap();
            comparator = com;
            heapsize = 0;
        }

        public boolean isEmpty() {
            return heapsize == 0;
        }

        public boolean containKey(T key) {
            return indexMap.containsKey(key);
        }

        private void push(T value) {
            heap.add(value);
            indexMap.put(value, heapsize);
            insertHeap(heapsize++);
        }

        private void insertHeap(int index) {
            //向上看，当某节点的父节点小于该节点时，互相交换值
            while (comparator.compare(heap.get((index - 1) / 2), heap.get(index)) < 0) {
                sweep(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private T pop() {
            if (heapsize == 0) throw new RuntimeException("heap is null");
            T value = heap.get(0);
            indexMap.remove(value);
            //将第一个值与最后一个值交换
            sweep(0, heapsize - 1);
            heap.remove(heapsize - 1);
            //整理完全二叉树，使其变为小根堆
            heapify(0, --heapsize);
            return value;
        }

        //向下看，左右子树中最小值是否小于当前值
        private void heapify(int index, int heapsize) {
            int left = index * 2 + 1;
            //左子树下标小于最小下标，则说明i节点有左儿子
            while (left < heapsize) {
                //取左右子树中值最小的下标
                int minest = left + 1 < heapsize && comparator.compare(heap.get(left), heap.get(left + 1)) < 0 ?
                        left + 1 : left;
                //子树中最小值与节点值比较，节点值小则交换值，下沉
                minest = comparator.compare(heap.get(minest), heap.get(index)) < 0 ? index : minest;
                //子节点最小值就是当前节点则跳出程序
                if (index == minest) break;
                sweep(index, minest);
                index = minest;
                left = index * 2 + 1;
            }
        }

        private void sweep(int j, int i) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            heap.set(j, o1);
            heap.set(i, o2);
            //索引表中的值也要换位置
            indexMap.put(o2, i);
            indexMap.put(o1, j);
        }

        /**
         * 修改堆中某个值
         * 不管是向上还是向下总会中一个，时间复杂度为O（logN）
         *
         * @param value
         */
        public void resign(T value) {
            //根据需要修改的值在索引map里找到索引位置
            int index = indexMap.get(value);
            //向上使其有序
            insertHeap(index);
            //向下使其有序
            heapify(index, heapsize);
        }

        public static void main(String[] args) {
            MyHeap<Integer> myHeap = new MyHeap<>(new HeapComparator());
            myHeap.push(3);
            myHeap.push(7);
            myHeap.push(6);
            myHeap.push(2);
            myHeap.push(9);
            while (myHeap.heapsize > 0) {
                System.out.println(myHeap.pop());
            }
        }
    }

    public static class HeapComparator implements Comparator<Integer> {
        //返回负数时，o1在上面，大根堆
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
}
