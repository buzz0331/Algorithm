import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int K;   //테스트 케이스 개수
    private static int V;   //정점의 개수
    private static int E;   //간선의 개수

    private static StringBuilder sb = new StringBuilder();
    private static ArrayList<ArrayList<Integer>> graph;
    
    private static int[] visited;
    private static boolean result;

    private static final int NO_VISITED = 0;
    private static final int W = 1;
    private static final int B = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());   //정점
            E = Integer.parseInt(st.nextToken());   //간선
            visited = new int[V + 1];
            result = true;

            graph = new ArrayList<>(V);
            for (int j = 0; j < V + 1; j++) {
                graph.add(new ArrayList<>());
                visited[j] = NO_VISITED;
            }

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            boolean answer = true;
            for (int j = 1; j <= V; j++) {
                if (visited[j] == NO_VISITED) {
                    answer = dfs(j, W);
                }
                if (!answer) {
                    break;
                }
            }

            if (answer) {
                sb.append("YES" + "\n");
            } else {
                sb.append("NO" + "\n");
            }
//            sb.append(result).append("\n");


//            result[i] = true;


        }

        System.out.println(sb);
    }

    private static boolean dfs(int start, int flag) {

        ArrayList<Integer> vertexes = graph.get(start);
        visited[start] = flag;

        for (Integer vertex : vertexes) {
            if (visited[vertex] == NO_VISITED) {
                dfs(vertex, flag == W ? B : W);
            } else if (visited[vertex] == visited[start]) { //이전에 같은 팀으로 배정했는데 돌다보니 인접한 놈일때..
                result = false;
                return false;
            }
            if (!result) {  //이미 안쪽에서 false를 반환했다면
                return false;
            }
        }
        result = true;
        return true;
    }
}
