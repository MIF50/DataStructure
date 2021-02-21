package com.MIF50.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    private Node root;
    private int count;

    public BinaryTree(){}

    public void insert(int value) {
        var node = new Node(value);
        if (isEmpty()) {
            root = node;
            count++;
            return;
        }

        var current = root;
        while (true){
            if (value < current.value) {
                if (current.leftChild == null){
                    current.leftChild = node;
                    count++;
                    break;
                }
                current = current.leftChild;
            } else {
                if (current.rightChild == null){
                    current.rightChild = node;
                    count++;
                    break;
                }
                current = current.rightChild;
            }
        }
    }

    public boolean find(int value) {
        var current = root;
        while (current != null){
            if (value < current.value)
                current = current.leftChild;
            else if (value > current.value)
                current = current.rightChild;
            else
                return true;
        }
        return false;
    }

    public void traversePreOrder() {
        var list = new ArrayList<Integer>();
        traversePreOrder(root,list);
        System.out.println(list);
    }

    public void traverseInOrder() {
        var list = new ArrayList<Integer>();
        traverseInOrder(root,list);
        System.out.println(list);
    }

    public void traversePostOrder(){
        traversePostOrder(root);
    }

    public void traverseLevelOrder() {
        var list = new ArrayList<Integer>();
        for (int i = 0; i <= height(); i++)
            list.addAll(getNodesAtDistance(i));
        System.out.println(list);
    }

    public int height(){
        return height(root);
    }

    public int minBinaryTree() {
        if (isEmpty()) throw new IllegalStateException("Your Tree is Empty");

        return minBinarySearchTree();
    }

    public boolean equals(BinaryTree other) {
        if (other == null) return false;
        return equals(other.root,root);
    }

    public boolean isBinarySearchTree(){
        return isBinarySearchTree(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    public ArrayList<Integer> getNodesAtDistance(int level){
        var list = new ArrayList<Integer>();
        getNodesAtDistance(root,level,list);
        return list;
    }

    public void swapRoot() {
        var temp = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = temp;
    }

    // TODO: 2/14/21 Exercise
    public int size() {
        return count;
    }

    public int countLeaves() {
        var leaves = new ArrayList<Integer>();
        countLeaves(root,leaves);
        return leaves.size();
    }

    public int maxBinaryTree() {
        return maxSearchBinaryTree();
    }

    public boolean contain(int value) {
        if (root == null) return false;
        return contain(root,value);
    }

    public boolean isSibling(int first,int second) {
        var firstParent = parentFor(first);
        var secondParent = parentFor(second);
        return secondParent != null && firstParent == secondParent;
    }

    public List<Integer> getAncestors(int value) {
        var list = new ArrayList<Integer>();
        getAncestors(value,list);
        return list;
    }

    private void getAncestors(int value,ArrayList<Integer> list) {
        var current = root;
        var last = current;
        while (current != null) {
            if (current.value > value) {
                list.add(current.value);
                last = current;
                current = current.leftChild;
            } else {
                list.add(current.value);
                last = current;
                current = current.rightChild;
            }
        }
        if (last!= null && last.value != value)
            list.clear();
    }

    private Node parentFor(int value) {
        var current = root;
        Node parent = null;
        while (current != null) {
            if (current.value > value) {
                parent = current;
                current = current.leftChild;
            } else if (current.value < value) {
                parent = current;
                current = current.rightChild;
            } else
                return parent;
        }

        return null;
    }

    private boolean contain(Node root,int value) {
        if (root.value == value) return true;
        if (root.leftChild != null && contain(root.leftChild, value))
            return true;
        if (root.rightChild != null && contain(root.rightChild, value))
            return true;

        return false;
    }

    // O(n)
    private int maxBinaryTree(Node root) {
        if (root == null) return 0;
        if (isLeaf(root)) return root.value;
        var left = maxBinaryTree(root.leftChild);
        var right = maxBinaryTree(root.rightChild);

        return Math.max(root.value,Math.max(left,right));
    }

    // O(log(n))
    private int maxSearchBinaryTree() {
        if (root == null) return 0;
        var current = root;
        var last = current;
        while (current != null) {
            last = current;
            current = current.rightChild;
        }
        return last.value;
    }

    private void countLeaves(Node root,ArrayList<Integer> countLeaves) {
        if (root == null) return;
        if (isLeaf(root)) {
            countLeaves.add(1);
            return;
        }

        countLeaves(root.leftChild,countLeaves);
        countLeaves(root.rightChild,countLeaves);
    }

    // TODO: 9/28/20 Private Traverse
    private void traversePreOrder(Node root,ArrayList<Integer> list) {
        // Root - Left - Right
        if (root == null) return;
        list.add(root.value);
        traversePreOrder(root.leftChild,list);
        traversePreOrder(root.rightChild,list);
    }

    private void traverseInOrder(Node root,ArrayList<Integer> list) {
        // Left - Root - Right
        if (root == null) return;
        traverseInOrder(root.leftChild,list);
        list.add(root.value);
        traverseInOrder(root.rightChild,list);
    }

    private void traversePostOrder(Node root) {
        // Left - Right - Root
        if (root == null) return;
        traversePostOrder(root.leftChild);
        traversePostOrder(root.rightChild);
        System.out.println(root.value);
    }

    private int height(Node root) {
        if (root == null) return -1;
        if (isLeaf(root)) return 0;
        return 1 + Math.max(height(root.leftChild),height(root.rightChild));
    }

    // TODO: -> O(n) for BinaryTree
    private int minBinaryTree(Node root) {
        if (root == null) return Integer.MAX_VALUE;

        if (isLeaf(root)) return root.value;

        var left = minBinaryTree(root.leftChild);
        var right = minBinaryTree(root.rightChild);

        return Math.min(root.value,Math.min(left,right));
    }

    // TODO: -> O(Log n) Binary Search Tree
    private int minBinarySearchTree() {
        if (root == null)
            throw new IllegalStateException("Your Tree is empty");

        var current = root;
        var last = current;
        while (current != null){
            last = current;
            current = current.leftChild;
        }
        return last.value;
    }

    public boolean isEmpty() { return root == null; }

    private boolean isLeaf(Node node){
        return node.leftChild == null && node.rightChild == null;
    }

    private boolean equals(Node first, Node second) {
        if (first == null && second == null) return true;

        if(first != null && second != null)
            return first.value == second.value
                    && equals(first.leftChild, second.leftChild)
                    && equals(first.rightChild, second.rightChild);

        return false;
    }

    private boolean isBinarySearchTree(Node node, int min, int max) {
        if (node == null) return true;

        if (node.value < min || node.value > max) return false;

        return isBinarySearchTree(node.leftChild,min,node.value-1)
                && isBinarySearchTree(node.rightChild,node.value + 1, max);
    }

    private void getNodesAtDistance(Node node, int level, ArrayList<Integer> list){
        if (node == null) return;

        if (level == 0 ) list.add(node.value);

        getNodesAtDistance(node.leftChild,level-1,list);
        getNodesAtDistance(node.rightChild,level-1,list);
    }

    // TODO: Node
    private static class Node {

        private final int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node="+value;
        }
    }
}
