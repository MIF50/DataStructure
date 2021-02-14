package com.MIF50.tries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// lookup O(L)
// insert O(L)
// delete O(L)

public class Trie {

    private final Node root = new Node(' ');

    public void insert(String word) {
        var current = root;
        for(char ch: word.toCharArray()){
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean contains(String word) {
        if (word == null) return false;

        var current = root;
        for (char ch: word.toCharArray()){
            if (!current.hasChild(ch)) return false;
            current = current.getChild(ch);
        }

        return current.isEndOfWord;
    }

    public boolean containsRecursive(String words) {
        if (words == null) return false;
        return containsRecursive(root,words,0);
    }

    private boolean containsRecursive(Node root,String word,int index) {
        if (index == word.length())
            return root.isEndOfWord;

        var ch = word.charAt(index);
        var child = root.getChild(ch);
        if (child == null) return false;

       return containsRecursive(child,word,index + 1);
    }

    public void traversal(){
        traversal(root);
    }

    private void traversal(Node root) {
        // pre-order traversal
        
//        System.out.println(root.value);
//        for (var child: root.getChildren()){
//            traversal(child);
//        }

        // post-order traversal
        for (var child: root.getChildren()){
            traversal(child);
        }
        System.out.println(root.value);
    }

    public void remove(String word) {
        if (word == null) return;
        remove(root,word,0);
    }

    private void remove(Node root, String word,int index) {
        if (index == word.length()){
            root.isEndOfWord = false;
            return;
        }

        var ch = word.charAt(index);
        var child = root.getChild(ch);
        if (child == null) return;
        remove(child,word,index + 1);

        if (!child.hasChildren() && !child.isEndOfWord)
            root.removeChild(ch);

    }

    public List<String> findWords(String prefix) {
        List<String> words = new ArrayList<>();
        var lastNode = findLastNodeOf(prefix);
        findWords(lastNode,prefix,words);
        return words;
    }

    private Node findLastNodeOf(String prefix) {
        if (prefix == null) return null;
        var current = root;
        for (char ch: prefix.toCharArray()){
            var child = current.getChild(ch);
            if (child == null) return null;
            current = child;
        }
        return current;
    }

    private void findWords(Node root, String prefix,List<String> words) {
        if (root == null) return;

        if (root.isEndOfWord)
            words.add(prefix);

        for (Node child: root.getChildren()){
            findWords(child,prefix+child.value,words);
        }
    }

    public int countWords() {
        var counts = new ArrayList<Integer>();
        countWords(root,counts);
        return counts.size();
    }

    private void countWords(Node root, ArrayList<Integer> counts) {
        if (root == null) return;

        if (root.isEndOfWord)
            counts.add(0);

        for (var child: root.getChildren()){
            countWords(child,counts);
        }
    }

    public String longestCommonPrefix() {
        var current = root;
        var prefix = new StringBuilder();
        while (current.getChildren().length == 1 && !current.isEndOfWord) {
            for (var child: current.getChildren()){
                prefix.append(child.value);
                current = child;
            }
        }
        return prefix.toString();
    }

    // Node class
    private static class Node {
        private final char value;
        private final HashMap<Character,Node> children = new HashMap<>();
        private boolean isEndOfWord;

        public Node(char value){
            this.value = value;
        }

        public boolean hasChild(char ch){
            return children.containsKey(ch);
        }

        public void addChild(char ch){
            children.put(ch, new Node(ch));
        }

        public Node getChild(char ch){
            return children.get(ch);
        }

        public Node[] getChildren(){
            return children.values().toArray(new Node[0]);
        }

        public boolean hasChildren(){
            return !children.isEmpty();
        }

        public void removeChild(char ch){
            children.remove(ch);
        }

        @Override
        public String toString() {
            return "value=" + value ;
        }
    }
}
