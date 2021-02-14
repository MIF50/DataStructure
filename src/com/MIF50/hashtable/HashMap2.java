package com.MIF50.hashtable;


// https://www.algolist.net/Data_structures/Hash_table/Open_addressing
public class HashMap2 {

    private final HashEntry [] table;
    private int size;

    public HashMap2(int capacity) {
        table = new HashEntry[capacity];
    }

    public void put(int key, String value) {
        var hash = hash(key);
        var initHash = -1;
        var indexOfDeleteEntry = -1;
        while (hash != initHash && (table[hash]!= null || table[hash] == DeleteEntry.getUniqueDeleteEntry()) && table[hash].key != key) {
            if (initHash == -1)
                initHash = hash;
            if (table[hash] == DeleteEntry.getUniqueDeleteEntry())
                indexOfDeleteEntry = hash;
            hash = hash(hash+1);
        }

        if ((table[hash] == null || hash == initHash) && indexOfDeleteEntry != -1){
            table[indexOfDeleteEntry] = new HashEntry(key,value);
            size++;
        } else if (hash != initHash) {
            if (table[hash] != DeleteEntry.getUniqueDeleteEntry() && table[hash] != null && table[hash].key == key){
                table[hash].value = value;
            } else {
                table[hash] = new HashEntry(key,value);
                size++;
            }
        }
    }

//    public void put(int key, String value) {
//        if (getEntry(key) != null) {
//            getEntry(key).value = value;
//            return;
//        }
//
//        if (isFull())
//            throw new StackOverflowError();
//
//        var hash = hash(key);
//        var entry = new HashEntry(key, value);
//        if (isEmptyIndex(hash)) {
//            table[hash] = entry;
//            size++;
//            return;
//        }
//
//        var emptyIndex = getAvailableEmptyFor(hash);
//        table[emptyIndex] = entry;
//        size++;
//    }

    public String get(int key) {
        if (isEmpty())
            throw new IllegalStateException();

        var entry = getEntry(key);
        return entry != null ? entry.value : null;
    }

    public void remove(int key) {
        var hash = hash(key);
        int initHash = -1;

        while (hash != initHash && (table[hash] != null || table[hash] == DeleteEntry.getUniqueDeleteEntry()) && table[hash].key != key) {
            if (initHash == -1)
                initHash = hash;
            hash = hash(hash+1);
        }

        if (table[hash] != null && hash != initHash) {
            size--;
            table[hash] = DeleteEntry.getUniqueDeleteEntry();
        }
    }

    private HashEntry getEntry(int key) {
        var hash = hash(key);
        int initHash = -1;

        while (hash != initHash && (table[hash] != null || table[hash] == DeleteEntry.getUniqueDeleteEntry()) && table[hash].key != key) {
            if (initHash == -1)
                initHash = hash;
            hash = hash(hash+1);
        }

        return (table[hash] != null && table[hash].key == key) ? table[hash] : null;
    }

    public int size() {
        return size;
    }

    private int hash(int key) {
        return key % table.length;
    }

    private boolean isFull() {
        return table.length == size;
    }

    private boolean isEmpty() { return size == 0; }

    private boolean isEmptyIndex(int index) {
        return table[index] == null;
    }

    private int getAvailableEmptyFor(int index) {
        var availableIndex = index+1;
        while (availableIndex% table.length < table.length && table[availableIndex% table.length] != null) {
            availableIndex = availableIndex + 1;
        }
        return hash(availableIndex);
    }

    private static class HashEntry {

        private final int key;
        private String value;

        public HashEntry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class DeleteEntry extends HashEntry {
        private static DeleteEntry entry;

        public DeleteEntry() {
            super(-1,"");
        }

        public static DeleteEntry getUniqueDeleteEntry() {
            if (entry == null) {
                entry = new DeleteEntry();
            }

            return entry;
        }


    }
}
