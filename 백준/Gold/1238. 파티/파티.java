import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        while(M --> 0) {
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())].add(
                    new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
            );
        }

        int[][] dist = dijkstra(N);

        System.out.println(findMaxStudent(dist, X));
    }

    private static int[][] dijkstra(int N){
        int[][] dist = new int[N + 1][N + 1];

        for(int i = 1; i < N + 1; i++){ //각 학생을 시작점으로 반복
            boolean[] visited = new boolean[N + 1];
            PriorityQueue<Node> pq = new PriorityQueue<>();

            Arrays.fill(dist[i], Integer.MAX_VALUE);
            pq.offer(new Node(i, 0));
            dist[i][i] = 0;

            while(!pq.isEmpty()) {
                Node current = pq.poll();
                int currentIdx = current.index;

                if(visited[currentIdx]) continue;
                visited[currentIdx] = true;

                for(Node neighbor : graph[currentIdx]) {
                    int newDist = dist[i][currentIdx] + neighbor.cost;
                    if(dist[i][neighbor.index] > newDist) {
                        dist[i][neighbor.index] = newDist;
                        pq.offer(new Node(neighbor.index, newDist));
                    }
                }
            }
        }

        return dist;
    }

    private static int findMaxStudent(int[][] dist, int X) {
        int maxValue = -1;

        for(int i = 1; i < dist.length; i++) {
            if(i == X) continue;
            maxValue = Math.max(maxValue, dist[i][X] + dist[X][i]);
        }

        return maxValue;
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
