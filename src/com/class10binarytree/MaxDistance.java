package com.class10binarytree;

public class MaxDistance {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static class Info {
        int maxDistance;
        int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    /**
     * 向子树要信息，一般两种情况：
     * 包括头节点时；不包括头节点时
     *
     * @param head
     * @return
     */
    private Info process(Node head) {
        if (head == null) return new Info(0, 0);
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
//        高度为左子树，右子树中最大高度加本身
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        //这里考虑两种情况，当包括head时，最大距离是左子树+右子树+1；
        // 不包括head时，最大距离就为左子树，右子树中距离最大的一个
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                (leftInfo.height + rightInfo.height + 1));
        return new Info(maxDistance, height);
    }

    private int distance(Node head) {
        return process(head).maxDistance;
    }

    public static void main(String[] args) {
        MaxDistance md = new MaxDistance();
        Node head = new Node(1);
        head.left = new Node(29);
        head.right = new Node(29);
        head.left.left = new Node(86);
        head.left.right = new Node(6);
        head.right.right = new Node(81);
        System.out.println(md.distance(head));
    }
}
