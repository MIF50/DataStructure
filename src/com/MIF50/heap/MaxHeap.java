package com.MIF50.heap;

public class MaxHeap {

    public static void heapify(int[] array) {
        var lastParentIndex = (array.length / 2 ) - 1;
        for (int i = lastParentIndex; i >= 0; i--)
            heapify(array,i);
    }

    public static int getKthLargest(int[] numbers, int k) {
        if (k < 1 || k > numbers.length)
            throw new IllegalArgumentException();

        var heap = new Heap();
        for(int number: numbers)
            heap.insert(number);

        for (int i = 0; i < k -1 ; i++)
            heap.remove();

        return heap.max();
    }

    public static boolean isMaxHeap(int[] array) {
        var lastParentIndex = (array.length / 2) - 1;
        for (int i = 0; i < lastParentIndex; i++) {
            if (array[leftChildIndex(i)] > array[i])
                return false;

            if (array[rightChildIndex(i)] > array[i])
                return false;
        }
        return true;
    }

    private static void heapify(int[] array, int index) {
        var largerIndex = index;

        var leftIndex = index * 2 + 1;
        if (leftIndex < array.length && array[leftIndex] > array[largerIndex])
            largerIndex = leftIndex;

        var rightIndex = index * 2 + 2;
        if (rightIndex < array.length && array[rightIndex]> array[largerIndex])
            largerIndex = rightIndex;

        if (largerIndex == index)
            return;

        swap(array,index,largerIndex);
        heapify(array,largerIndex);
    }

    private static void swap(int[] array, int first, int second){
        var temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private static int leftChildIndex(int parent) {
        return parent * 2 + 1;
    }

    private static int rightChildIndex(int parent) {
        return parent * 2 + 2;
    }
}
