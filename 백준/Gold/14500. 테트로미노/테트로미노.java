import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static int answer = -1, N, M;
    private static boolean[][] visited;
    private static int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;

                checkExtraShape(i, j);
            }
        }


        System.out.print(answer);
    }

    private static void dfs(int row, int col, int depth, int sum) {
        if(depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for(int[] direction : directions) {
            int nR = row + direction[0];
            int nC = col + direction[1];

            if(nR < 0 || nC < 0 || nR >= N || nC >= M) continue;
            if(visited[nR][nC]) continue;

            visited[nR][nC] = true;
            dfs(nR, nC, depth + 1, sum + map[nR][nC]);
            visited[nR][nC] = false;
        }
    }

    private static void checkExtraShape(int row, int col) {
        for(int i = 0; i < 4; i++) {
            int sum = map[row][col];

            for(int j = 0; j < 3; j++) {
                int dIdx = (i + j) % 4;
                int nR = row + directions[dIdx][0];
                int nC = col + directions[dIdx][1];

                if(nR < 0 || nC < 0 || nR >= N || nC >= M) break;

                sum += map[nR][nC];
                if(j == 2) answer = Math.max(answer, sum);
            }
        }
    }
}
