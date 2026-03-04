import java.util.*;
import java.io.*;

public class Main {

    private static List<Edge>[] graph, reverseGraph;
    private static int N, M, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        reverseGraph = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[x].add(new Edge(y, cost));
            reverseGraph[y].add(new Edge(x, cost));
        }

        int[] go = dijkstra(graph);
        int[] back = dijkstra(reverseGraph);

        int max = -1;
        for(int start = 1; start <= N; start++) {
            if(start == X) continue;

            max = Math.max(max, go[start] + back[start]);
        }

        System.out.print(max);
    }

    private static int[] dijkstra(List<Edge>[] graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[X]= 0;
        pq.offer(new Edge(X, 0));

        while(!pq.isEmpty()) {
            Edge current = pq.poll();

            if(visited[current.to]) continue;
            visited[current.to] = true;

            for(Edge next : graph[current.to]) {
                int newDist = dist[current.to] + next.cost;
                if(dist[next.to] > newDist) {
                    dist[next.to] = newDist;
                    pq.offer(new Edge(next.to, newDist));
                }
            }
        }
        return dist;
    }

    private static class Edge implements Comparable<Edge> {
        public int to;
        public int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
