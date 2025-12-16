import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final long INF = Long.MAX_VALUE / 4;
    private static long[] dist;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>(M);
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        dist = new long[N + 1];
        StringBuilder sb = new StringBuilder();

        if (bellmanFord(edges, 1, N)) {
            for(int i = 2; i <= N; i++) {
                long d = dist[i];
                if(d == INF) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(d).append("\n");
                }
            }
        } else {
            sb.append(-1);
        }

        System.out.print(sb);
    }

    private static boolean bellmanFord(List<Edge> edges, int start, int N) {
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for(int i = 0; i < N; i++) {
            boolean updated = false;

            for(Edge e : edges) {
                if(dist[e.from] >= INF / 2) continue; // 아직 도달하지 않은 노드

                long nd = dist[e.from] + e.cost;
                if(dist[e.to] > nd) {
                    dist[e.to] = nd;
                    updated = true;
                }
            }

            if(!updated) break;
            if(i == N - 1) return false; // 음수 사이클 존재
        }

        return true;
    }

    private static class Edge {
        public int from;
        public int to;
        public int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
