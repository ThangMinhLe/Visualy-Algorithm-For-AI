package ttntupdate;
import java.util.*;
import java.io.*;


class Edge {
    int to, weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class Node implements Comparable<Node> {
    int vertex, cost;

    Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class AStarAlgorithm {
    private static List<List<Edge>> adjList;
    private static List<Integer> heuristic;

    public static int AStar(int start, int end) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        int[] g = new int[adjList.size()];
        int[] f = new int[adjList.size()];
        int[] prev = new int[adjList.size()];
        Arrays.fill(g, Integer.MAX_VALUE);
        Arrays.fill(f, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        Map<Integer, Boolean> inOpenSet = new HashMap<>();
        Map<Integer, Boolean> inCloseSet = new HashMap<>();

        g[start] = 0;
        f[start] = heuristic.get(start);
        openSet.add(new Node(start, f[start]));
        inOpenSet.put(start, true);

        while (!openSet.isEmpty()) {
            int current = openSet.poll().vertex;
            inOpenSet.put(current, false);

            if (current == end) {
                int totalCost = g[end];
                List<Integer> path = new ArrayList<>();
                while (current != -1) {
                    path.add(current);
                    current = prev[current];
                }
                Collections.reverse(path);
                System.out.print("Shortest path: ");
                for (int v : path) {
                    System.out.print(v + " ");
                }
                System.out.println("\nPath cost: " + totalCost);
                return totalCost;
            }

            inCloseSet.put(current, true);

            for (Edge edge : adjList.get(current)) {
                int neighbor = edge.to;
                int tentative_g = g[current] + edge.weight;

                if (!inCloseSet.getOrDefault(neighbor, false) && tentative_g < g[neighbor]) {
                    prev[neighbor] = current;
                    g[neighbor] = tentative_g;
                    f[neighbor] = g[neighbor] + heuristic.get(neighbor);

                    if (!inOpenSet.getOrDefault(neighbor, false)) {
                        openSet.add(new Node(neighbor, f[neighbor]));
                        inOpenSet.put(neighbor, true);
                    }
                }
            }
        }

        return -1; // No path found
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("input.txt");
            Scanner scanner = new Scanner(inputFile);
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int s = scanner.nextInt();
            int t = scanner.nextInt();

            adjList = new ArrayList<>();
            heuristic = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adjList.add(new ArrayList<>());
                heuristic.add(0);
            }

            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                adjList.get(u).add(new Edge(v, w));
            }

            for (int i = 1; i <= n; i++) {
                heuristic.set(i, scanner.nextInt());
            }

            scanner.close();
            AStar(s, t);
        } catch (FileNotFoundException e) {
            System.err.println("Error opening input file");
            e.printStackTrace();
        }
    }
}
// A -> K : 1-> 10
// dòng đầu tiên là số đỉnh - số cạnh - đỉnh start - đỉnh end
// dòng cuối cùng là h(đỉnh)
// các dòng ở giữa: đỉnh đầu - đỉnh cuối - trọng số
// input.txt
// 10 14 1 2
// 1 3 9
// 1 4 7
// 1 6 20
// 3 8 6
// 4 8 8
// 4 5 4
// 6 7 4
// 8 10 5
// 5 10 4
// 5 9 3
// 6 9 6
// 9 10 9
// 9 2 5
// 10 2 6
// 14 0 15 6 8 7 12 10 4 2

