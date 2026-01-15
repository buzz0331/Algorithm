import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<Bus>[] bus;
    private static int n, m;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        bus = new ArrayList[n + 1];
        for(int i = 1; i < bus.length; i++) {
            bus[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            bus[from].add(new Bus(to, cost));
        }

        for(int i = 1; i < n + 1; i++) {
            dijkstra(i);
        }
        System.out.print(sb);
    }

    private static void dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Bus(start, 0));

        while(!pq.isEmpty()) {
            Bus current = pq.poll();

            for(Bus next: bus[current.to]) {
                int newCost = current.cost + next.cost;

                if(dist[next.to] > newCost){
                    dist[next.to] = newCost;
                    pq.offer(new Bus(next.to, newCost));
                }
            }
        }

        for(int i = 1; i < dist.length; i++) {
            if(dist[i] == Integer.MAX_VALUE) sb.append(0).append(" ");
            else sb.append(dist[i]).append(" ");
        }

        sb.append("\n");
    }

    private static class Bus implements Comparable<Bus> {
        public int to;
        public int cost;

        Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
