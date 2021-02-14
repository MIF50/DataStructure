package com.MIF50.hashtable;

import java.util.*;

//put(key,value)
//get(key):value
//remove(key)
//k: int , v: String
//collisions: chaining
// Entry(k,v)
// LinkedList<Entry>[]
// [LL,LL,LL,LL,LL,LL]
public class HashTable {

    private final LinkedList<Entry>[] entries;

    public HashTable(int capacity){
        entries = new LinkedList[capacity];
    }

    public void put(int key, String value) {
        var entry = getEntry(key);
        if (entry!=null){
            entry.value = value;
            return;
        }

        getOrCreateBucket(key).addLast(new Entry(key, value));
    }

    public String get(int key) {
        var entry = getEntry(key);
        return entry==null ? null : entry.value;
    }

    public void remove(int key) {
        var entry = getEntry(key);
        if (entry!=null) {
            getBucket(key).remove(entry);
            return;
        }

        throw new IllegalStateException();
    }

    private int hash(int key){
        return key % entries.length;
    }

    private Entry getEntry(int key) {
        var bucket = getBucket(key);
        if (bucket!=null){
            for (var entry: bucket)
                if (entry.key == key) return entry;
        }

        return null;
    }

    private LinkedList<Entry> getBucket(int key){
        return entries[hash(key)];
    }

    private LinkedList<Entry> getOrCreateBucket(int key) {
        var index = hash(key);
        var bucket = entries[index];
        if (bucket == null) {
            entries[index] = new LinkedList<>();
            bucket = entries[index];
        }

        return bucket;
    }

    public int mostFrequent(int[] array) {
        if (array.length == 0) return -1;
        var hash = new HashMap<Integer,Integer>();
        for (var number : array) {
            var count = hash.getOrDefault(number, 0);
            hash.put(number,count + 1);
        }

        var mostFrequent = Integer.MIN_VALUE;
        var countFrequent = Integer.MIN_VALUE;
        for (var key: hash.keySet()) {
            if (hash.get(key) > countFrequent){
                mostFrequent = key;
                countFrequent = hash.get(key);
            }
        }
        return mostFrequent;
    }

    public int countPairsWithDiff(int [] array, int k) {
        var hash = new HashMap<Integer,Integer>();

        for (int i=0;i<array.length;i++) {
            for (int j=i+1;j<array.length;j++) {
                var firstPair = array[i];
                var secondPair = array[j];
                if (Math.abs(firstPair-secondPair) == k)
                    hash.put(Math.min(firstPair,secondPair),Math.max(firstPair,secondPair));
            }
        }

        return hash.size();
    }

    public int findPair(int[] array, int k) {
        Arrays.sort(array);
        var set = new HashSet<Integer>();
        var count = 0;

        for (int i=0;i<array.length;i++) {
            while (i+1<array.length && array[i] == array[i+1]) {
                i++;
            }
            if (set.contains(array[i] - k)) {
                count++;
                System.out.println("(" +  (array[i]-k) + " , "+   array[i]+")");
            }
            if (set.contains(array[i] + k)) {
                count++;
                System.out.println("(" + array[i] + " , "+  (array[i]+k) +")");
            }
            set.add(array[i]);
        }
        return count;
    }

    public void twoSum(int [] array,int target) {
        Arrays.sort(array);
        var set = new HashMap<Integer,Integer>();
        var index = 0;
        for (int number : array) {
            set.put(number,index);
            index ++;
        }

        for (int i=0;i<array.length;i++) {
            if (set.containsValue(target - array[i])) {
                var secondIndex = set.get(Math.abs(array[i] - target));
                System.out.println("["+ i+","+ secondIndex+"]");
            }

        }
    }

    public int[] twoSum2(int[] array, int target) {
        int i = 0, j = array.length -1;
        int[] x = new int[2];

        var indexes = new HashMap<Integer,Integer>();
        for(int k =0;k<array.length;k++) {
            if (!indexes.containsKey(array[k])) {
                indexes.put(array[k],k);
            } else if (array[k] * 2 == target) {
                x[0] = indexes.get(array[k]);
                x[1] = k;
                return x;
            }
        }

        Arrays.sort(array);
        while (i<j) {
            if (array[i] + array[j] > target) {
                j--;
            } else if (array[i] + array[j] < target) {
                i++;
            } else {
                x[0] = indexes.get(array[i]);
                x[1] = indexes.get(array[j]);
                if (x[0] > x[1]) {
                    var temp = x[1];
                    x[1] = x[0];
                    x[0] = temp;
                }
                return x;
            }
        }

        return x;
    }

    private static class Entry {

        private final int key;
        private String value;

        public Entry(int key,String value){
            this.key = key;
            this.value = value;
        }
    }
}
