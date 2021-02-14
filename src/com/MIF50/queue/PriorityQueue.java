package com.MIF50.queue;

import java.util.Arrays;

// insert(2)
// [1,3,5,7]
public class PriorityQueue {

    private final int [] items;
    private int count = 0;

    public PriorityQueue(int capacity){
        items = new int[capacity];
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public boolean isFull(){
        return count == items.length;
    }

    public void insert(int item){
        if (isFull()) // one exercise to make it dynamic
            throw new IllegalStateException();

        var positionToInsert = shiftItemToInsert(item);
        items[positionToInsert] = item;
        count++;
    }

    private int shiftItemToInsert(int item){
        int position;
        for (position = count-1; position >= 0; position--) {
            if (item < items[position])
                items[position+1]=items[position];
            else
                break;
        }
        return position+1;
    }

    public int remove(){
        if (isEmpty())
            throw new IllegalStateException();

        return items[--count];
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
