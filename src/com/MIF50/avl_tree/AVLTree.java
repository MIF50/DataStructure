package com.MIF50.avl_tree;

public class AVLTree {

    private AVLNode root;
    private int size;

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(AVLNode root) {
        if (root == null) return true;
        if (isLeftHeavy(root) || isRightHeavy(root))
            return false;

        return isBalanced(root.leftChild) && isBalanced(root.rightChild);
    }

    public boolean isPrefect() {
        return isPrefect(root);
    }

    private boolean isPrefect(AVLNode root) {
        return getSize() == Math.pow(2,height(root) + 1) - 1;
    }
    
    public void insert(int value) {
        root = insert(root,value);
    }

    private AVLNode insert(AVLNode root,int value) {
        if (root == null) {
            size++;
            return new AVLNode(value);
        }

        if (value < root.value)
            root.leftChild = insert(root.leftChild,value);
        else
            root.rightChild = insert(root.rightChild,value);

        setHeight(root);

        return balance(root);
    }

    private AVLNode balance(AVLNode root) {
        if (isLeftHeavy(root)) {
            if (balanceFactor(root.leftChild) < 0)
                root.leftChild = rotateLeft(root.leftChild);
            return rotateRight(root);
        } else if (isRightHeavy(root)) {
            if (balanceFactor(root.rightChild) > 0)
                root.rightChild = rotateRight(root.rightChild);
            return rotateLeft(root);
        }
        return root;
    }

    private AVLNode rotateLeft(AVLNode root) {
        var newRoot = root.rightChild;

        root.rightChild = newRoot.leftChild;
        newRoot.leftChild = root;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private AVLNode rotateRight(AVLNode root) {
        var newRoot = root.leftChild;

        root.leftChild = newRoot.rightChild;
        newRoot.rightChild = root;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private void setHeight(AVLNode node) {
        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
    }

    private boolean isLeftHeavy(AVLNode node){
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(AVLNode node){
        return balanceFactor(node) < -1;
    }

    private int balanceFactor(AVLNode node) {
        return (node == null)? 0 : height(node.leftChild) - height(node.rightChild);
    }

    private int height(AVLNode node){
        return (node == null)? -1 : node.height;
    }

    public int getSize() {
        return size;
    }

    private static class AVLNode {
        private final int value;
        private AVLNode leftChild;
        private AVLNode rightChild;
        private int height;

        public AVLNode(int value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "value="+this.value;
        }
    }
}
