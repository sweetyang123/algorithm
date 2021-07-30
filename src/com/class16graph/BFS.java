package com.class16graph;

import java.util.*;

/**
 * 图的宽度优先遍历
 *队列加set（保证节点只进入了一次）
 */
public class BFS {
    private static void process(Node satrt){
        if (satrt==null)return;
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(satrt);
        set.add(satrt);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            List<Node> nexts = cur.nexts;
            //循环当前节点的相邻节点
            for (Node node:nexts) {
//                已经加入过了就不管了，加入新节点
                if (!set.contains(node)){
                    set.add(node);
                    queue.add(node);
                }
            }
        }

    }

    public static void main(String[] args) {
        Node node1  = new Node(1);
        Node node2  = new Node(2);
        Node node3  = new Node(3);
        Node node4  = new Node(4);
        Node node5  = new Node(5);
        Graph graph = new Graph();
        Edge edge1 = new Edge(node1,node2,0);
        Edge edge2 = new Edge(node1,node3,0);
        Edge edge3 = new Edge(node2,node3,0);
        Edge edge4 = new Edge(node2,node5,0);
        Edge edge5 = new Edge(node3,node4,0);
        Edge edge6 = new Edge(node3,node5,0);
        node1.nexts.add(node2);
        node1.nexts.add(node3);
        node2.nexts.add(node1);
        node2.nexts.add(node3);
        node2.nexts.add(node5);
        node3.nexts.add(node1);
        node3.nexts.add(node2);
        node3.nexts.add(node4);
        node3.nexts.add(node5);
        node4.nexts.add(node3);
        node5.nexts.add(node2);
        node5.nexts.add(node3);
        graph.edges.add(edge1);
        graph.edges.add(edge2);
        graph.edges.add(edge3);
        graph.edges.add(edge4);
        graph.edges.add(edge5);
        graph.edges.add(edge6);
        graph.nodes.put(1,node1);
        graph.nodes.put(2,node2);
        graph.nodes.put(3,node3);
        graph.nodes.put(4,node4);
        graph.nodes.put(5,node5);
        process(node1);
    }
}
