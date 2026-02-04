import java.util.*;
import java.io.*;

public class Main {

    private static int w, h;
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1} , {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) break;

            int[][] map = new int[h][w];
            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if(map[i][j] == 1) {
                        count++;
                        bfs(map, i, j);
                    }
                }
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }

    private static void bfs(int[][] map, int r, int c) {
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{r, c});
        map[r][c] = 2;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for(int[] direction: directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];

                if(nR < 0 || nC < 0 || nR >= h || nC >= w) continue;

                if(map[nR][nC] == 1) {
                    map[nR][nC] = 2;
                    queue.offer(new int[]{nR, nC});
                }
            }
        }
    }
}
