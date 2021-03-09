package com.MIF50.heap;


public class MinPriorityQueue {

    private final MinHeap minHeap = new MinHeap();

    public void add(int priority,String value) {
        minHeap.insert(priority,value);
    }

    public void remove() {
        minHeap.remove();
    }

    public boolean isEmpty() {
        return minHeap.isEmpty();
    }
}
