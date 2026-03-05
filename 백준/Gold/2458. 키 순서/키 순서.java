import java.util.*;
import java.io.*;

public class Main {

    private static boolean[][] graph;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new boolean[N + 1][N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = true;
        }

        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        int count = 0;
        for(int i = 1; i <= N; i++) {
            boolean reachable = true;
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;
                if(!(graph[i][j] || graph[j][i])) {
                    reachable = false;
                    break;
                }
            }

            if(reachable) count++;
        }

        System.out.println(count);
    }
}
