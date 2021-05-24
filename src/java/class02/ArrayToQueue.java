package class02;

public class ArrayToQueue {
    public static class MyQueue{
        private int[] arr;
        private int pushi;
        private int polli;
        private int size;
        private int limit;

        public void MyQueue(int limit) {
            arr=new int[limit];
            pushi=0;
            polli=0;
            size=0;
            this.limit=limit;
        }
        public void push(int value){
            if(size==limit){
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            arr[pushi]=value;
            pushi=nextIndex(pushi);
        }
        public int poll(){
            if (size==0){
                throw new RuntimeException("队列空了，不能再弹出了");
            }
            size--;
            polli=nextIndex(polli);
            return arr[polli];
        }
        private int nextIndex(int i) {
            return i<limit-1?i+1:0;
        }
    }
}
