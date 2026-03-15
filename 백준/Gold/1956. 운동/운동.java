import java.util.*;
import java.io.*;

public class Main {

    private static int[][] dist;
    private static final int INF = 400000000;
    private static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[V + 1][V + 1];
        for(int i = 1; i <= V; i++) {
            Arrays.fill(dist[i], INF);
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = c;
        }

        floydWarshall();
        System.out.print(findMinCycle());
    }

    private static void floydWarshall() {
        for(int k = 1; k <= V; k++) {
            for(int i = 1; i <= V; i++) {
                for(int j = 1; j <= V; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    private static int findMinCycle() {
        int min = INF;
        for(int i = 1; i <= V; i++) {
            min = Math.min(min, dist[i][i]);
        }

        return min == INF ? -1 : min;
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
