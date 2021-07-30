package com.class16graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int value;
    //入度
    public int in;
    //出度
    public int out;
    //相邻节点
    public List<Node> nexts;
    //相邻边
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
