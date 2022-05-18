package com.class16graph;

/**
 * //从哪个节点到哪个节点，权重是多少
 */
public class Edge {
    public Node from;
    public Node to;
    public int weight;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
