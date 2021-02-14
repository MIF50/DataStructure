package com.MIF50.queue;

import com.MIF50.stack.Stack;

import java.util.Queue;

public class ReverseQueue {

    public void reverse(Queue<Integer> queue){
        // input = [10,20,30] - output [30,20,10]
        var stack = new Stack();

        while (!queue.isEmpty())
            stack.push(queue.remove());

        while (!stack.isEmpty())
            queue.add(stack.pop());
    }

    /*
    *  input Q = [10,20,30,40,50] , K=3
    *  output   Q = [30, 20, 10, 40, 50]
    * */
    public void reverse(Queue<Integer> queue, int k) {
        if (k <= 0 || k > queue.size() || queue.isEmpty())
            return;

        var stack = new Stack();
        for (int i=0;i<k;i++)
            stack.push(queue.remove());

        while (!stack.isEmpty())
            queue.add(stack.pop());

        for (int i=0;i<queue.size()-k;i++)
            queue.add(queue.remove());
    }


    public void reverse2(Queue<Integer> queue, int k) {
        if (k <= 0 || k > queue.size() || queue.isEmpty())
            return;

        var items = arrayFromQueue(queue);
        reverseArrayAt(k,items);
        for (int item : items)
            queue.add(item);
    }

    private int [] arrayFromQueue(Queue<Integer> queue) {
        int [] items = new int[queue.size()];
        var index = 0;
        while (!queue.isEmpty())
            items[index++] = queue.poll();

        return items;
    }

    private void reverseArrayAt(int k,int[] items) {
        var stack = new Stack();
        for (int i = 0;i<k;i++) {
            stack.push(items[i]);
        }
        int index = 0;
        while (!stack.isEmpty())
            items[index++] = stack.pop();
    }
}
