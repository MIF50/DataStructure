package com.MIF50.queue;

import java.util.NoSuchElementException;

public class LinkedListQueue {

    private final LinkedList linkedList = new LinkedList();

    public void enqueue(int item) {
        linkedList.addLast(item);
    }

    public int dequeue() {
        return linkedList.removeFirst();
    }

    public int peek() {
        if (linkedList.isEmpty())
            throw new NoSuchElementException();

        return linkedList.first.value;
    }

    public int size() {
        return linkedList.size();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    private static class LinkedList {

        private Node first;
        private Node last;
        private int size;

        public int size(){
            return size;
        }

        public void addLast(int value) {
            var newNode = new Node(value);
            if (isEmpty())
                first = last = newNode;
            else {
                last.next = newNode;
                last = newNode;
            }
            size++;
        }

        public int removeFirst() {
            if (isEmpty())
                throw new NoSuchElementException();

            int item;
            if (isSingleNode()) {
                item = first.value;
                first = last = null;
            } else {
                // [ 10 -> 20 -> 30 ]
                item = first.value;
                var second = first.next;
                first.next = null;
                first = second;
            }
            size--;
            return item;
        }

        private boolean isEmpty() {
            return  first == null;
        }

        private boolean isSingleNode() {
            return first == last;
        }

        private static class Node {
            private final int value;
            private Node next;

            public Node(int value){
                this.value = value;
            }
        }
    }
}
