package com.MIF50;

import com.MIF50.undirected_graph.WeightedGraph;

public class Main {

    public static void main(String[] args) {
        var graph = new WeightedGraph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        graph.addEdge("A","B",4);
        graph.addEdge("A", "C",1);
        graph.addEdge("B","C",2);
        graph.addEdge("C","D",5);
        graph.addEdge("D", "B",4);

        var tree = graph.getMinimSpanningTree();
        tree.print();


    }
}
