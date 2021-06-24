package com.class05trie;

import java.util.HashMap;

/**
 * 前缀树，将字符串以前缀树的方式存储，如："abc","cds".....
 */
public class TrieTree02 {
    public static class Node2 {
       public int pass;
       public int end;
       public HashMap<Integer,Node2> next;
        public Node2() {
            this.pass = 0;
            this.end = 0;
            this.next = new HashMap<>();
        }
    }

    public static class Trie01{
        private Node2 root;

        public Trie01() {
            root=new Node2();
        }

        public void insert(String value){
            if (value==null||value.equals(""))return;
            Node2 node = root;
            node.pass++;
            int index=0;
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {//从左往右遍历字符
                index = chars[i];//由字符，对应成走向哪条路
                //如果没有该分支，则新建
                if (!node.next.containsKey(index)){
                    node.next.put(index,new Node2());
                }
                node=node.next.get(index);
                node.pass++;
            }
            node.end++;
        }

        /**
         * 查找以word为前缀的字符串出现了几次
         * @param word
         * @return
         */
        public int prefixNumber(String word){
            if (word==null)return 0;
            Node2 node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (int i = 0; i <chars.length ; i++) {
                index=chars[i];
                //无该分支则返回0
                if (!node.next.containsKey(index))return 0;
                //否则来到下一个节点
                node=node.next.get(index);
            }
            return node.pass;
        }

        /**
         * 查询word在前缀树中存在的次数
         * @param word
         * @return
         */
        private  int search(String word){
            if (word==null) return 0;
            Node2 node=root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (int i = 0; i <chars.length ; i++) {
                index=chars[i];
                if (!node.next.containsKey(index))return 0;
                node=node.next.get(index);
            }
            return node.end;
        }
        private void delete(String word){
           if (search(word)==0) return;
           Node2 node=root;
           char[] chars = word.toCharArray();
           node.pass--;
           int index=0;
            for (int i = 0; i <chars.length ; i++) {
                index=chars[i];
                //如果下一个节点的pass为1则不管后面的节点直接置空返回
                if (--node.next.get(index).pass==0){node.next.remove(index);return;}
                node=node.next.get(index);
            }
            node.end--;
        }


        //test
        private static String[] randomArry(int maxValue,int maxSize) {
            String str = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
            int size=(int) (Math.random() *maxSize+1);
            String[] arr = new String[size];
            for (int i = 0; i < size; i++) {
                int value=(int) (Math.random()*maxValue+1);
                String word = "";
                for (int j = 0; j <value ; j++) {
                    word+=str.charAt((int)(Math.random()*str.length()));
                }
                arr[i]=word;
            }
           return arr;
        }
        private static void print(String[] arr){
            if (arr==null)return;
            for (int i = 0; i <arr.length ; i++) {
                System.out.print(arr[i]+" ");
            }
        }

        private static int search1(String[] arr,String word) {
            if (arr==null||word==null)return 0;
            int count=0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(word))count++;
            }
            return count;
        }
        private static int prefixNumber1(String[] arr,String word) {
            if (arr==null||word==null)return 0;
            int count=0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].startsWith(word))count++;
            }
            return count;
        }
        private static String[] delete(String[] arr,String word) {
            if (arr==null||word==null)return new String[0];
            String[] arr1 = new String[arr.length];
            int index=0;
            for (int i = 0; i < arr.length; i++) {
                if (!arr[i].equals(word)){
                    arr1[index++]=arr[i];
                }
            }
            return arr1;
        }
        public static void main(String[] args) {
            int testtime=10000;
            int maxValue = 100;
            int maxSize = 100;
//            String[] arr= randomArry(maxValue,maxSize);
//            print(arr);
            for (int i = 0; i <testtime ; i++) {
                String[] arr= randomArry(maxValue,maxSize);
                Trie01 trie01 = new Trie01();
                for (int j = 0; j <arr.length ; j++) {
                    trie01.insert(arr[j]);
                }
                for (int j = 0; j <arr.length ; j++) {
                    int end1= trie01.search(arr[j]);
                    int end2= search1(arr,arr[j]);
                    int pass1= trie01.prefixNumber(arr[j]);
                    int pass2= prefixNumber1(arr,arr[j]);
                    if (end1!=end2||pass1!=pass2){
                        System.out.println("第"+j+"位不等");
                        return;
                    }
                }

            }
            System.out.println("succed");
        }


    }
}
