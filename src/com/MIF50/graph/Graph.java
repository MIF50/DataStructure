package com.MIF50.graph;

import kotlin.collections.ArrayDeque;

import java.util.*;

public class Graph {

    private final Map<String,Node> nodes = new HashMap<>();
    private final Map<Node, List<Node>> adjacencyList = new HashMap<>();

    public void addNode(String Label) {
        var node = new Node(Label);
        nodes.putIfAbsent(Label,node);
        adjacencyList.putIfAbsent(node,new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        var fromNode = validateNode(from);
        var toNode = validateNode(to);
        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeNode(String label) {
        // validate node
        var node = nodes.get(label);
        if (node == null)
            return;
        // remove all connection to this node
        for(var neighbour: adjacencyList.keySet())
            adjacencyList.get(neighbour).remove(node);
        // remove node
        adjacencyList.remove(node);
        nodes.remove(node);
    }

    public void removeEdge(String from,String to) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            return;

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void traverseDepthFirst(String label) {
        // validate node
        var node = nodes.get(label);
        if (node == null)
            return;

        var visited = new HashSet<Node>();
        var stack = new Stack<Node>();
        stack.push(node);

        while (!stack.isEmpty()) {
            var current = stack.pop();
            if (visited.contains(current))
                continue;

            // mark it as visited
            System.out.println(current.label);
            visited.add(current);

            // visit all neighbour
            for (var neighbour: adjacencyList.get(current)) {
                if (!visited.contains(neighbour))
                    stack.push(neighbour);
            }
        }
    }

    public void traverseDepthFirstRec(String label) {
        // validate node
        var node =  nodes.get(label);
        if (node == null)
            return;

        traverseDepthFirstRec(node, new HashSet<>());
    }

    private void traverseDepthFirstRec(Node node, Set<Node> visited) {
        // mark it as visited
        System.out.println(node);
        visited.add(node);
        // visit all neighbour
        for (var neighbour :adjacencyList.get(node)) {
            if (!visited.contains(neighbour))
                traverseDepthFirstRec(neighbour,visited);
        }
    }

    public void traverseBreadthFirst(String label) {
        // validate node
        var node = nodes.get(label);
        if (node == null)
            return;

        var visited = new HashSet<Node>();
        var queue = new ArrayDeque<Node>();
        queue.add(node);

        while (!queue.isEmpty()) {
            var current = queue.removeFirst();
            if (visited.contains(current))
                continue;

            // mark it as visited
            System.out.println(current);
            visited.add(current);

            // visit all neighbour
            for (var neighbour: adjacencyList.get(current)) {
                if (!visited.contains(neighbour))
                    queue.add(current);
            }
        }
    }

    public List<String> topologicalSort() {
        var visited = new HashSet<Node>();
        var stack = new Stack<Node>();

        for (var node : nodes.values())
            topologicalSort(node,stack,visited);

        return getListFromStack(stack);
    }

    private void topologicalSort(Node node,Stack<Node> stack,Set<Node> visited) {
        if (visited.contains(node))
            return;

        visited.add(node);

        for (var neighbour: adjacencyList.get(node))
            topologicalSort(neighbour,stack,visited);

        stack.push(node);
    }

    private List<String> getListFromStack(Stack<Node>stack) {
        var sorted = new ArrayList<String>();
        while (!stack.isEmpty())
            sorted.add(stack.pop().label);
        return sorted;
    }

    public boolean hasCycle() {
        var all = new HashSet<>(nodes.values());
        var visiting  = new HashSet<Node>();
        var visited = new HashSet<Node>();

        while (!all.isEmpty()) {
            var current = all.iterator().next();
            if (hasCycle(current,all,visiting,visited))
                return true;
        }

        return false;
    }

    private boolean hasCycle(Node node,Set<Node> all, Set<Node> visiting, Set<Node> visited) {
        all.remove(node);
        visiting.add(node);

        for (var neighbour: adjacencyList.get(node)) {
            if (visited.contains(neighbour))
                continue;

            if (visiting.contains(neighbour))
                return true;

            if (hasCycle(neighbour,all,visiting,visited))
                return true;
        }

        visiting.remove(node);
        visited.add(node);
        return false;
    }

    private Node validateNode(String label) {
        var node = nodes.get(label);
        if (node == null)
            throw new IllegalArgumentException("node " + label + " not found in Graph");
        return node;
    }

    public void print() {
        for (var source: adjacencyList.keySet()) {
            var targets = adjacencyList.get(source);
            if (!targets.isEmpty())
                System.out.println(source + " is connected to " +targets);
        }
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
