package com.MIF50;

import com.MIF50.avl_tree.AVLTree;


public class Main {

    public static void main(String[] args) {
        var tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(35);
        tree.insert(8);
        tree.insert(15);

        System.out.println(tree.isPrefect());

    }
}
