import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(TC --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            long[] dist = new long[N + 1];
            Arrays.fill(dist, 0); // 모든 노드를 출발 노드로 설정
            List<Edge> edges = new ArrayList<>(M);

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            for(int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, -T));
            }

            boolean hasNegativeCycle = bellmanFord(N, edges, dist);
            sb.append(hasNegativeCycle ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }

    private static boolean bellmanFord(int N, List<Edge> edges, long[] dist) {
        for(int i = 0; i < N; i++) {
            boolean updated = false;

            for(Edge e : edges) {
                long nd = dist[e.from] + e.cost;

                if(dist[e.to] > nd) {
                    dist[e.to] = nd;
                    updated = true;

                    if(i == N - 1) {
                        return true;
                    }
                }
            }

            if(!updated) break;
        }
        return false;
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
