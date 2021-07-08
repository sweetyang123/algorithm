package com.class10binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化二叉树
 * 能完整模拟出一颗二叉树的结构：
 * 每个节点都要有左右子树，没有值的用null代替
 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
 *       因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 *       比如如下两棵树
 *               __2
 *              /
 *             1
 *             和
 *             1__
 *                \
 *                 2
 *       补足空位置的中序遍历结果都是{ null, 1, null,null,null,2, null}
 */
public class SerializeBT {
    private static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    private Queue preSerializable(Node head){
        Queue<String> ans = new LinkedList<>();
        return serializablePre(head,ans);
    }
    private Queue serializablePre(Node head,Queue<String> ans){
        if (head==null)ans.add(null);
        else{
            ans.add(head.value+"");
            serializablePre(head.left,ans);
            serializablePre(head.right,ans);
        }
        return ans;
    }
    private Queue postSerializable(Node head){
        Queue<String> ans = new LinkedList<>();
        return serializablePost(head,ans);
    }

    /**
     * 先序遍历的反序列化
     *
     * @param ans
     * @return
     */
    private  Node deSerializiablePre(Queue<String> ans){
        String poll = ans.poll();
        if (poll==null)return null;
            Node head=new Node(Integer.valueOf(poll));
            head.left=deSerializiablePre(ans);
            head.right= deSerializiablePre(ans);
            return head;
    }
//    后序遍历的序列化
    private Queue serializablePost(Node head,Queue<String> ans){
        if (head==null)ans.add(null);
        else{
            serializablePost(head.left,ans);
            serializablePost(head.right,ans);
            ans.add(head.value+"");
        }
        return ans;
    }
//    按宽度遍历的序列化
    private Queue serializableLevel(Node head){
        Queue<String> ans = new LinkedList<>();
        if (head==null){ans.add(null);}
        else {
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            ans.add(head.value+"");
            while (!queue.isEmpty()){
                Node cur=queue.poll();
                if (cur.left==null){
                    ans.add(null);
                }else {
                    queue.add(cur.left);
                    ans.add(cur.left.value+"");
                }
                if (cur.right==null){
                    ans.add(null);
                }else {
                    queue.add(cur.right);
                    ans.add(cur.right.value+"");
                }
            }
        }
        return ans;
    }

    /**
     * 按层反序列化
     * 加一个队列辅助完成
     * @param ans
     * @return
     */
    private Node deSerializableLevel(Queue<String> ans){
        if (ans==null||ans.size()==0)return null;
        Queue<Node> queue = new LinkedList<>();
        Node head=generateNode(ans.poll());
        if (head!=null)queue.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            cur.left=generateNode(ans.poll());
            cur.right=generateNode(ans.poll());
            if (cur.left!=null)queue.add(cur.left);
            if (cur.right!=null)queue.add(cur.right);
        }
        return head;
    }

    private Node generateNode(String poll) {
        if (poll==null)return null;
        return new Node(Integer.valueOf(poll));
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left=new Node(2);
        head.right=new Node(3);
        head.left.left=new Node(4);
        SerializeBT ab=new SerializeBT();
        ab.print(ab.serializableLevel(head));
        System.out.println(" ");
        ab.print(ab.preSerializable(head));
        Node node = ab.deSerializiablePre(ab.preSerializable(head));
        System.out.println(11);

    }
    private void print(Queue<String> queue){
        while (!queue.isEmpty()){
            System.out.print(queue.poll()+" ");
        }
    }
}
