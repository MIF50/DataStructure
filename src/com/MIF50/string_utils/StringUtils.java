package com.MIF50.string_utils;

import java.util.*;

public class StringUtils {

    public int countVowels(String input) {
        if (input == null)
            return 0;

        List<Character> VOWELS = Arrays.asList('a','e','o','u','i');
        int count = 0;
        for (var ch: input.toLowerCase().toCharArray()){
            if (VOWELS.contains(ch))
                count++;
        }
        return count;
    }

    public String reverse(String input) {
        if (input == null)
            return "";

        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (var ch: input.toCharArray())
            stack.push(ch);

        while (!stack.isEmpty())
            result.append(stack.pop());

        return result.toString();
    }

    public String reverseSentence(String input) {
        if (input == null)
            return "";

        String[] words = input.trim().split(" ");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ",words);
//        Stack<String> stack = new Stack<>();
//        for (var word: words)
//            stack.push(word);
//        StringBuilder reversed = new StringBuilder();
//        while (!stack.isEmpty()) {
//            reversed.append(stack.pop());
//            reversed.append(" ");
//        }
//
//        return reversed.toString().trim();

    }

    public boolean areRotations(String input, String expected) {
        if (input == null || expected == null)
            return false;

        String temp = input + input;
        return input.length() == expected.length() &&
                temp.contains(expected);
    }

    public String removeDuplicate(String input) {
        if (input == null)
            return "";

        StringBuilder result = new StringBuilder();
        Set<Character> seen = new HashSet<>();
        for (var ch : input.toCharArray()) {
            if (!seen.contains(ch)) {
                seen.add(ch);
                result.append(ch);
            }
        }

        return result.toString();
    }

    public Character mostRepeated(String input) {
        if (input == null || input.isEmpty())
            throw new IllegalArgumentException();

        final int ASCII_SIZE = 256;
        int[] frequencies = new int[ASCII_SIZE];
        for (var ch : input.toCharArray()) {
            frequencies[ch]++;
        }

        int max = Integer.MIN_VALUE;
        char result = ' ';
        for (int i=0; i<frequencies.length;i++) {
            if (frequencies[i] > max) {
                max = frequencies[i];
                result = (char) i;
            }
        }
        return result;
    }
}
