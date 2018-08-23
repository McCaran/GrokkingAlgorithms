package ai152.Heorhiev;

import java.util.*;

/**
 * Класс, реализующий методы поиска в глубину для графа
 * содержит метод для инициализации поиска и основной рекурсивынй метод поиска
 */

public class DepthFirstSearch {
    private Graph graph;
    private HashSet<Node> visitedNodes = new HashSet<>();
    private Stack<Node> path = new Stack<>();

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public void startSearch(Node startNode, Node endNode) {
        visitedNodes.clear();
        path.clear();
        search(startNode, endNode);
    }

    private void search(Node startNode, Node endNode) {
        path.push(startNode);
        Node curNode = startNode;
        Iterator iterator = graph.getRelatives(startNode).iterator();
        if (!visitedNodes.contains(startNode)) {
            visitedNodes.add(startNode);
        }
        if (curNode.equals(endNode)) {
            System.out.println("Путь в глубину: " + path);
            return;
        }
        while (iterator.hasNext()) {
            curNode = (Node) iterator.next();
            if (!visitedNodes.contains(curNode)) {
                search(curNode, endNode);
            }
        }
        path.pop();
    }
}

