import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static int N, answer = 0;
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test_case = 1;

        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            map = new int[N][N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();
            sb.append("Problem ").append(test_case).append(": ").append(answer).append("\n");
            test_case++;
        }

        System.out.print(sb);
    }

    private static void bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        boolean[][] visited = new boolean[N][N];
        pq.offer(new int[]{0, 0, map[0][0]});
        visited[0][0] = true;

        while(!pq.isEmpty()) {
            int[] current = pq.poll();

            if(current[0] == N - 1 && current[1] == N - 1) {
                answer = current[2];
                return;
            }

            for(int[] direction : directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];

                if(nR < 0 || nC < 0 || nR >= N || nC >= N) continue;
                if(visited[nR][nC]) continue;

                pq.offer(new int[]{nR, nC, current[2] + map[nR][nC]});
                visited[nR][nC] = true;
            }
        }
    }
}
