package com.class04;

import java.util.Arrays;
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

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
//            入堆，保证根为最大值
//            for (int i = 0; i <arr.length ; i++) {//O(N)
//                insertHeap(arr,i);//O(logN)
//            }
        for (int i = arr.length - 1; i >= 0; i--) {//O(N)
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        sweep(arr, 0, --heapSize);

        //每次将最大值放到最后，使其有序
        while (heapSize > 0) {//O(N)
            heapify(arr, 0, heapSize);//O(logN)
            sweep(arr, 0, --heapSize);//O(1)
        }

    }

    /**
     * 已知一个几乎有序的数组，几乎有序是指，如果把数组拍好顺序的话，
     * 每个元素移动的距离 一定不超过k，并且k相对于数组长度来说是比较小的，
     * 请选择一个合适的排序策略，对数组进行排序
     *
     * @param arr
     * @param k
     */
    public static void sort(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 0...K-1放入小根堆
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        //k到数组长度一个一个的放入堆，堆一个一个的弹出最小值，使堆中一直只有k+1个数
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    //向下看，左右子树中最大值是否大于当前值
    private static void heapify(int[] heap, int index, int heapsize) {
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

    private static void sweep(int[] heap, int index, int i) {
        int temp = heap[index];
        heap[index] = heap[i];
        heap[i] = temp;
    }

    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
//          int[] arr=  new int[]{4,6,7,8,3,2,9,1};
//          int[] arr1=  new int[]{4,6,7,8,3,2,9,1};
//           int[] temp= new HeapSort(arr.length).sort(arr);
//                for (int i = 0; i < temp.length; i++) {
//                    System.out.println(temp[i]);
//                }

        System.out.println("test begin");
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sort(arr1, k);
            comparator(arr2, k);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}


