import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static final Queue<Node> queue = new LinkedList<>();
    private static final int ROOT = 1;
    private static int[] visited;

    private static class Node {
        public int value;
        public int parent;

        public Node(int value, int parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visited = new int[N + 1];
//        visited[1] = 0;

        ArrayList<Integer>[] graph = new ArrayList[N + 1];  //인접 행렬 그래프
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            visited[i+1] = 0;

//            graph[v1][v2] = true;
//            graph[v2][v1] = true;
            graph[v1].add(v2);
            graph[v2].add(v1);

//            if (min == ROOT) {
//                queue.offer(new Node(max, 1, ROOT));
//            }
        }

        queue.offer(new Node(1, Integer.MAX_VALUE));
        bfs(graph);

        for (int i = 2; i < N+1; i++) {
            System.out.println(visited[i]);
        }

    }

    public static void bfs(ArrayList<Integer>[] graph) {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited[node.value] = node.parent;
//
//            for (int i = 2; i < N + 1; i++) {
//                if (graph[node.value][i] && visited[i]==0) {
//                    queue.offer(new Node(i, node.height + 1, node.value));
//                }
//            }
            for (Integer i : graph[node.value]) {
                if (visited[i] == 0) {
                    queue.offer(new Node(i, node.value));
                }
            }
        }
    }
}
