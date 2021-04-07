package com.MIF50;

import com.MIF50.search.Search;

public class Main {

    public static void main(String[] args) {
        int[] array = {3,5,6,9,11,18,20,21,24,30 };
        var search = new Search();
        var index = search.exponentialSearch(array,30);
        System.out.println(index);
    }
}
