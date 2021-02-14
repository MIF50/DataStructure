package com.MIF50.heap;

public class MaxHeap {

    public static void heapify(int[] numbers){
        var lastParentIndex = (numbers.length / 2 ) - 1;
        for (int i = lastParentIndex; i >= 0; i--) {
            heapify(numbers,i);
        }
    }

    public static int getKthLargest(int[] numbers, int k){
        if (k == 0 || k > numbers.length){
            throw new IllegalStateException();
        }
        var heap = new Heap();
        for(int number: numbers){
            heap.insert(number);
        }
        for (int i = 0; i < k -1 ; i++) {
            heap.remove();
        }
        return heap.max();
    }

    private static void heapify(int[] numbers, int index){
        var largerIndex = index;

        var leftIndex = index * 2 + 1;
        if (leftIndex < numbers.length && numbers[leftIndex] > numbers[largerIndex]){
            largerIndex = leftIndex;
        }

        var rightIndex = index * 2 + 2;
        if (rightIndex < numbers.length && numbers[rightIndex]> numbers[largerIndex]){
            largerIndex = rightIndex;
        }

        if (largerIndex == index){
            return;
        }
        swap(numbers,index,largerIndex);
        heapify(numbers,largerIndex);
    }

    private static void swap(int[] numbers, int first, int second){
        var temp = numbers[first];
        numbers[first] = numbers[second];
        numbers[second] = temp;
    }
}
