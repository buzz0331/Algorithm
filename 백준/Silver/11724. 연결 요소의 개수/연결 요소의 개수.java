import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;

    private static ArrayList<ArrayList<Integer>> graph;
    private static boolean[] visited;

    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(N + 1);
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
                count ++;
                visited[i] = true;
            }
        }

        System.out.println(count);

    }

    private static void dfs(int start) {
        ArrayList<Integer> elements = graph.get(start);

        for (int element : elements) {
            if (!visited[element]) {
                visited[element] = true;
                dfs(element);
            }
        }
    }
}
