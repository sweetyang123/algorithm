package com.class10binarytree;

/**
 * 判断一颗二叉树是否是平衡二叉树
 * 平衡性：左右子树都要是平衡的，左右子树高度相差不大于1
 */
public class IsBalanced {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static class Info {
        boolean isBst;
        int height;

        public Info(boolean isBst, int height) {
            this.isBst = isBst;
            this.height = height;
        }
    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private boolean isBalance(Node head) {
        return process(head).isBst;
    }

    private Info process(Node X) {
        if (X == null) return new Info(true, 0);
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);
        //主要是组装info信息，高度为左右子树的最大高度加本身，
        // 是否是平衡的需要分别判断左右子树是否平衡，并且左右子树高度相差是否小于等于1
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBst = true;
//        左边不平衡则不平衡
        if (!leftInfo.isBst) isBst = false;
//        右边不平衡则不平衡
        if (!rightInfo.isBst) isBst = false;
//        高度是否相差<=1
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) isBst = false;
        return new Info(isBst, height);
    }

    //    public static void main(String[] args) {
//        IsBalanced ib = new IsBalanced();
//        Node head = new Node(1);
//        head.left=new Node(2);
//        head.right=new Node(2);
//        head.right.right=new Node(2);
//        head.right.right.left=new Node(2);
//        head.left.left=new Node(6);
//        System.out.println(ib.isBalance(head));
//        System.out.println(ib.isBalanced1(head));
//    }
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

    public static void main(String[] args) {
        IsBalanced is = new IsBalanced();
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (is.isBalance(head) != is.isBalanced1(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
