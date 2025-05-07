import java.io.*;
import java.util.*;

public class Main {

    private static ArrayList<Integer>[] adjList;
    private static boolean[] visited;
    private static boolean isFound = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        for(int i = 0; i < N & !isFound; i++){
            backtracking(i, 0);
            visited[i] = false;
        }
        System.out.println(isFound ? 1 : 0);
    }

    private static void backtracking(int start, int depth) {
        visited[start] = true;

        if (depth == 4) {
            isFound = true;
            return;
        }

        for (Integer a : adjList[start]) {
            if (isFound) {
                break;
            }
            if (!visited[a]) {
                backtracking(a, depth + 1);
                visited[a] = false;
            }
        }
    }
}
