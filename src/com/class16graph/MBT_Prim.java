package com.class16graph;

import java.util.*;

/**
 * 最小生成树---Prim
 * 小根堆（先用最小边）+hashSet（看某节点是否已判断过）
 * 1）可以从任意节点出发来寻找最小生成树
 * 2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
 * 3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
 * 4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
 * 5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
 * 6）当所有点都被选取，最小生成树就得到了
 */
public class MBT_Prim {
    public static List<Edge> getMBT(Graph graph) {
        PriorityQueue<Edge> queue = new PriorityQueue(new MyComparator());
        List<Edge> res = new ArrayList<>();
        HashSet<Node> set = new HashSet<>();
//        可能是森林，多颗最小生成树
        for (Node node : graph.nodes.values()) {
            if (!set.contains(node)) {//任意找一个点
                set.add(node);
//                将有该点的边都放到小根堆里
                for (Edge cur : node.edges) {
                    queue.add(cur);
                }
                while (!queue.isEmpty()) {
//                    拿到该点关联的最小边，相应的另一个点是否已存在
                    Edge poll = queue.poll();
                    Node toNode = poll.to;
//                    不存在才将另一个点放入set，且将另一个点的相连的所有边放入小根堆，
//                    并要这条边，
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        res.add(poll);
                        for (Edge edge : toNode.edges) {
                            queue.add(edge);
                        }
                    }
                }
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
}
