import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int V, E;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        krusukal();
//        prim();
    }

    private static void krusukal() throws IOException {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] parent = new int[V + 1];

        for(int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            pq.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int edgeCount = 0;
        long weightCount = 0;
        
        while(!pq.isEmpty() && edgeCount < V - 1) {
            Edge edge = pq.poll();

            if(union(edge.x, edge.y, parent)) {
                weightCount += edge.weight;
                edgeCount++;
            }
        }

        System.out.print(weightCount);
    }

    private static int find(int x, int[] parent) {
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x], parent);
    }

    private static boolean union(int x, int y, int[] parent) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);

        if(rootX == rootY) return false;

        if(rootX < rootY) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
        }

        return true;
    }

    private static class Edge implements Comparable<Edge> {
        public int x;
        public int y;
        public int weight;

        Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    private static void prim() throws IOException {
        List<Node>[] nodes = new ArrayList[V + 1];

        for(int i = 0; i < V + 1; i++) {
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodes[x].add(new Node(y, weight));
            nodes[y].add(new Node(x, weight));
        }

        Queue<Node> pq = new PriorityQueue<>();
        long weightCount = 0;
        boolean[] visited = new boolean[V + 1];
        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visited[node.to]) continue;

            visited[node.to] = true;
            weightCount += node.weight;

            for (Node next : nodes[node.to]) {
                if(!visited[next.to]) pq.offer(next);
            }
        }

        System.out.print(weightCount);
    }

    private static class Node implements Comparable<Node> {
        public int to;
        public int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
