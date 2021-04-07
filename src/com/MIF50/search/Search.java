package com.MIF50.search;


public class Search {
    private final static int ITEM_NOT_FOUND = -1;

    public int linearSearch(int[] array,int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target)
                return i;
        }
        return ITEM_NOT_FOUND;
    }

    public int binarySearch(int[] array,int target) {
        return binarySearchIteration(array, target);
    }

    private int binarySearchRecursion(int[] array, int target) {
        return binarySearchRecursion(array,target,0,array.length-1);
    }

    private int binarySearchRecursion(int[] array, int target,int left,int right) {
        if (right < left)
            return ITEM_NOT_FOUND;

        var middle = (left + right) / 2;
        if (array[middle] > target)
            return binarySearchRecursion(array,target,left,middle-1);
        else if (array[middle] < target)
            return binarySearchRecursion(array,target,middle+1,right);
        else
            return middle;
    }

    private int binarySearchIteration(int[] array, int target) {
        var left = 0;
        var right = array.length - 1;
        while (left <= right) {
            var middle = (left + right) / 2;
            if (array[middle] > target)
                right = middle - 1;
            else if (array[middle] < target)
                left = middle + 1;
            else
                return middle;
        }

        return ITEM_NOT_FOUND;
    }

    public int ternarySearch(int[] array, int target) {
        return ternarySearch(array,target,0,array.length - 1);
    }

    private int ternarySearch(int[] array, int target,int left, int right) {
        if (left > right)
            return ITEM_NOT_FOUND;

        var partitionSize = (right - left) / 3;
        var mid1 = left + partitionSize;
        var mid2 = right - partitionSize;

        if (array[mid1] == target)
            return mid1;
        else if (array[mid2] == target)
            return mid2;
        else if (array[mid1] > target)
            return ternarySearch(array,target,left,mid2 -1);
        else if (array[mid2] < target)
            return ternarySearch(array,target,mid1+1,right);
        else
            return ternarySearch(array,target,mid1+1,mid2-1);

    }

    public int jumpSearch(int[] array, int target) {
        var blockSize = (int) Math.sqrt(array.length);
        var start = 0;
        var next = blockSize;

        while (start < array.length && array[next-1] < target) {
            start = next;
            next += blockSize;
            if (next > array.length)
                next = array.length;

        }

        for (int i = start;i < next;i++) {
            if (array[i] == target)
                return i;
        }

        return ITEM_NOT_FOUND;
    }

    public int exponentialSearch(int[] array,int target) {
        var bound = 1;
        while (bound < array.length && array[bound] < target)
            bound *= 2;

        var left = bound / 2;
        var right = Math.min(bound,array.length - 1);
        return binarySearchRecursion(array,target,left,right);
    }
}
