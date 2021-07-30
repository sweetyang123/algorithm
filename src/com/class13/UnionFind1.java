package com.class13;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 有若干个样本a、b、c、d…类型假设是V
 * 在并查集中一开始认为每个样本都在单独的集合里
 * 用户可以在任何时候调用如下两个方法：
 * boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
 * void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
 * isSameSet和union方法的代价越低越好
 *
 *  1）每个节点都有一条往上指的指针
 * 2）节点a往上找到的头节点，叫做a所在集合的代表节点
 * 3）查询x和y是否属于同一个集合，就是看看找到的代表节点是不是一个
 * 4）把x和y各自所在集合的所有点合并成一个集合，只需要小集合的代表点挂在大集合的代表点的下方即可
 *
 * 1）节点往上找代表点的过程，把沿途的链变成扁平的
 * 2）小集合挂在大集合的下面
 * 3）如果方法调用很频繁，那么单次调用的代价为O(1)，两个方法都如此
 */
public class UnionFind1 {
    private static class Node<V>{
        V value;
        public Node(V value) {
            this.value = value;
        }
    }
    static class UnionFind<V>{
        public HashMap<V,Node<V>> nodes;//装list里的数包裹一层放到nodes里
        public HashMap<Node<V>,Node<V>> parentMap;//将当前节点作为key，父节点作为value
        public HashMap<Node<V>,Integer> sizeMap;//将当前节点作为key，以当前节点为父节点的个数作为value

        public UnionFind(List<V> values) {
            nodes=new HashMap<>();
            parentMap=new
                    HashMap<>();
            sizeMap=new HashMap<>();
            for (V cur:values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur,node);
                parentMap.put(node,node);
                sizeMap.put(node,1);
            }
        }

        /**
         * 判断集合x,y是否在同一个集合里
         * @param x
         * @param y
         * @return
         */
        boolean isSameSet(V x, V y){
            //x,y的最大祖先节点相同则在同一个集合里
            return firstFather(nodes.get(x))==firstFather(nodes.get(y));
        }
        /**
         * 给你一个节点，请你往上到不能再往上，把代表返回
         * 并在这个过程中经过的节点的父都设置为最上面那个节点
         * 这样，每次找一步就能最大集合点
          */
        private Node<V> firstFather(Node<V> x) {
            Stack<Node> stack = new Stack<>();
            //将x的祖先节点都进栈
            while (x!=parentMap.get(x)){
                stack.push(x);
                x=parentMap.get(x);
            }
            while (!stack.empty()){
                //将x的祖先节点的父节点都设置为x最上面的那个祖先
                parentMap.put(stack.pop(),x);
            }
            return x;
        }
        /**
         * 将x,y集合合并，小集合放到大集合下面
         */
        void union(V x, V y){
            Node<V> xNode = firstFather(nodes.get(x));
            Node<V> yNode = firstFather(nodes.get(y));
            //如果两个集合不在一个集合里，将两个集合合并
            if (xNode!=yNode){
                int xSize = sizeMap.get(xNode);
                int ySize = sizeMap.get(yNode);
                Node<V> small = xSize<=ySize?xNode:yNode;
                Node<V> big = small==xNode?yNode:xNode;
//                将较小节点作为子，较大节点作为父
                parentMap.put(small,big);
                sizeMap.put(big,xSize+ySize);
                sizeMap.remove(small);
            }
        }
    }

}
