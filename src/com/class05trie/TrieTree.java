package com.class05trie;

/**
 * 前缀树，将字符串以前缀树的方式存储，如："abc","cds".....
 */
public class TrieTree {
    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] next;

        public Node1() {
            this.pass = 0;
            this.end = 0;
            //最多只有26个分支，字母只有26个
            this.next = new Node1[26];
        }
    }

    public static class Trie01 {
        private Node1 root;

        public Trie01() {
            root = new Node1();
        }

        public void insert(String value) {
            if (value == null || value.equals("")) return;
            Node1 node = root;
            node.pass++;
            int index = 0;
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {//从左往右遍历字符
                index = chars[i] - 'a';//由字符，对应成走向哪条路
                //如果没有该分支，则新建
                if (node.next[index] == null) {
                    node.next[index] = new Node1();
                }
                node = node.next[index];
                node.pass++;
            }
            node.end++;
        }

        /**
         * 查找以word为前缀的字符串出现了几次
         *
         * @param word
         * @return
         */
        public int prefixNumber(String word) {
            if (word == null) return 0;
            Node1 node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                //无该分支则返回0
                if (node.next[index] == null) return 0;
                //否则来到下一个节点
                node = node.next[index];
            }
            return node.pass;
        }

        /**
         * 查询word在前缀树中存在的次数
         *
         * @param word
         * @return
         */
        private int search(String word) {
            if (word == null) return 0;
            Node1 node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.next[index] == null) return 0;
                node = node.next[index];
            }
            return node.end;
        }

        private void delete(String word) {
            if (search(word) == 0) return;
            Node1 node = root;
            char[] chars = word.toCharArray();
            node.pass--;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                //如果下一个节点的pass为1则不管后面的节点直接置空返回
                if (--node.next[index].pass == 0) {
                    node.next[index] = null;
                    return;
                }
                node = node.next[index];
            }
            node.end--;
        }


        //test
        private static String[] randomArry(int maxValue, int maxSize) {
            String str = "abcdefghijklmnopqrstuvwxyz";
            int size = (int) (Math.random() * maxSize + 1);
            String[] arr = new String[size];
            for (int i = 0; i < size; i++) {
                int value = (int) (Math.random() * maxValue + 1);
                String word = "";
                for (int j = 0; j < value; j++) {
                    word += str.charAt((int) (Math.random() * str.length()));
                }
                arr[i] = word;
            }
            return arr;
        }

        private static void print(String[] arr) {
            if (arr == null) return;
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
        }

        private static int search1(String[] arr, String word) {
            if (arr == null || word == null) return 0;
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
                if (arr[i].equals(word)) count++;
            }
            return count;
        }

        private static int prefixNumber1(String[] arr, String word) {
            if (arr == null || word == null) return 0;
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].startsWith(word)) count++;
            }
            return count;
        }

        private static String[] delete(String[] arr, String word) {
            if (arr == null || word == null) return new String[0];
            String[] arr1 = new String[arr.length];
            int index = 0;
            for (int i = 0; i < arr.length; i++) {
                if (!arr[i].equals(word)) {
                    arr1[index++] = arr[i];
                }
            }
            return arr1;
        }

        public static void main(String[] args) {
            int testtime = 10000;
            int maxValue = 10;
            int maxSize = 10;
//            String[] arr= randomArry(maxValue,maxSize);
//            print(arr);
            for (int i = 0; i < testtime; i++) {
                String[] arr = randomArry(maxValue, maxSize);
                String[] arr1 = randomArry(maxValue, maxSize);

                Trie01 trie01 = new Trie01();
                for (int j = 0; j < arr.length; j++) {
                    trie01.insert(arr[j]);
                }

                for (int k = 0; k < arr.length; k++) {
                    trie01.delete(arr[k]);
                    arr = delete(arr, arr[k]);
                    for (int j = k + 1; j < arr.length; j++) {
                        int end1 = trie01.search(arr[j]);
                        int end2 = search1(arr, arr[j]);
                        int pass1 = trie01.prefixNumber(arr[j]);
                        int pass2 = prefixNumber1(arr, arr[j]);
                        if (end1 != end2 || pass1 != pass2) {
                            System.out.println("第" + j + "位不等");
                            return;
                        }
                    }
                }

            }
            System.out.println("succed");
        }


    }
}
