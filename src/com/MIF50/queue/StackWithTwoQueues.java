package com.MIF50.queue;
// s [10,20,30,40,50]
// q1 [10,]
// q2 []
import java.util.NoSuchElementException;

public class StackWithTwoQueues {

    private final ArrayQueue q1 = new ArrayQueue(5);
    private final ArrayQueue q2 = new ArrayQueue(5);

    public void push(int item) {
        if (q1.isFull() || q2.isFull())
            throw new IllegalStateException("Stack is Full");

        if (!q1.isEmpty()) {
            while (!q1.isEmpty())
                q2.enqueue(q1.dequeue());
        }
        q1.enqueue(item);

        while (!q2.isEmpty()) {
            q1.enqueue(q2.dequeue());
        }
    }

    public int pop() {
        if (isEmpty())
            throw new NoSuchElementException();
        return q1.dequeue();
    }

    public int peek() {
        if (isEmpty())
            throw new NoSuchElementException();

        return q1.peek();
    }

    public int size() {
        return q1.size();
    }

    public boolean isEmpty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}
