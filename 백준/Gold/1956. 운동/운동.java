import java.util.*;
import java.io.*;

public class Main {

    private static List<Edge>[] adj;
    private static final int INF = 400000000;
    private static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Edge(b, c));
        }

        int min = INF;
        for(int i = 1; i <= V; i++) {
            int result = dijkstra(i);

            if(result != 0) {
                min = Math.min(min, result);
            }
        }

        System.out.print(min == INF ? -1 : min);
    }

    private static int dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[V + 1];

        Arrays.fill(dist, INF);
        pq.offer(new Edge(start, 0));
        dist[start] = 0;
        int count = 0;

        while(!pq.isEmpty()) {
            Edge current = pq.poll();

//            System.out.println("current: " + current.to);

            if(dist[current.to] < current.cost) continue;

            if(current.to == start) {
                if(++count == 2) break;
            }

            for(Edge next : adj[current.to]) {
                int newDist = dist[current.to] + next.cost;

                if(dist[next.to] == 0 || dist[next.to] > newDist) { // 사이클 조건 추가
                    dist[next.to] = newDist;
                    pq.offer(new Edge(next.to, newDist));
                }
            }
        }
        return dist[start];
    }

    private static class Edge implements Comparable<Edge> {
        public int to, cost;

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
