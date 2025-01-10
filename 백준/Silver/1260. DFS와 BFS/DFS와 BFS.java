import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //정점의 개수
        int M = Integer.parseInt(st.nextToken()); //간선의 개수
        int V = Integer.parseInt(st.nextToken()); //탐색을 시작할 정점의 번호


        boolean[] DFS_visited = new boolean[N+1];
        boolean[] BFS_visited = new boolean[N+1];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        DFS(graph, DFS_visited, V);
        System.out.println();
        BFS(graph, BFS_visited, V);

    }

    private static void DFS(List<List<Integer>> graph, boolean[] visited, int v) {
        visited[v] = true;
        List<Integer> E = graph.get(v);
        Collections.sort(E);

        System.out.print(v + " ");

        for (int i : E) {
            if (!visited[i]) {
                DFS(graph, visited, i);
            }
        }
    }

    private static void BFS(List<List<Integer>> graph, boolean[] visited, int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            Integer i = queue.poll();

            System.out.print(i + " ");

            List<Integer> E = graph.get(i);
            Collections.sort(E);

            for (int j : E) {
                if (!visited[j]) {
                    visited[j] = true;
                    queue.offer(j);
                }
            }
        }
    }
}
