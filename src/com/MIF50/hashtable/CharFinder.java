package com.MIF50.hashtable;

import java.util.HashMap;
import java.util.HashSet;

public class CharFinder {

    // a green apple -> should return (g)
    public char findFirstNonRepeatingChar(String input) {
        var hash = new HashMap<Character,Integer>();
        var chars = input.toCharArray();

        for (char ch : chars) {
            var count = hash.getOrDefault(ch, 0);
            hash.put(ch,count+1);
        }

        for (char ch : chars) {
            if (hash.get(ch) == 1) return ch;
        }

        return Character.MIN_VALUE;
    }

    //green apple -> should return (e)
    public char findFirstRepeatedChar(String input) {
        var set = new HashSet<Character>();

        for (char ch : input.toCharArray()) {
            if (set.contains(ch)) return ch;

            set.add(ch);
        }

        return Character.MIN_VALUE;
    }
}
