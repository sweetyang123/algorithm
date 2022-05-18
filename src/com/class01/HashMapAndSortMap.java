package com.class01;

import java.util.HashMap;

public class HashMapAndSortMap {
    public static class Node {
        public Integer value;

        public Node(Integer data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {

        HashMap<Node, String> map = new HashMap<>();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        map.put(node1, "我是node1");
        map.put(node2, "我是node2");
        System.out.println(map);
        HashMap<String, String> map1 = new HashMap<>();
        String str = "1";
        String str1 = "2";
        map1.put(str, "我是node1");
        map1.put(str1, "我是node2");
        System.out.println(map1);
    }
}
