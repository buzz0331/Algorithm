import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N + 1];
        int[] indegree = new int[N + 1];
        List<Integer>[] adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            indegree[b]++;
            adj[a].add(b);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) pq.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while(true) {
            if(pq.isEmpty()) { // 비었을 때 모든 문제집 방문했는지 탐색
                for(int i = 1; i <= N; i++) {
                    if(!visited[i]) {
                        pq.offer(i);
                        break;
                    }
                }
                break; // 모든 문제집 탐색 완료
            }

            Integer num = pq.poll();
            sb.append(num).append(" ");
            for(int a : adj[num]) {
                if(--indegree[a] == 0) {
                    pq.offer(a);
                }
            }

        }
        System.out.println(sb);
    }
}
