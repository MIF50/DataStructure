package com.MIF50.sort;

import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {

    public void sort(int[] array) {
        sort(array,4);
    }

    private void sort(int[] array,int numberOfBucket) {
        var index = 0;
        for(var bucket: createBuckets(array,numberOfBucket)){
            bucket.sort();
            while (!bucket.isEmpty())
                array[index++] = bucket.remove();
        }
    }

    private BucketLinkedList[] createBuckets(int[] array,int numberOfBucket) {
        BucketLinkedList[] buckets = new BucketLinkedList[numberOfBucket];

        for (int i=0;i<numberOfBucket;i++) {
            buckets[i] = new BucketLinkedList();
        }

        for (var item:array) {
            var index = item / numberOfBucket;
            buckets[index].add(item);
        }
        return buckets;
    }

    private static class BucketLinkedList {

        private final LinkedList<Integer> linkedList;

        public BucketLinkedList() {
            linkedList = new LinkedList<Integer>();
        }

        public void add(int number) {
            linkedList.add(number);
        }

        public void sort () {
            Collections.sort(linkedList);
        }

        public int remove() {
            return linkedList.remove();
        }

        public boolean isEmpty() {
            return linkedList.isEmpty();
        }
    }
}
