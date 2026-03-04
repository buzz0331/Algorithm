import java.util.*;
import java.io.*;

public class Main {

    private static List<Edge>[] adjacents;
    private static int[][] dist;
    private static int N, M, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjacents = new ArrayList[N + 1];
        dist = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            adjacents[i] = new ArrayList<>();
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjacents[x].add(new Edge(y, cost));
        }

        for(int start = 1; start <= N; start++) {
            dijkstra(start);
        }

        int max = -1;
        for(int start = 1; start <= N; start++) {
            if(start == X) continue;

            max = Math.max(max, dist[start][X] + dist[X][start]);
        }

        System.out.print(max);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        pq.offer(new Edge(start, 0));
        dist[start][start] = 0;

        while(!pq.isEmpty()) {
            Edge current = pq.poll();

            if(visited[current.to]) continue;
            visited[current.to] = true;

            for(Edge next : adjacents[current.to]) {
                int newDist = dist[start][current.to] + next.cost;
                if(dist[start][next.to] > newDist) {
                    dist[start][next.to] = newDist;
                    pq.offer(new Edge(next.to, newDist));
                }
            }
        }
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
