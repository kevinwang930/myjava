package kevin.project;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
    private Map<String, Map<String, Integer>> graph;

    public Dijkstra() {
        graph = new HashMap<>();
    }

    public void addEdge(String from, String to, int weight) {
        if (!graph.containsKey(from)) {
            graph.put(from, new HashMap<>());
        }
        graph.get(from).put(to, weight);
    }

    public Map<String, Integer> shortestPath(String start) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(
                (a, b) -> distances.get(a) - distances.get(b));

        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            for (String neighbor : graph.get(node).keySet()) {
                int distance = distances.get(node) + graph.get(node).get(neighbor);
                if (distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    queue.add(neighbor);
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.addEdge("A", "B", 1);
        dijkstra.addEdge("A", "C", 4);
        dijkstra.addEdge("B", "C", 2);
        dijkstra.addEdge("B", "D", 5);
        dijkstra.addEdge("C", "D", 1);

        Map<String, Integer> distances = dijkstra.shortestPath("A");
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
