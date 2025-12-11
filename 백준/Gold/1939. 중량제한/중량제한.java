import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<Node>[] neighbors;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        neighbors = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            neighbors[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken()); // 비용

            neighbors[A].add(new Node(B, C));
            neighbors[B].add(new Node(A, C));
        }

        st = new StringTokenizer(br.readLine());

        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        System.out.print(findMaxWeight(from, to));
    }

    private static int findMaxWeight(int from, int to) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 최대 힙
        boolean[] visited = new boolean[neighbors.length];

        pq.offer(new Node(from, Integer.MAX_VALUE));

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(visited[node.to]) continue;
            visited[node.to] = true;

            if(node.to == to) return node.cost; // 목적지 섬에 도달한 경우

            for(Node next : neighbors[node.to]) {
                if(!visited[next.to]) {

                    pq.offer(new Node(next.to, Math.min(node.cost, next.cost))); // 더 작은 비용을 큐에 저장 (경로 상에 더 작은 중량제한을 가진 다리가 존재)
                }
            }
        }

        return -1   ;
    }

    private static class Node implements Comparable<Node> {
        public int to;
        public int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.cost, this.cost);
        }
    }
}
