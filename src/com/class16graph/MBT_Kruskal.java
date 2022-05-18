package com.class16graph;

import java.util.*;

/**
 * 最小生成树---Kruskal
 * 小根堆（先用最小边）+并查集
 * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2）当前的边要么进入最小生成树的集合，要么丢弃
 * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5）考察完所有边之后，最小生成树的集合也得到了
 */
public class MBT_Kruskal {
    public static List<Edge> getMBT(Graph graph) {
        PriorityQueue<Edge> queue = new PriorityQueue(new MyComparator());
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }
        UnionFind uf = new UnionFind();
        uf.makeSet(graph.nodes.values());
        List<Edge> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            //当该边的两个点不是同一个集合（不在我们要生成的最小树中），
//            则合并两个点为一个集合，并在结果集里加上这条边
            if (!uf.isSameSet(cur.from, cur.to)) {
                res.add(cur);
                uf.union(cur.from, cur.to);
            }
        }
        return res;
    }

    static class MyComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    static class UnionFind {
        HashMap<Node, Node> fatherMap;
        HashMap<Node, Integer> sizeMap;

        public void makeSet(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public boolean isSameSet(Node node1, Node node2) {
            return findFather(node1) == findFather(node1);
        }

        public void union(Node node1, Node node2) {
            if (node1 == null || node2 == null) return;
            Node a = findFather(node1);
            Node b = findFather(node2);
            if (a != b) {
                Integer aSize = sizeMap.get(a);
                Integer bSize = sizeMap.get(b);
                Node small = aSize >= bSize ? b : a;
                Node big = small == b ? a : b;
                fatherMap.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }

        private Node findFather(Node node) {
            Stack<Node> stack = new Stack();
//            找到node的所有祖先节点
            while (node != fatherMap.get(node)) {
                stack.push(node);
                node = fatherMap.get(node);
            }
            //并行，使最上面的点作为他每个后代的父节点
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), node);
            }
            return node;
        }
    }
}
