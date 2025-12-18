import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        List<Integer>[] graphs = new ArrayList[N + 1];
        int[] indegree = new int[N + 1];
        Arrays.setAll(graphs, i -> new ArrayList<>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graphs[A].add(B);
            indegree[B]++;
        }

        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) queue.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            int student = queue.poll();
            sb.append(student).append(" ");

            for(Integer next : graphs[student]) {
                indegree[next]--;
                if(indegree[next] == 0) queue.offer(next);
            }
        }

        System.out.print(sb);
    }
}
