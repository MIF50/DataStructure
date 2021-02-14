package com.MIF50.linked_list;

import java.util.NoSuchElementException;

/*
*  Lookup
*        by Index = O(n)
*        by Value = O(n)
*  Insert
*       Beginning/End = O(1)
*       Middle = O(1)
*  Delete
*       Beginning = O(1)
*       Middle = O(n)
*       End = O(n) / O(1) Double Linked List
*
* */
public class LinkedList {

    private final static int ITEM_NOT_FOUND = -1;

    private Node first;
    private Node last;
    private int size;

    public int size(){
        return size;
    }

    public void addFirst(int value) {
        var newNode = new Node(value);
        if (isEmpty()){
            first = last = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        size++;
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

    public int indexOf(int item) {
        int index = 0;
        var current = first ;
        while (current != null){
            if (current.value == item) return index;
            current = current.next;
            index++;
        }
        return ITEM_NOT_FOUND;
    }

    public boolean contains(int item){
        return indexOf(item) != ITEM_NOT_FOUND;
    }

    public void removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (isSingleNode())
            first = last = null;
        else {
            // [ 10 -> 20 -> 30 ]
            var second = first.next;
            first.next = null;
            first = second;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (isSingleNode())
            first = last = null;
        else {
            // [ 10 -> 20 -> 30 ]
            var previousLast = getPrevious(last);
            last = previousLast;
            previousLast.next = null;
        }
        size--;
    }

    public int[] toArray() {
        var array = new int[size];
        var current = first;
        var index = 0;
        while (current != null){
            array[index++] = current.value;
            current = current.next;
        }
        return array;
    }

    public void reverse() {
        if (isEmpty()) return;
        // [10 -> 20 -> 30]            ->        [10 <- 20 <- 30]
        //  p      c     n
        //         p     c    n
        //               p    c
        var previous = first;
        var current = first.next;
        while (current != null){
            var next = current.next;
            current.next = previous;
            // go forward one step
            previous = current;
            current = next;
        }
        last = first;
        last.next = null;
        first = previous;
    }

    public int getKthFromEnd(int k) {
        if (isEmpty())
            throw new IllegalStateException();
        // [ 10 -> 20 -> 30 -> 40 -> 50 ]
        //               *            *
        // k = 1 (50)
        // k = 2 (40)
        // k = 3 (30) (distance = 2)

        var current = first;
        var next = first;
        for (int i=0; i<k-1; i++) {
            next = next.next;
            if (next == null)
                throw new IllegalArgumentException();
        }

        while (next != last){
            current = current.next;
            next = next.next;
        }
        return current.value;
    }

    public void printMiddle() {
        if (isEmpty())
            throw new IllegalArgumentException();

        var firstPointer = first;
        var secondPointer = first;
        while (secondPointer != last && secondPointer.next != last) {
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next.next;
        }
        if (secondPointer == last)
            System.out.println(firstPointer.value);
        else
            System.out.println(firstPointer.value + " , " + firstPointer.next.value);
    }

    public boolean hasLoop() {
        var slowPointer = first;
        var fastPointer = first;
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (slowPointer == fastPointer)
                return true;
        }
        return false;
    }

    public void createLoop() {
        if (isSingleNode()) return;
        var previousLast = getPrevious(last);
        previousLast.next.next = first;
    }

    private boolean isEmpty(){
        return  first == null;
    }

    private Node getPrevious(Node node) {
        var current = first;
        while (current != null){
            if (current.next == node) return current;
            current = current.next;
        }
        return null;
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
