package com.class16graph;

import java.util.*;

/**
 * 最小生成树---Dijkstra
 * map（放初始节点到每个节点的距离）+hashSet（看某节点是否已判断过）;
 * 加强堆（增加某个节点最短距离改变时做动态调整的方法）
 * 1）Dijkstra算法必须指定一个源点
 * 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，
 * 即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
 * 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，
 * 更新源点到各个点的最小距离表，不断重复这一步
 * 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
 */
public class MBT_Dijkstra {
    public static Map<Node, Integer> getMBT(Node from) {
        Map<Node, Integer> res = new HashMap<>();
        //存放任意给定的一个点from到其他点的距离
        Map<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        HashSet<Node> selectedNodes = new HashSet<>();
//        获取没被选中过的最小节点
        Node minNode = getMinNodeAndUnSelected(distanceMap, selectedNodes);
        while (minNode != null) {
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                int distance = distanceMap.get(minNode) + edge.weight;
//                如果节点to已经存在，
//                则将他的距离与from的距离加上他们之间的边的权重作比较，小的作为to的最新距离
                if (distanceMap.containsKey(to)) {
                    distanceMap.put(to, Math.min(distanceMap.get(to), distance));
                } else {
                    distanceMap.put(to, distance);
                }
                selectedNodes.add(minNode);
                minNode = getMinNodeAndUnSelected(distanceMap, selectedNodes);
            }
        }
        return res;
    }

    private static Node getMinNodeAndUnSelected(Map<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        int minDistance = Integer.MAX_VALUE;
        Node minNode = null;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(node) && minDistance > distance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    /**
     * 改进，用自定义的堆，
     * 增加当堆中数据发生变化时调整堆结构的方法
     */
    public static class NodeRecord {
        Node node;
        int distance;

        public NodeRecord(Node node, int distance) {
            this.distance = distance;
            this.node = node;
        }
    }

    public static class NodeHeap {
        Node[] nodes;// 实际的堆结构
        //存放Node在堆上的索引号
        HashMap<Node, Integer> headIndexMap;
        //存放源点到每个Node的距离
        HashMap<Node, Integer> distanceMap;
        int size;//堆上有多少个点

        public NodeHeap(int size) {
            nodes = new Node[size];
            headIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            //node在堆里,更新distance与堆结构
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, headIndexMap.get(node));
            } else if (!isEntered(node)) {
//                node没有进入过堆，则在堆里新增节点
                distanceMap.put(node, distance);
                nodes[size] = node;
                headIndexMap.put(node, size);
                insertHeapify(node, size++);
            }
        }

        //        头节点出堆，将尾节点与头节点互换，再删除尾节点，将头节点heapify
        public NodeRecord pop() {
            NodeRecord record = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            distanceMap.remove(nodes[size - 1]);
            headIndexMap.put(nodes[size - 1], -1);
            nodes[size - 1] = null;
            heapify(0, size--);
            return record;
        }

        //        当前节点向下看，左右子节点的distance大于自己则交换位置
        private void heapify(int i, int size) {
            int left = 2 * i + 1;
            while (left < size) {
                int small = left + 1 < size &&
                        distanceMap.get(nodes[left]) > distanceMap.get(nodes[left + 1]) ? left + 1 : left;
                small = distanceMap.get(nodes[i]) < distanceMap.get(nodes[small]) ? i : small;
                if (small == i) {
                    break;
                }
                swap(small, i);
                i = small;
                left = 2 * i + 1;
            }
        }

        //        入堆
        private void insertHeapify(Node node, Integer index) {
            while (distanceMap.get(nodes[index]) > distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void swap(int i, int j) {
            headIndexMap.put(nodes[i], j);
            headIndexMap.put(nodes[j], i);
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }

        //-1为出现过
        private boolean inHeap(Node node) {
            return isEntered(node) && headIndexMap.get(node) != -1;
        }

        private boolean isEntered(Node node) {
            return headIndexMap.containsKey(node);
        }
    }

    public static Map<Node, Integer> getMBT(Node from, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        //from节点如果存在于堆里则更新，否则增加，其他情况则忽略
        nodeHeap.addOrUpdateOrIgnore(from, 0);
        Map<Node, Integer> res = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge e : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(cur, distance + e.weight);
            }
            res.put(cur, distance);
        }
        return res;
    }
}
