package com.MIF50;

import com.MIF50.tree.BinaryTree;

public class Main {

    public static void main(String[] args) {
        var tree = new BinaryTree();
        tree.insert(7);
        tree.insert(4);
        tree.insert(10);
        tree.insert(8);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);

        System.out.println(tree.getAncestors(9));
    }

}
