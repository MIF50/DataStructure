package com.MIF50.undirected_graph;

import java.util.*;

public class WeightedGraph {

    private final Map<String,Node> nodes = new HashMap<>();

    public void addNode(String label) {
        nodes.putIfAbsent(label,new Node(label));
    }

    public void addEdge(String from, String to, int weight) {
        var fromNode = validateNode(from);
        var toNode = validateNode(to);
        fromNode.addEdge(toNode,weight);
        toNode.addEdge(fromNode,weight);
    }

    public Path getShortestPath(String from, String to) {
        var fromNode = validateNode(from);
        var toNode = validateNode(to);

        var distances = new HashMap<Node,Integer>();
        for (var node : nodes.values())
            distances.put(node,Integer.MAX_VALUE);
        distances.replace(fromNode,0);

        var previousNodes = new HashMap<Node,Node>();
        var visited = new HashSet<Node>();

        var queue = new PriorityQueue<NodeEntry>(
                Comparator.comparingInt(ne -> ne.priority )
        );
        queue.add(new NodeEntry(fromNode,0));

        while (!queue.isEmpty()) {
            //. mark current as visited node.
            var current = queue.remove().node;
            visited.add(current);

            //. visit all neighbour to current node.
            for (var edge: current.getEdges()) {
                if (visited.contains(edge.to))
                    continue;

                var newDistance = distances.get(current) + edge.weight;
                if (newDistance < distances.get(edge.to)) {
                    distances.replace(edge.to,newDistance);
                    previousNodes.put(edge.to,current);
                    queue.add(new NodeEntry(edge.to, newDistance));
                }
            }
        }

        return buildPath(previousNodes,toNode);
    }

    private Path buildPath(Map<Node,Node> previousNodes,Node toNode) {
        var stack = new Stack<Node>();
        stack.push(toNode);
        var previous = previousNodes.get(toNode);
        while (previous != null) {
            stack.push(previous);
            previous = previousNodes.get(previous);
        }

        var path = new Path();
        while (!stack.isEmpty())
            path.add(stack.pop().label);

        return path;
    }

    public boolean hasCycle() {
        var visited = new HashSet<Node>();
        for (var node : nodes.values()) {
            if (!visited.contains(node) && hasCycle(node,null,visited))
                return true;
        }
        return false;
    }

    private boolean hasCycle(Node node,Node parent,HashSet<Node> visited) {
        visited.add(node);

        for (var edge: node.getEdges()) {
            if (edge.to == parent)
                continue;

            if (visited.contains(edge.to) || hasCycle(edge.to,node,visited))
                return true;
        }
        return false;
    }

    public WeightedGraph getMinimSpanningTree(){
        var tree = new WeightedGraph();
        if (nodes.isEmpty())
            return tree;

        var edges = new PriorityQueue<Edge>(
                Comparator.comparingInt(e-> e.weight)
        );

        var startedNode = nodes.values().iterator().next();
        tree.addNode(startedNode.label);
        edges.addAll(startedNode.getEdges());

        if (edges.isEmpty())
            return tree;

        while (tree.nodes.size() < nodes.size()) {
            var minEdge = edges.remove();
            var nextNode = minEdge.to;

            if (tree.containNode(nextNode.label))
                continue;

            tree.addNode(nextNode.label);
            tree.addEdge(minEdge.from.label,minEdge.to.label,minEdge.weight);

            for (var edge: nextNode.getEdges()) {
                if (!tree.containNode(edge.to.label))
                    edges.add(edge);
            }
        }

        return tree;
    }

    public boolean containNode(String label) {
        return nodes.containsKey(label);
    }

    private Node validateNode(String label) {
        var node = nodes.get(label);
        if (node == null)
            throw new IllegalStateException("your node " +label+ " is invalid");
        return node;
    }

    public void print() {
        for (var node: nodes.values()) {
            var edges = node.getEdges();
            if (!edges.isEmpty())
                System.out.println(node + " is connected to " + edges);
        }
    }

    private static class NodeEntry {

        private final Node node;
        private final int priority;

        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    private static class Node {

        private final String label;
        private final List<Edge> edges = new ArrayList<>();

        public Node(String label) {
            this.label = label;
        }

        public void addEdge(Node to,int weight) {
            edges.add(new Edge(this,to,weight));
        }

        public List<Edge> getEdges() {
            return edges;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private static class Edge {

        private final Node from;
        private final Node to;
        private final int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "->" + to;
        }
    }
}
