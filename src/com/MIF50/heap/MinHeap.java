package com.MIF50.heap;

public class MinHeap {

    private final Node[] items = new Node[10];
    private int size;

    public void insert(int key, String value) {
        if (isFull())
            throw new IllegalStateException("your min heap is full");
        items[size++] = new Node(key, value);
        bubbleUp();
    }

    public Node remove() {
        if (isEmpty())
            throw new IllegalStateException("your min Heap is empty");
        var root = items[0];
        items[0] = items[--size];
        bubbleDown();
        return root;
    }

    private void bubbleDown() {
        var parent = 0;
        while (parent < size && !isValidParent(parent)) {
            var lowestChildIndex = lowestChildIndex(parent);
            swap(parent,lowestChildIndex);
            parent = lowestChildIndex;
        }
    }

    private int lowestChildIndex(int parent) {
        if (!hasLeftChildIndex(parent))
            return parent;


        if (!hasRightChildIndex(parent))
            return leftChildIndex(parent);

        return (leftChild(parent).key < rightChild(parent).key) ? leftChildIndex(parent): rightChildIndex(parent);
    }

    private boolean isValidParent(int parent) {
        if (!hasLeftChildIndex(parent))
            return true;

        var isValid = items[parent].key <= leftChild(parent).key;

        if (hasRightChildIndex(parent))
            isValid &= items[parent].key <= rightChild(parent).key;

        return isValid;
    }

    private Node leftChild(int parent) {
        return items[leftChildIndex(parent)];
    }

    private Node rightChild(int parent) {
        return items[rightChildIndex(parent)];
    }

    private boolean hasLeftChildIndex(int parent) {
        return leftChildIndex(parent) <= size;
    }

    private boolean hasRightChildIndex(int parent) {
        return rightChildIndex(parent) <= size;
    }

    private int leftChildIndex(int parent) {
        return (parent * 2) + 1;
    }

    private int rightChildIndex(int parent) {
        return (parent * 2) + 2;
    }

    private void bubbleUp() {
        var index = size -1;
        while (index > 0 && items[index].key < items[parentFor(index)].key) {
            swap(index,parentFor(index));
            index = parentFor(index);
        }
    }

    private int parentFor(int index) {
        return (index - 1) / 2;
    }

    public boolean isFull() {
        return items.length == 0;
    }

    public boolean isEmpty() {
        return  items.length == size;
    }

    private void swap(int first, int second) {
        var temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }

    private static class Node {

        private final int key;
        private final String value;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "key=" + key + ", value=" + value;
        }
    }
}
