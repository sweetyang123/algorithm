package com.class10binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。
 * 树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。 叶节点是没有任何下属的基层员工(subordinates列表为空)，
 * 除基层员工外，每个员工都有一个或多个直接下级
 * <p>
 * 派对的最大快乐值
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 */
public class MaxHappy {
    static class Employee {
        public int happy; // 这名员工可以带来的快乐值
        List<Employee> subordinates; // 这名员工有哪些直接下级

        public Employee(int happy) {
            this.happy = happy;
        }
    }

    static class Info {
        int yes;//来了的快乐值是多少
        int no;//没来的快乐值又是多少

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    private Info process(Employee boss) {
        if (boss == null) return new Info(0, 0);
        List<Employee> subordinates = boss.subordinates;
        int yes = boss.happy;
        int no = 0;
        if (subordinates != null && subordinates.size() > 0) {
            for (int i = 0; i < subordinates.size(); i++) {
                Info info = process(subordinates.get(i));
                //头节点不来则快乐值是子节点来或不来的最大快乐值
                no = Math.max(info.yes, info.no);
//              头节点来，则值为子节点不来的快乐值加上自己的快乐值
                yes += info.no;
            }
        }
        return new Info(yes, no);
    }

    private int getMaxHappy(Employee employee) {
        Info process = process(employee);
        return Math.max(process.yes, process.no);
    }

    public static void main(String[] args) {
        MaxHappy mh = new MaxHappy();
        Employee e = new Employee(9);
        Employee sube1 = new Employee(1);
        Employee sube2 = new Employee(7);
        Employee sube3 = new Employee(5);
        List<Employee> list = new ArrayList<>();
        list.add(sube1);
        list.add(sube2);
        list.add(sube3);
        e.subordinates = list;
        System.out.println(mh.getMaxHappy(e));
    }
}
