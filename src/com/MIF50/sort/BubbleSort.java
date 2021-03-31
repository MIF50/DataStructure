package com.MIF50.sort;

public class BubbleSort {

    public void sort(int[] array) {

        var isSorted = false;
        for (var i = 0;i < array.length;i++) {
            isSorted = true;
            for (var j = 1;j<array.length - i;j++) {
                if (array[j] < array[j-1]) {
                    swap(array,j,j-1);
                    isSorted = false;
                }
            }
            if (isSorted)
                return;
        }
    }

    private void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
