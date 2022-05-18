package com.class13;

import javax.lang.model.element.NestingKind;
import java.util.*;

/**
 * 如果两个user，a字段一样，或者b字段一样，或者c字段一样则认为是同一个人，
 * 请合并user，返回合并之后的用户数量
 */
public class UserUnionFind {
    static class User {
        String a;
        String b;
        String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    private int getSize(List<User> users) {
        UnionFind<User> unionFind = new UnionFind<>(users);
        HashMap<String, User> aMap = new HashMap<>();
        HashMap<String, User> bMap = new HashMap<>();
        HashMap<String, User> cMap = new HashMap<>();
        for (User user : users) {
            //如果aMap中存在循环中user的a属性，则将两个user合并，否则将这个属性与user的对应关系放到map中
            if (aMap.containsKey(user.a)) {
                unionFind.union(user, aMap.get(user.a));
            } else {
                aMap.put(user.a, user);
            }
            if (bMap.containsKey(user.b)) {
                unionFind.union(user, bMap.get(user.b));
            } else {
                bMap.put(user.b, user);
            }
            if (cMap.containsKey(user.c)) {
                unionFind.union(user, cMap.get(user.c));
            } else {
                cMap.put(user.c, user);
            }
        }
        return unionFind.sizeMap.size();
    }

    private static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }
    }

    static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;//装list里的数包裹一层放到nodes里
        public HashMap<Node<V>, Node<V>> parentMap;//将当前节点作为key，父节点作为value
        public HashMap<Node<V>, Integer> sizeMap;//将当前节点作为key，以当前节点为父节点的个数作为value

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * 判断集合x,y是否在同一个集合里
         *
         * @param x
         * @param y
         * @return
         */
        boolean isSameSet(V x, V y) {
            //x,y的最大祖先节点相同则在同一个集合里
            return firstFather(nodes.get(x)) == firstFather(nodes.get(y));
        }

        /**
         * 给你一个节点，请你往上到不能再往上，把代表返回
         * 并在这个过程中经过的节点的父都设置为最上面那个节点
         * 这样，每次找一步就能最大集合点
         */
        private Node<V> firstFather(Node<V> x) {
            Stack<Node> stack = new Stack<>();
            //将x的祖先节点都进栈
            while (x != parentMap.get(x)) {
                stack.push(x);
                x = parentMap.get(x);
            }
            while (!stack.empty()) {
                //将x的祖先节点的父节点都设置为x最上面的那个祖先
                parentMap.put(stack.pop(), x);
            }
            return x;
        }

        /**
         * 将x,y集合合并，小集合放到大集合下面
         */
        void union(V x, V y) {
            Node<V> xNode = firstFather(nodes.get(x));
            Node<V> yNode = firstFather(nodes.get(y));
            //如果两个集合不在一个集合里，将两个集合合并
            if (xNode != yNode) {
                int xSize = sizeMap.get(xNode);
                int ySize = sizeMap.get(yNode);
                Node<V> small = xSize <= ySize ? xNode : yNode;
                Node<V> big = small == xNode ? yNode : xNode;
//                将较小节点作为子，较大节点作为父
                parentMap.put(small, big);
                sizeMap.put(big, xSize + ySize);
                sizeMap.remove(small);
            }
        }
    }
}
