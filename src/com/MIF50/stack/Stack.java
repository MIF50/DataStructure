package com.MIF50.stack;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Stack {

    private final int[] items;
    private int count = 0;

    public Stack(){
        items = new int[10];
    }

    public void push(int item){
        if (isFull())
            throw new StackOverflowError();

        items[count++]=item;
    }

    public int pop(){
        if (isEmpty())
            throw new NoSuchElementException();

        return items[--count];
    }

    public int peek(){
        if (isEmpty())
            throw new NoSuchElementException();

        return items[count - 1];
    }

    public boolean isEmpty(){
        return count==0;
    }


    private boolean isFull() { return count == items.length; }

    @Override
    public String toString() {
        var content = Arrays.copyOfRange(items,0,count);
        return Arrays.toString(content);
    }
}
