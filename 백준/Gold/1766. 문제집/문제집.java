import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 문제의 수
        int M = Integer.parseInt(st.nextToken()); // 문제 순서 정보

        List<Integer>[] problems = new ArrayList[N + 1];
        int[] indegree = new int[N + 1];
        Arrays.setAll(problems, i -> new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 우선순위 높은 수
            int y = Integer.parseInt(st.nextToken()); // 우선순위 낮은 수

            problems[x].add(y);
            indegree[y]++;
        }

        putZeroIndegreeNode(indegree);
        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()) {
            Integer node = pq.poll();
            sb.append(node).append(" ");

            for(Integer next : problems[node]) {
                indegree[next]--;
                if(indegree[next] == 0) pq.offer(next);
            }
        }
        System.out.print(sb);
    }

    private static void putZeroIndegreeNode(int[] indegree) {
        for(int i = 1; i < indegree.length; i++) {
            if(indegree[i] == 0) pq.offer(i);
        }
    }

}
