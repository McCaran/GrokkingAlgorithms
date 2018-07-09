package ai152.Heorhiev;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Класс, реализующий граф
 * основой реализации является хэш-карта, ключом которой является узел(вершина)
 * а значения для этого узла - множество его соседей
 * содержит методы для добавления и получения узлов, ребер
 * а так же тестовый метод для проверки работы графа
 */

public class Graph {
    //Ключом является узел, значением - набор соседних элементов
    private HashMap<Node, HashSet<Node>> graph = new HashMap<>();

    public void addNode(Node node) {
        if (graph.containsKey(node)) {
            System.out.println("Узел " + node.getName() + " уже есть в графе");
            return;
        }
        graph.put(node, new HashSet<>());
    }

    public void addNodes(Node[] nodes) {
        if (nodes.length == 0) {
            System.out.println("Элементы отсутствуют");
            return;
        } else {
            for (Node node : nodes) {
                if (node != null) addNode(node);
            }
        }
    }

    public void addEdge(Node node, Node relative) {
        if (!graph.containsKey(node)) {
            System.out.println("Узел " + node.getName() + " отсутствует в графе");
            return;
        } else if (!graph.containsKey(relative)) {
            System.out.println("Узел " + relative.getName() + " отсутствует в графе");
            return;
        } else if (graph.get(node).contains(relative)) {
            System.out.println("Такое ребро уже есть");
        }
        graph.get(node).add(relative);
        graph.get(relative).add(node);
    }

    public HashSet<Node> getRelatives(Node node) {
        HashSet hashSet;
        return graph.get(node);
    }

    public HashSet<Node> getNodes() {
        return new HashSet<>(graph.keySet());
    }

    public boolean containsNode(Node node) {
        return graph.containsKey(node);
    }

    //Метод для теста работы графа
    public static void testGraph() {
        System.out.println("Пытаемся вывести граф до заполнения");
        Graph graph = new Graph();
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        System.out.println(graph);
        graph.addNode(nodeA);
        Node[] nodes = {nodeB, nodeC, nodeD, nodeE,};
        graph.addNodes(nodes);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addEdge(nodeA, nodeB);
        graph.addEdge(nodeA, nodeC);
        graph.addEdge(nodeA, nodeD);
        graph.addEdge(nodeC, nodeD);
        graph.addEdge(nodeD, nodeE);
        System.out.println("Выводим граф после заполнения");
        System.out.println(graph);
        BreadthFirstSearch breadthSearch = new BreadthFirstSearch(graph);
        System.out.println("Путь в ширину: " + breadthSearch.search(nodeE, nodeB));
        System.out.println("Путь в ширину: " + breadthSearch.search(nodeB, nodeE));
        DepthFirstSearch depthSearch = new DepthFirstSearch(graph);
        depthSearch.startSearch(nodeE, nodeB);
        depthSearch.startSearch(nodeB, nodeE);
    }

    @Override
    public String toString() {
        if (graph.isEmpty()) {
            return "Граф пуст";
        }
        StringBuilder sb = new StringBuilder();
        for (Node node : graph.keySet()) {
            sb.append("Узел " + node.getName() + ".");
            if (graph.get(node).isEmpty()) {
                sb.append("Соседи отсутствуют\n");
            } else {
                sb.append("Соседи: ");
                for (Node relative : graph.get(node)) {
                    sb.append(" " + relative.getName());
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}
