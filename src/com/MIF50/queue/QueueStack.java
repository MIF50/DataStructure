package com.MIF50.queue;
import java.util.Stack;

// how to implement Queue with Stack
// q [10,20,30]
// s1 [10,20,30] enqueue
// s2 [30,20,10] dequeue
public class QueueStack {
    private final Stack<Integer> enStack;
    private final Stack<Integer> deStack;

    public QueueStack(){
        enStack = new Stack<>();
        deStack = new Stack<>();
    }

    public void enqueue(int item){
        enStack.push(item);
    }

    public int dequeue(){
        if (isEmpty())
            throw new IllegalStateException();

        moveStackEnqueueToStackDequeue();

        return deStack.pop();
    }

    public int peek(){
        if (isEmpty())
            throw new IllegalStateException();

        moveStackEnqueueToStackDequeue();

        return deStack.peek();
    }

    private void moveStackEnqueueToStackDequeue(){
        if (deStack.isEmpty()){
            while (!enStack.isEmpty())
                deStack.push(enStack.pop());
        }
    }

    public boolean isEmpty(){
        return enStack.isEmpty() && deStack.isEmpty();
    }

}
