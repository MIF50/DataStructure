package com.MIF50.sort;


import java.util.Arrays;

public class CountingSort {

    public void sort(int[] array) {
        var max = getMax(array);
        sort(array,max);
    }

    private void sort(int[] array, int max) {
        int[] counts = new int[max + 1];
        for (var item: array) {
            counts[item]++;
        }
        int k = 0;
        for (int i = 0; i< counts.length;i++) {
            for (int j = 0; j<counts[i];j++) {
                array[k++] = i;
            }
        }
    }

    private int getMax(int[] array) {
        if (array.length == 0)
            return 0;
        return Arrays.stream(array).max().orElse(0);
    }
}
