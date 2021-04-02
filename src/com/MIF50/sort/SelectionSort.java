package com.MIF50.sort;

public class SelectionSort {

    public void sort(int[] array) {
        for (var i = 0;i < array.length;i++){
            var minIndex = findMinIndex(array,i);
            swap(array,i,minIndex);
        }
    }

    private int findMinIndex(int[] array,int i) {
        var minIndex = i;
        for (var j=i+1;j<array.length;j++){
            if (array[j] < array[minIndex])
                minIndex = j;
        }
        return minIndex;
    }

    private void swap(int[] array,int first, int second) {
        var temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
