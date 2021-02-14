package com.MIF50.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayQueue {

    private final int[] items;
    private int front;
    private int rear;
    private int count;

    public ArrayQueue(int capacity){
        items = new int[capacity];
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public boolean isFull(){
        return rear == items.length;
    }

    public void enqueue(int item){
        if (count == items.length)
            throw new IllegalStateException();

        items[rear] = item;
        rear = availableFor(rear);
        count++;
    }

    public int dequeue(){
        if (isEmpty())
            throw new NoSuchElementException();
        
        var item = items[front];
        items[front] = 0;
        front = availableFor(front);
        count--;
       return item;
    }

    private int availableFor(int position) {
        return (position + 1) % items.length;
    }

    public int peek(){
        return items[front];
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
