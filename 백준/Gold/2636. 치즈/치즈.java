import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static int N, M, size = 0, time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        StringBuilder sb = new StringBuilder();
        sb.append(time).append("\n");
        sb.append(size);
        System.out.print(sb);
    }

    private static void bfs() {
        while(true) {
            List<int[]> disappear = new ArrayList<>();
            Queue<int[]> queue = new ArrayDeque<int[]>();
            boolean[][] visited = new boolean[N][M];

            queue.offer(new int[]{0, 0});
            visited[0][0] = true;

            while(!queue.isEmpty()) {
                int[] current = queue.poll();

                for(int[] direction : directions) {
                    int nR = current[0] + direction[0];
                    int nC = current[1] + direction[1];

                    if(nR < 0 || nC < 0 || nR >= N || nC >= M) continue;
                    if(visited[nR][nC]) continue;

                    visited[nR][nC] = true;
                    if(map[nR][nC] == 1) {
                        disappear.add(new int[]{nR, nC});
                    }  else {
                        queue.offer(new int[]{nR, nC});
                    }
                }
            }

            if(disappear.isEmpty()) break;

            // 치즈 녹이기
            for(int[] d : disappear) {
                map[d[0]][d[1]] = 0;
            }
            size = disappear.size();
            time++;
        }
    }
}
