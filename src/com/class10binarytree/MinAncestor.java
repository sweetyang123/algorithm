package com.class10binarytree;

import java.util.*;

/**
 * 给一个头节点，两个未知节点O1，O1，求O1，O2的最小公共祖先
 * ① 哈希表，hashSet
 * ② 最初交汇点，是否发现O1，O2，
 * 在左边发现了则在左边。。。；
 * 都没发现但发现了O1，O2则当前节点为最小祖先
 */
public class MinAncestor {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static class Info {
        Node ancestor;//最初交汇点
        boolean findO1;//是否找到O1
        boolean findO2;//是否找到O2

        public Info(Node ancestor, boolean findO1, boolean findO2) {
            this.ancestor = ancestor;
            this.findO1 = findO1;
            this.findO2 = findO2;
        }
    }

    /**
     * 先将每个节点的父子关系都放到HashMap中，节点为key，父节点为value；
     * 再将O1的所有祖先节点放到HashSet中，之后再查看HashSet中是否有O2或O2的祖先节点
     *
     * @param head
     * @param O1
     * @param O2
     */
    private static Node process(Node head, Node O1, Node O2) {
        if (head == null) return null;
        Map<Node, Node> parentMap = new HashMap<>();
        //key为子节点，value为父节点
        parentMap.put(head, null);
        //将整棵树的父子关系放到parentMap中
        fillParentMap(head, parentMap);
        Node cur = O1;
        Set O1Set = new HashSet();
        O1Set.add(cur);
        // 将O1及O1的所有父放到O1Set中
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            O1Set.add(cur);
        }
        cur = O2;
        //查询O1Set中是否存在O2及O2的祖先，存在则是最小公共祖先，否则不存在
        while (!O1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    private static void fillParentMap(Node head, Map<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    /**
     * @param head
     * @param O1
     * @param O2
     * @return
     */
    private static Info process1(Node head, Node O1, Node O2) {
        if (head == null) return new Info(null, false, false);
        Info leftInfo = process1(head.left, O1, O2);
        Info rightInfo = process1(head.right, O1, O2);
        //当前节点为O1或者左子树中找到了O1或者右子树中找到了O1则说明找到了O1
        boolean findO1 = head == O1 || leftInfo.findO1 || rightInfo.findO1;
        //当前节点为O2或者左子树中找到了O2或者右子树中找到了O2则说明找到了O2
        boolean findO2 = head == O2 || leftInfo.findO2 || rightInfo.findO2;
        Node ancestor = null;
        //左子树存在最小公共祖先
        if (leftInfo.ancestor != null) {
            ancestor = leftInfo.ancestor;
        }
        //右子树存在最小公共祖先
        else if (rightInfo.ancestor != null) {
            ancestor = rightInfo.ancestor;
        }
        //左右子树都没存在最先公共祖先，但分别找到了O1，O2，则当前节点为最小公共祖先
        else {
            if (findO1 && findO2) ancestor = head;
        }
        return new Info(ancestor, findO1, findO2);
    }

    private static Node process2(Node head, Node O1, Node O2) {
        return process1(head, O1, O2).ancestor;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (process(head, o1, o2) != process2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
