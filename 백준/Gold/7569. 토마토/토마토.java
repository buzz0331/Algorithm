import java.util.*;
import java.io.*;

public class Main {

    private static int[][][] map;
    private static int[][] directions = {
            {1, 0, 0}, {-1, 0, 0},
            {0, -1, 0}, {0, 1, 0},
            {0, 0, -1}, {0, 0, 1}
    };
    private static int M, N, H;
    private static final Queue<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];

        for(int h = 0; h < H; h++) {
            for(int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for(int m = 0; m < M; m++) {
                    map[h][n][m] = Integer.parseInt(st.nextToken());
                    if(map[h][n][m] == 1) {
                        queue.offer(new int[]{h, n, m});
                    }
                }
            }
        }

        bfs();
        System.out.print(calMaxDate());
    }

    private static int calMaxDate() {
        int max = -1;
        for(int h = 0; h < H; h++) {
            for(int n = 0; n < N; n++) {
                for(int m = 0; m < M; m++) {
                    if(map[h][n][m] == 0) return -1;
                    max = Math.max(max, map[h][n][m]);
                }
            }
        }
        return max - 1;
    }

    private static void bfs() {

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for(int[] direction : directions) {
                int nH = current[0] + direction[0];
                int nR = current[1] + direction[1];
                int nC = current[2] + direction[2];

                if(nH < 0 || nR < 0 || nC < 0 || nH >= H || nR >= N || nC >= M) continue;

                if(map[nH][nR][nC] == 0) { // 익지 않은 토마토
                    map[nH][nR][nC] = map[current[0]][current[1]][current[2]] + 1;
                    queue.offer(new int[]{nH, nR, nC});
                }
            }
        }

    }

}
