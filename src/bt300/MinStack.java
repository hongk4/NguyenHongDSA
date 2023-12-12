package bt300;

import CTDLVGT.Hw3_22001589_NguyenXuanHong.b2mimgo.LinkedListStack;

import java.util.EmptyStackException;

class MinStack {
    private class Node {
        int element;
        int min;
        Node next;

        public Node(int element, int min, Node next) {
            this.element = element;
            this.min = min;
            this.next = next;
        }

        public Node(int element, int min) {
            this.element = element;
            this.min = min;
        }
        public Node(int element, Node next) {
            this.element = element;
            this.next = next;
        }

        public Node(int element) {
            this.element = element;
        }
    }
    private Node stack = null;
    private int size = 0;
    public MinStack() {

    }

    public void push(int val) {
        if (stack == null) {
            stack = new Node(val, val);
        } else {
            int newMin = Math.min(val, stack.min);
            Node newNode = new Node(val, newMin, stack);
            stack = newNode;
        }
        size++;
    }

    public void pop() {
        if (stack != null) {
            stack = stack.next;
        }
    }
    public int size(){
        return size;
    }

    public int top() {
        if (stack != null) {
            return stack.element;
        }
        return -1; // or throw an exception to indicate an empty stack
    }

    public int getMin() {
        return stack.min;
    }

    public static void main(String[] args) {
      MinStack minStack = new MinStack();
      minStack.push(1);
      minStack.push(2);
      minStack.push(3);
       for(int i = 0;i<minStack.size(); i++){
           System.out.print(minStack.top()+" ");
           minStack.pop();
       }
        System.out.println(minStack.getMin());

    }
}
