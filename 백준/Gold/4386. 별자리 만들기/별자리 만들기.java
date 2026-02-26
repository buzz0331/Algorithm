import java.util.*;
import java.io.*;

public class Main {

    private static double[][] stars;
    private static List<Edge>[] adjacent;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        stars = new double[N][2];
        adjacent = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            adjacent[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());

            for(int j = 0; j < i; j++) {
                double w = Math.abs(stars[i][0] - stars[j][0]);
                double h = Math.abs(stars[i][1] - stars[j][1]);

                double distance = Math.sqrt(Math.pow(w, 2) + Math.pow(h, 2));
                adjacent[i].add(new Edge(j, distance));
                adjacent[j].add(new Edge(i, distance));
            }
        }

        System.out.print(String.format("%.2f", prim()));
    }

    private static double prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];

        pq.offer(new Edge(0, 0));
        double sum = 0;

        while(!pq.isEmpty()) {
            Edge current = pq.poll();

            if(visited[current.to]) continue;
            visited[current.to] = true;
            sum += current.cost;

            for(Edge next : adjacent[current.to]) {
                pq.offer(next);
            }
        }
        return sum;
    }

    private static class Edge implements Comparable<Edge> {
        public int to;
        public double cost;

        public Edge(int to, double cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }
}
