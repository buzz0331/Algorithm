import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static int W, H, K;

    private static int[][] adjDirections = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static int[][] horseDirections = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
            {2, -1}, {2, 1}, {1, -2}, {1, 2}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[H][W][K + 1]; // (W, H) 좌표에 말 동작을 K번 사용했을 때의 방문 여부
        queue.offer(new int[]{0, 0, 0, 0}); // R, C, 말 동작 수, 전체 동작 수
        visited[0][0][0] = true;

        while(!queue.isEmpty()) {
            int[] current = queue. poll();

            if(current[0] == H - 1 && current[1] == W - 1) {
                return current[3];
            }

            // 먈 동작 사용 x
            for(int[] aDirection : adjDirections) {
                int nR = current[0] + aDirection[0];
                int nC = current[1] + aDirection[1];

                if(nR < 0 || nC < 0 || nR >= H || nC >= W) continue;
                if(visited[nR][nC][current[2]] || map[nR][nC] == 1) continue;

                visited[nR][nC][current[2]] = true;
                queue.offer(new int[]{nR, nC, current[2], current[3] + 1});
            }

            if(current[2] == K) continue; // 이미 말 동작 횟수 소진

            // 말 동작 사용 o
            for(int[] hDirection : horseDirections) {
                int nR = current[0] + hDirection[0];
                int nC = current[1] + hDirection[1];

                if(nR < 0 || nC < 0 || nR >= H || nC >= W) continue;
                if(visited[nR][nC][current[2] + 1] || map[nR][nC] == 1) continue;

                visited[nR][nC][current[2] + 1] = true;
                queue.offer(new int[]{nR, nC, current[2] + 1, current[3] + 1});
            }
        }

        return -1;
    }
}
