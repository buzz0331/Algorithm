import java.util.*;
import java.io.*;

public class Main {

    private static List<Edge> edges;
    private static int m, n;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if(m == 0 && n == 0) break;

            parents = new int[m];
            for(int i = 0; i < m; i++) {
                parents[i] = i;
            }
            edges = new ArrayList<>();

            int totalCost = 0;
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                edges.add(new Edge(x, y, c));
                totalCost += c;
            }
            
            Collections.sort(edges);

            sb.append(totalCost - kruskal()).append("\n");
        }

        System.out.print(sb);
    }

    private static int kruskal() {
        int sum = 0;
        int count = 0;

        for (Edge current : edges) {
            if (union(current.from, current.to)) {
                sum += current.cost;
                count++;

                if (count == m - 1) break;
            }
        }

        return sum;
    }

    private static int find(int x) {
        if(parents[x] == x) return x;

        return parents[x] = find(parents[x]);
    }

    private static boolean union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX == parentY) return false;

        if(parentX < parentY) {
            parents[parentY] = parentX;
        } else {
            parents[parentX] = parentY;
        }

        return true;
    }

    private static class Edge implements Comparable<Edge> {
        public int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
