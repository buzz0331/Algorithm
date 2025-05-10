import java.util.*;
import java.io.*;

public class Main {
    private static ArrayList<Integer>[] graph;
    private static int N;

    private static int[] dist;

    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         int M = Integer.parseInt(st.nextToken());
         int K = Integer.parseInt(st.nextToken());
         int X = Integer.parseInt(st.nextToken());

         graph = new ArrayList[N + 1];
         for(int i = 1; i <= N; i++){
             graph[i] = new ArrayList<>();
         }

         for(int i = 0; i < M; i++){
             st = new StringTokenizer(br.readLine());

             int A = Integer.parseInt(st.nextToken());
             int B = Integer.parseInt(st.nextToken());

             graph[A].add(B);
         }

//        Dijkstra(X);
        BFS(X);
        System.out.print(checkAnswer(K, dist));
    }

    private static class Node implements Comparable<Node>{
        public int number;
        public int cost;

        public Node(int number, int cost){
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }

//    private static void Dijkstra(int start){
//        boolean[] visited = new boolean[N + 1];
//        dist = new int[N + 1];
//        PriorityQueue<Node> pq = new PriorityQueue<>();
//
//        Arrays.fill(dist, Integer.MAX_VALUE);
//
//        pq.offer(new Node(start, 0));
//        dist[start] = 0;
//
//        while(!pq.isEmpty()){
//            Node currentVertex = pq.poll();
//            if (visited[currentVertex.number]) {    //이미 방문한 경우 패스
//                continue;
//            } else {
//                visited[currentVertex.number] = true;
//            }
//
//            for(int adjNode : graph[currentVertex.number]){
//                if(dist[adjNode] > currentVertex.cost + 1){
//                    dist[adjNode] = currentVertex.cost + 1;
//                    pq.offer(new Node(adjNode, currentVertex.cost + 1));
//                }
//            }
//        }
//    }
private static void BFS(int start){
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];
    dist = new int[N + 1];
    Arrays.fill(dist, -1); // -1로 초기화

    queue.offer(start);
    visited[start] = true;
    dist[start] = 0;

    while(!queue.isEmpty()){
        int current = queue.poll();
        for(int next : graph[current]){
            if(!visited[next]){
                visited[next] = true;
                dist[next] = dist[current] + 1;
                queue.offer(next);
            }
        }
    }
}

    private static StringBuilder checkAnswer(int answer, int[] dist) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            if(dist[i] == answer){
                sb.append(i).append("\n");
            }
        }
        if(sb.length() == 0){
            sb.append(-1);
        }
        return sb;
    }
}
