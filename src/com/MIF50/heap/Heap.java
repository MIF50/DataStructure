package com.MIF50.heap;

// left = parent * 2 + 1
// right = parent * 2 + 2
// parent = (index -1) / 2

public class Heap {

    private int [] items;
    private int size;

    public Heap(){
        items = new int[10];
    }

    public void insert(int value){
        if (isFull())
            throw new IllegalStateException();
        items[size++] = value;
        bubbleUp();
    }

    public int remove(){
        if (isEmpty()) throw new IllegalStateException();
        var root = items[0];
        items[0] = items[--size];
        bubbleDown();
        return root;
    }

    public int max(){
        if (isEmpty()){
            throw new IllegalStateException();
        }
        return items[0];
    }

    private void bubbleDown(){
        var parent = 0;
        while (parent < size && !isValidParent(parent)){
            var largerChildIndex = largerChildIndex(parent);
            swap(parent,largerChildIndex);
            parent = largerChildIndex;
        }
    }

    private boolean isValidParent(int parent){
        if (!hasLeftChild(parent)) return true;

        var isValid = items[parent] >= leftChild(parent);

        if (hasRightChild(parent))
            isValid &= items[parent] >= rightChild(parent);

        return isValid;
    }

    private int largerChildIndex(int parent){
        if (!hasLeftChild(parent)) return parent;

        if (!hasRightChild(parent)) return leftChildIndex(parent);

        return (leftChild(parent) > rightChild(parent)) ? leftChildIndex(parent) : rightChildIndex(parent);
    }

    private boolean hasLeftChild(int parent){
        return leftChildIndex(parent) <= size;
    }

    private boolean hasRightChild(int parent){
        return rightChildIndex(parent) <= size;
    }

    private int leftChild(int parent){
        return items[leftChildIndex(parent)];
    }

    private int rightChild(int parent){
        return items[rightChildIndex(parent)];
    }

    private int leftChildIndex(int parent){
        return (parent * 2) + 1;
    }

    private int rightChildIndex(int parent){
        return (parent * 2) + 2;
    }

    public boolean isFull(){
        return size == items.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void bubbleUp(){
        var index = size - 1;
        while (index > 0 && items[index] > items[parent(index)]){
            swap(index,parent(index));
            index = (index - 1) / 2;
        }
    }

    private int parent(int index){
        return (index - 1) / 2;
    }

    private void swap(int first, int second){
        var temp = items[first];
        items[first] = items[second];
        items[second] = temp;
    }
}
