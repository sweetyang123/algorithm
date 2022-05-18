package com.toface;

import java.util.*;

/**
 * @author tian.yang
 * @description
 * @date 2022/5/18 17:16
 */
public class TopNRepeat {
    private static List<Integer> topN(int[] arr,int n){
        if (arr==null||arr.length==0)return new ArrayList<>(0);
        Map<Integer,Integer> map = new HashMap(arr.length);
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i]))map.put(arr[i],map.get(arr[i])+1);
            else map.put(arr[i],1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(n,new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1)-map.get(o2);
            }
        });
        for (Integer key:map.keySet()) {
            if (queue.size()<n)queue.add(key);
            else if (map.get(queue.peek())<map.get(key)){
                queue.remove();
                queue.add(key);
            }
        }
        List<Integer> res = new ArrayList<>(n);
        while (!queue.isEmpty()){
            res.add(queue.remove());
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arrs= new int[]{2,2,3,1,1,4,5,2,3};
        System.out.println(topN(arrs,3));
    }
}
