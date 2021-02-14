package com.MIF50.stack;

import java.util.NoSuchElementException;

public class TwoStacks {

    private final int[] items;
    private int top1,top2;
    private final int size;

    public TwoStacks(int capacity) {
        this.size = capacity;
        items = new int[size];
        top1 = -1;
        top2 = size;
    }

    public void push1(int item) {
        if (isFull1())
            throw new StackOverflowError();

        items[++top1] = item;
    }

    public int pop1() {
        if (isEmpty1())
            throw new NoSuchElementException();

        return items[top1--];
    }

    public void push2(int item) {
        if (isFull2())
            throw new StackOverflowError();

        items[--top2] = item;
    }

    public int pop2() {
        if (isEmpty2())
            throw new NoSuchElementException();

        return items[top2++];
    }

    public boolean isEmpty1() { return top1 < 0; }

    public boolean isEmpty2() { return  top2 >= size; }

    public boolean isFull1() { return top1 + 1 == top2; }

    public boolean isFull2() { return top1 + 1 == top2; }
}
