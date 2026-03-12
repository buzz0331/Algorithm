import java.util.*;
import java.io.*;

public class Main {

    private static List<Edge>[] adj;
    private static int m, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if(m == 0 && n == 0) break;

            adj = new ArrayList[m];
            for(int i = 0; i < m; i++) {
                adj[i] = new ArrayList<>();
            }

            int totalCost = 0;
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                adj[x].add(new Edge(y, c));
                adj[y].add(new Edge(x, c));
                totalCost += c;
            }

            sb.append(totalCost - prim()).append("\n");
        }

        System.out.print(sb);
    }

    private static int prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[m];
        int sum = 0;
        int count = 0;

        pq.offer(new Edge(0, 0));

        while(!pq.isEmpty()) {
            Edge current = pq.poll();

            if(visited[current.to]) continue;
            visited[current.to] = true;
            sum += current.cost;
            count++;
            if(count == m) break;

            for(Edge next : adj[current.to]) {
                if(!visited[next.to]) pq.offer(next);
            }
        }

        return sum;
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
