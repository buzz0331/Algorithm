import java.util.*;
import java.io.*;

public class Main {
    private static int V;
    private static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); //정점 개수
        int E = Integer.parseInt(st.nextToken()); //간선 개수
        int K = Integer.parseInt(br.readLine()); //시작 정점

        graph = new ArrayList[V + 1];
        for(int i = 0; i <= V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }

        int[] dist = dijkstra(K);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < dist.length; i++){
            if(dist[i] == Integer.MAX_VALUE){
                sb.append("INF").append("\n");
            }else{
                sb.append(dist[i]).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush(); bw.close();
    }

    private static int[] dijkstra(int start){
        int[] dist = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int currentIdx = current.index;

            if(visited[currentIdx]) continue;
            visited[currentIdx] = true;

            for(Node neighbor : graph[currentIdx]){
                int newDist = dist[currentIdx] + neighbor.cost;
                if(dist[neighbor.index] > newDist){
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
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}
