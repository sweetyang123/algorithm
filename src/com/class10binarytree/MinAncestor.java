package com.class10binarytree;

/**
 * 给一个头节点，两个未知节点O1，O1，求O1，O2的最小公共祖先
 * ① 哈希表，hashSet
 * ② 最初交汇点，是否发现O1，O2，
 * 在左边发现了则在左边。。。；
 * 都没发现但发现了O1，O2则当前节点为最小祖先
 */
public class MinAncestor {
    static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 先将每个节点的父节点放到哈希表中，在将O1的父节点对应关系装到
     * set中，之后再将O2从set中取，有则是最先交叉点
     * @param head
     * @param O1
     * @param O2
     */
    private void process(Node head,Node O1,Node O2){
        if (head==null)return;

    }
}
