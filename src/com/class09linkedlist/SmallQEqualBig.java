package com.class09linkedlist;

public class SmallQEqualBig {
    public  static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    private static Node partition01(Node head,int num){
        if (head==null||head.next==null)return head;
        int i=0;
        Node cur=head;
        while (cur!=null){
            i++;
            cur= cur.next;
        }
        Node[] nodes = new Node[i];
        cur=head;
        for (int j = 0; cur!=null ; j++) {
            nodes[j]=cur;
            cur=cur.next;
        }
        addPartition(nodes,num);
        for (int j = 0; j <nodes.length ; j++) {
            head=nodes[j];
            head=head.next;
        }
//        for (int j = 1; j !=nodes.length ; j++) {
//            nodes[j-1].next=nodes[j];
//        }
//        nodes[i-1].next=null;
//        return nodes[0];
        return head;
    }
    private static Node samllEqualBig(Node head,int num){
        if (head==null||head.next==null)return head;
        Node sH=null;
        Node sT=null;
        Node eH=null;
        Node eT=null;
        Node bH=null;
        Node bT=null;
        Node cur = head;
        Node temp;
        while (cur!=null){
            temp=cur;
            temp.next=null;
            if (temp.value<num){
                if (sH==null){
                    sH=temp;
                    sT=temp;
                }else {
                    sT.next=temp;
                    sT=temp;
                }
            }else if(temp.value==num){
                if (eH==null){
                    eH=temp;
                    eT=temp;
                }else {
                    eT.next=temp;
                    eT=temp;
                }
            }else {
                if (bH==null){
                    bH=temp;
                    bT=temp;
                }else {
                    bT.next=temp;
                    bT=temp;
                }
            }
            cur = cur.next;
        }
        if (sT!=null){
            sT.next=eH;
            eT=eT==null?bH:eT;
        }
        if (eT!=null){
            eT.next=bH;
        }
        return head;
    }

    private static void addPartition(Node[] nodes, int num) {
        int l=-1;
        int index=0;
        int r=nodes.length;
       while (index!=r){
           if (nodes[index].value<num){
               swap(nodes,++l,index++);
           }else if (nodes[index].value==num){
               index++;
           }else {
               swap(nodes,--r,index);
           }
       }
    }

    private static void swap(Node[] nodes, int l, int r) {
       int temp= nodes[l].value;
        nodes[l].value=nodes[r].value;
        nodes[r].value= temp;
    }

    public static void main(String[] args) {
        int testSize=10;
        int testValue=10;
       Node node= getRadomLinkedList(testSize,testValue);
       printN(node);
       Node node1= node;
       partition01(node1,6);
       printN(node);

    }
    private static Node getRadomLinkedList(int testSize, int testValue) {
        Node head=new Node((int)(Math.random()*testValue));
        Node node = head;
        int size=(int)(Math.random()*testSize)+1;
        for (int i = 0; i <size ; i++) {
            head.next=new Node((int)(Math.random()*testValue));
            head=head.next;
        }
        return node;
    }
    private static void printN(Node node) {
        while (node!=null){
            System.out.print(node.value+" ,");
            node=node.next;
        }
        System.out.println(" ");
    }
}
