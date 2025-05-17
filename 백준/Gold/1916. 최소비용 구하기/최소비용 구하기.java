import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        graph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())].add(
                    new Node(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] dist = dijkstra(start, end);
        bw.write(String.valueOf(dist[end]));
        bw.flush(); bw.close();
    }

    private static int[] dijkstra(int start, int end) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int currentIdx = current.index;

            if(visited[currentIdx]) continue;
            visited[currentIdx] = true;

            if(currentIdx == end) break;

            for(Node neighbor : graph[currentIdx]){
                int newDist = dist[currentIdx] + neighbor.cost;
                if (dist[neighbor.index] > newDist) {
                    dist[neighbor.index] = newDist;
                    pq.offer(new Node(neighbor.index, newDist));
                }
            }
        }

        return dist;
    }

    private static class Node implements Comparable<Node> {
        public int index;
        public int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
