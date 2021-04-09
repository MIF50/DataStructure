package com.MIF50.string_utils;

import java.util.*;

public class StringUtils {

    public static int countVowels(String input) {
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

    public static String reverse(String input) {
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

    public static String reverseSentence(String input) {
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

    public static boolean areRotations(String input, String expected) {
        if (input == null || expected == null)
            return false;

        String temp = input + input;
        return input.length() == expected.length() &&
                temp.contains(expected);
    }

    public static String removeDuplicate(String input) {
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

    public static Character mostRepeated(String input) {
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

    public static String capitalize(String input) {
        if (input == null || input.trim().isEmpty())
            return "";
        
        String[] words = input
                .trim()
                .replaceAll(" +"," ")
                .split(" ");
        for (int i=0;i<words.length;i++) {
            words[i] =  words[i].substring(0,1).toUpperCase()
                    + words[i].substring(1).toLowerCase();
        }

        return String.join(" ",words);
    }

    // O(n log n)
    public static boolean areAnagrams(String input1, String input2) {
        if (input1 == null || input2 == null || input1.length() != input2.length())
            return false;

        var array1 = input1.toLowerCase().toCharArray();
        Arrays.sort(array1);
        var array2 = input2.toLowerCase().toCharArray();
        Arrays.sort(array2);
        return Arrays.equals(array1,array2);
    }

    // O(log n)
    public static boolean areAnagrams2(String input1,String input2) {
        if (input1 == null || input2 == null || input1.length() != input2.length())
            return false;

        final int ENGLISH_ALPHABET = 26;
        int[] frequencies = new int[ENGLISH_ALPHABET];
        input1 = input1.toLowerCase();
        for (int i=0;i<input1.length();i++){
            frequencies[input1.charAt(i) - 'a']++;
        }

        input2 = input2.toLowerCase();
        for (int i=0;i<input2.length();i++){
            var index = input2.charAt(i) - 'a';
            if (frequencies[index] == 0)
                return false;
            frequencies[index]--;
        }
        return true;
    }

    public static boolean isPalindrome(String input) {
        if (input == null)
            return false;

        var left = 0;
        var right = input.length() - 1;
        while (left<right){
            if (input.charAt(left++) != input.charAt(right--))
                return false;
        }
        return true;
    }
}
