package com.class10binarytree;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * 按层遍历，就是宽度优先遍历
 * 获取最大节点数
 */
public class LevelTraversal {
    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 队列辅助按层遍历
     *
     * @param head
     */
    private void level(Node head) {
        if (head != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(head);
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                System.out.print(cur.value + " ");
//                队列固定大小时用offer比add更好
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) queue.add(cur.right);
            }
        }
        System.out.println(" ");
    }

    /**
     * 找出二叉树中宽度最大的层
     * 队列加哈希表
     * 哈希表记录每个节点在哪一层
     */
    private int MaxNodesWithLevel(Node head) {
        int max = 0;
        if (head != null) {
            Map<Node, Integer> curMap = new HashMap<>();
            int curLevel = 1;
            int levelNodes = 0;
            Queue<Node> queue = new LinkedList<>();
            //将头节点入队列
            queue.offer(head);
//            并将头节点所在的层数初始化入哈希表中
            curMap.put(head, curLevel);
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
//                根据节点在哈希表中获取所在层数
                int tempLevel = curMap.get(cur);
//                当前节点的左右子树分别入队，并在哈希表中加入子树所在层数的对应关系
                if (cur.left != null) {
                    queue.add(cur.left);
                    curMap.put(cur.left, tempLevel + 1);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    curMap.put(cur.right, tempLevel + 1);
                }
//                如果当前层数与节点所在层数相等，则节点数+1
                if (tempLevel == curLevel) {
                    levelNodes++;
                } else {
//                    否则结算当前层，将当前层的节点数与之前获得的最大值做比较，取更大值
//                    并将当前层+1，节点数归零
                    max = Math.max(levelNodes, max);
                    curLevel++;
                    levelNodes = 1;
                }
            }
//            最后一层得到了结果但未结算，所以需要最后再结算依次
            max = Math.max(levelNodes, max);
        }
        return max;
    }

    /**
     * 不管在哪一层，只需要拿到当前层的最右节点和下一层的最右节点
     * 只需要一个队列实现
     *
     * @param head
     * @return
     */
    private int MaxNodesQueue(Node head) {
        int max = 0;
        if (head != null) {
            int curLevelNodes = 0;
            Node cur = head;
            Node curEnd = head;//当前层最右节点
            Node nextEnd = null;//下一层最右节点
            Queue<Node> queue = new LinkedList<>();
            queue.add(cur);
            while (!queue.isEmpty()) {
                cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                    nextEnd = cur.left;
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    nextEnd = cur.right;
                }
                curLevelNodes++;
                //如果当前节点为当前层最右节点，则结算当前层
                if (cur == curEnd) {
                    max = Math.max(max, curLevelNodes);
                    curLevelNodes = 0;
                    curEnd = nextEnd;
                }
            }
        }
        return max;
    }

    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        LevelTraversal lt = new LevelTraversal();

        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            lt.levelOrder1(head);
            if (lt.MaxNodesWithLevel(head) != lt.MaxNodesQueue(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) return new ArrayList(0);
        int curLevel = 1;
        Map<Node, Integer> curMap = new HashMap<>();
        curMap.put(root, curLevel);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int tempLevel = curMap.get(cur);
            int size = queue.size();
            if (cur.left != null) {
                queue.add(cur.left);
                curMap.put(cur.left, tempLevel + 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                curMap.put(cur.right, tempLevel + 1);
            }
            if (curLevel == tempLevel) {
                list.add(cur.value);
            } else {
                res.add(list);
                list = new ArrayList<>();
                list.add(cur.value);
                curLevel++;
            }
        }
        if (list.size() > 0) res.add(list);
        return res;
    }

    public List<List<Integer>> levelOrder1(Node root) {
        if (root == null) {
            return new ArrayList(0);
        }
        Node cur = root;
        Queue<Node> queue = new LinkedList<>();
//        queue.
        queue.add(cur);
        LinkedList lis1 = new LinkedList();
        List<List<Integer>> res = new ArrayList<>();

        LinkedList<Integer> list = null;
        while (!queue.isEmpty()) {
            list = new LinkedList();
            for (int i = queue.size(); i > 0; i--) {
                cur = queue.poll();
                if (res.size() % 2 == 0) {
                    list.addLast(cur.value);
                } else {
                    list.addFirst(cur.value);
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            res.add(list);
        }
        return res;
    }


}
