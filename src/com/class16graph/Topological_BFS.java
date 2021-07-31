package com.class16graph;


import java.util.*;

/**
 * 拓扑排序-宽度实现
 */
public class Topological_BFS {
    /**
     * 先找到入度为0的节点进入队列，
     * 相邻节点的入度分别减1(存放在map中)，
     * 然后再将相邻节点中入度为0的节点入队，
     * 直到队列为空为止
     * @param graph
     * @return
     */
    private static List topologic(Graph graph){
        if(graph==null)return null;
        HashMap<Node,Integer> indegreeMap = new HashMap();
        Queue<Node> zeroQueue = new LinkedList<>();
        for (Node node: graph.nodes.values()) {
            indegreeMap.put(node,node.in);
            if(node.in==0)zeroQueue.add(node);
        }
        List<Node> res= new ArrayList<>();

        while (!zeroQueue.isEmpty()){
            Node cur = zeroQueue.poll();
            res.add(cur);
            System.out.println(cur.value);
            for (Node next: cur.nexts) {
                indegreeMap.put(next,indegreeMap.get(next)-1);
                if (indegreeMap.get(next)==0)zeroQueue.add(next);
//                next.in--;
//                if (next.in==0)zeroQueue.add(next);
            }
        }
        return res;
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
        topologic(graph);
    }
}
