package com.MIF50.stack;

import java.util.Stack;

public class StringReverse {

    public String reverse(String input){
        if (input == null)
            throw new IllegalArgumentException();

        var stack = new Stack<Character>();
        for (char ch : input.toCharArray()) {
            stack.push(ch);
        }
        var textReverse = new StringBuilder();
        while (!stack.isEmpty()){
            textReverse.append(stack.pop());
        }
        return textReverse.toString();
    }
}
