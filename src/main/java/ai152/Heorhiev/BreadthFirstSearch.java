package ai152.Heorhiev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Класс, реализующий методы поиска в ширину с графом
 * содержит основной метод поиска и вспомогательрный метод для построения
 * после окончания поиска
 */

public class BreadthFirstSearch {
    private Graph graph;
    private ArrayList<Node> visitedNodes = new ArrayList<>();
    //Ключ - узел, значение - родитель
    private HashMap<Node, Node> parentage = new HashMap<>();

    public BreadthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public LinkedList<Node> search(Node startNode, Node endNode) {
        visitedNodes.clear();
        parentage.clear();
        parentage.put(startNode, null);
        if (startNode.equals(endNode)) {
            System.out.println("Введены два одинаковых узла");
            return null;
        } else if (!graph.containsNode(startNode) || !graph.containsNode(endNode)) {
            System.out.println("Одного из узлов нет в графе");
            return null;
        }
        Node curNode = startNode;
        visitedNodes.add(curNode);
        while (!curNode.equals(endNode)) {
            for (Node node: graph.getRelatives(curNode)) {
                if (!parentage.containsKey(node)) {
                    visitedNodes.add(node);
                    parentage.putIfAbsent(node, curNode);
                }
            }
            visitedNodes.remove(0);
            curNode = visitedNodes.get(0);
        }
        if (parentage.containsKey(endNode)) {
            return buildPath(endNode);
        }
        return null;
    }

    private LinkedList<Node> buildPath(Node endNode) {
        Node curNode = endNode;
        LinkedList<Node> path = new LinkedList<>();
        while (curNode != null) {
            path.addFirst(curNode);
            curNode = parentage.get(curNode);
        }
        return path;
    }
}
