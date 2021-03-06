package com.MIF50;

import com.MIF50.heap.MaxHeap;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int [] array = {5,3,8,2,4,1,9};
        MaxHeap.heapify(array);
        System.out.println(Arrays.toString(array));
    }
}
