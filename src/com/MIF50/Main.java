package com.MIF50;

import com.MIF50.sort.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = {1,2,8,3,1,4 };
//        var sorter = new BubbleSort();
//        sorter.sort(array);
//        System.out.println(Arrays.toString(array));

//        var sorter2 = new SelectionSort();
//        sorter2.sort(array);
//        System.out.println(Arrays.toString(array));

//        var sorter2 = new InsertionSort();
//        sorter2.sort(array);
//        System.out.println(Arrays.toString(array));]

//        var sorter2 = new MergeSort();
//        sorter2.sort(array);
//        System.out.println(Arrays.toString(array));

//        var sorter2 = new QuickSort();
//        sorter2.sort(array);
//        System.out.println(Arrays.toString(array));

        var sorter2 = new CountingSort();
        sorter2.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
