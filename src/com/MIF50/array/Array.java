package com.MIF50.array;

public class Array {

    private final static int ITEM_NOT_FOUND = -1;
    private int[] items;
    private int count;

    public Array(int length) {
        items = new int[length];
        count = 0;
    }

    public void insert(int item) {
        if (isFull())
            resizeArray();

        items[count++] = item;
    }

    private void resizeArray() {
        int [] copyItems = new int[count * 2];
        for (int i=0; i<count; i++)
            copyItems[i] = items[i];
        items = copyItems;
    }

    public void removeAt(int index) {
        if (!isValidIndex(index))
            throw new IllegalArgumentException("\"Index = \"+index + \" Size = \"+ count");

        for (int i=index; i<count-1; i++)
            items[i] = items[i+1];

        count--;
    }

    public int indexOf(int item) {
        for (int i=0; i<count; i++) {
            if (items[i] == item)
                return i;
        }
        return ITEM_NOT_FOUND;
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
        }
    }

    public int max() {
        if (isEmpty())
            throw new IllegalArgumentException();

        int max = items[0];
        for (int i=1; i<count ;i++) {
            if (items[i] > max)
                max = items[i];
        }
        return max;
    }

    public Array intersect(int[] array) {
        if (isEmpty())
            return new Array(0);

        var intersectArray = new Array(1);
        for (var item : array) {
            if (contains(item))
                intersectArray.insert(item);
        }
        return intersectArray;
    }

    public void reverse() {
        var reverseArray = new int[count];
        var length = count-1;
        for (int i=count-1;i>=0;i--)
            reverseArray[length-i] = items[i];
        items = reverseArray;
    }

    public void insertAt(int index, int item) {
        if (!isValidIndex(index))
            throw new IllegalArgumentException("Index = "+index + " Size = "+ count);

        if (isFull())
            resizeArray();

        for (int i=count-1;i>=index;i--)
            items[i+1]=items[i];

        items[index] = item;
        count++;
    }

    private boolean contains(int item) {
        for (int i=0;i<count;i++) {
            if (items[i]==item)
                return true;
        }
        return false;
    }

    private boolean isFull() {
        return count >= items.length;
    }

    private boolean isEmpty() {
        return count == 0;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index <= count;
    }

}
