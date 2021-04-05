package com.MIF50.stack;


public class MinStack {

    private Node top;

    public void push(int item) {
        if (isEmpty()){
            top = new Node(item, item);
            return;
        }

        var newNode = new Node(item, Math.min(item, top.min));
        newNode.next = top;
        top = newNode;
    }

    public void pop() {
        if (isEmpty())
            throw new IllegalStateException();

        var temp = top.next;
        top.next = null;
        top = temp;
    }

    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return top.value;
    }

    public int min() {
        if (isEmpty())
            throw new IllegalStateException();

        return top.min;
    }

    private boolean isEmpty() { return top == null; }

    private static class Node {
        private final int value;
        private final int min;
        private Node next;

        public Node(int value,int min) {
            this.value = value;
            this.min = min;
        }
    }
}
