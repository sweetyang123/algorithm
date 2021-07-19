package com.class10binarytree;

/**
 * Class Node {
 * 	V value;
 * 	Node left;
 * 	Node right;
 * 	Node parent;
 * }
 * 给你二叉树中的某个节点，返回该节点的后继节点(中序遍历中后一个节点)
 * 1、可先找到头节点，再中序遍历找到后继节点，时间复杂度为O（N）
 *
 */
public class SuccessorNode {
    static class Node{
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 求某节点的后继节点
     * @param node
     * @return
     */
    private Node processSuccessor(Node node){
        if (node==null)return node;
        //有右孩子时，返回右孩子的最左节点
        if (node.right!=null){
              return  getMaxLeftNode(node.right);
        }else {
            //无右孩子时，向上找当前节点是否是右孩子，直到不是或者父节点为空结束(往上找某节点是其父节点的左子树的节点)
            Node parent = node.parent;
            while (parent!=null&&node==parent.right){
                node=parent;
                parent=node.parent;
            }
            return parent;
        }
    }

    /**
     * 求某节点的前驱节点
     * @param node
     * @return
     */
    private Node processPrecursor(Node node) {
        if (node==null)return node;
        //当有左子树时，则为左子树的最右节点
        if (node.left!=null){
            return getMaxRightNode(node.left);
        }else {
            //当右子树时，则为头节点
            return node.parent;
        }
    }
    private Node getMaxRightNode(Node node) {
        if (node==null)return node;
        while (node.right!=null){
            node= node.right;
        }
        return node;
    }
    private Node getMaxLeftNode(Node node) {
        if (node==null)return node;
        while (node.left!=null){
            node= node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        Node node  = new Node(1);
        node.left=new Node(2);
        node.left.parent=node;
        node.right=new Node(3);
        node.right.parent=node;
        node.left.left=new Node(4);
        node.left.left.parent=node.left;
        node.left.right=new Node(5);
        node.left.right.parent=node.left;
        node.right.left=new Node(6);
        node.right.left.parent=node.right;
        node.right.right=new Node(7);
        node.right.right.parent=node.right;
        SuccessorNode node1 = new SuccessorNode();
        System.out.println(node1.processSuccessor(node.left.right).value);
        System.out.println(node1.processPrecursor(node.left.right).value);

    }
}
