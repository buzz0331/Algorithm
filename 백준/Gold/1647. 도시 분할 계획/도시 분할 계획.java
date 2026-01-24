import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Edge>[] edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges[a].add(new Edge(b, cost));
            edges[b].add(new Edge(a, cost));
        }

        System.out.print(prim(edges, 1));
    }

    private static int prim(List<Edge>[] edges, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        pq.offer(new Edge(start, 0));

        int maxCost = -1; // 현재 MST 집합 내부 간선중 최대 간선 (마을 2개 분리시에 삭제할 간선)
        int costSum = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if(visited[current.to]) continue;

            maxCost = Math.max(maxCost, current.cost); // 최대 간선 갱신
            costSum += current.cost;
            visited[current.to] = true;

            for (Edge edge : edges[current.to]) {
                if (!visited[edge.to]) {
                    pq.offer(edge);
                }
            }
        }

        costSum -= maxCost;
        return costSum;
    }

    private static class Edge implements Comparable<Edge> {
        public int to;
        public int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
