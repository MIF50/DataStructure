package com.MIF50.graph;

import java.util.*;

public class Graph {

    private final Map<String,Node> nodes = new HashMap<>();
    private final Map<Node, List<Node>> adjacencyList = new HashMap<>();

    public void insert(String Label) {
        var node = new Node(Label);
        nodes.putIfAbsent(Label,node);
        adjacencyList.putIfAbsent(node,new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        var fromNode = validateNode(from);
        var toNode = validateNode(to);
        adjacencyList.get(fromNode).add(toNode);
    }

    public void traverseDepthFirst(String label) {
        var node =  nodes.get(label);
        if (node == null) return;
        traverseDepthFirst(node, new HashSet<>());
    }

    private void traverseDepthFirst(Node node, Set<Node> visited) {
        System.out.println(node);
        visited.add(node);
        for (var neighbour :adjacencyList.get(node)) {
            if (!visited.contains(neighbour))
                traverseDepthFirst(neighbour,visited);
        }
    }

    public void print() {
        for (var source: adjacencyList.keySet()) {
            var targets = adjacencyList.get(source);
            if (!targets.isEmpty())
                System.out.println(source + " is connected to " +targets);
        }
    }

    private Node validateNode(String label) {
        var node = nodes.get(label);
        if (node == null)
            throw new IllegalArgumentException();
        return node;
    }

    private static class Node {

        private final String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }
}
