import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    //컴퓨터 수
        int M = Integer.parseInt(br.readLine());    //네트워크 상에서 연결되어 있는 컴퓨터 쌍의 수

        List<List<Integer>> graph = new ArrayList<>(N);
        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }

        boolean[] BFS_visited = new boolean[N+1];
        boolean[] DFS_visited = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(e).add(v);
            graph.get(v).add(e);
        }

        int count = 0;

        count = findByDFS(graph, BFS_visited, count, 1);
        System.out.println(count);
//        int count = findByBFS(graph, DFS_visited);

    }

    private static int findByDFS(List<List<Integer>> graph, boolean[] visited, int count, int start) {
        visited[start] = true;
        List<Integer> E = graph.get(start);

        for (int i : E) {
            if (!visited[i]) {
                count = findByDFS(graph, visited, count, i);
                count++;
            }
        }
        return count;
    }

    private static int findByBFS(List<List<Integer>> graph, boolean[] dfsVisited) {
        return 0;
    }

}
