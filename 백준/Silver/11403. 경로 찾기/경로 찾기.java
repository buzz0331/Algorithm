import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] G, result;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        G = new int[N][N];
        result = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                G[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int row = 0; row < N; row++) {
            bfs(row);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (G[start][i] == 1) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            result[start][current] = 1;

            for (int i = 0; i < N; i++) {
                if (G[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }

    }
}
