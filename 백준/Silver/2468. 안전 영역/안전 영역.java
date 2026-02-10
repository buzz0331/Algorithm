import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static boolean[][] visited;
    private static int N;
    private static int[][] directions = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int max = Integer.MIN_VALUE;
        int min = 0;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        }

        int answer = 1;
        for(int height = max - 1; height >= min; height--) {
            int count = 0;
            visited = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] <= height) continue;
                    if(visited[i][j]) continue;

                    bfs(i, j, height);
                    count++;
                }
            }

            answer = Math.max(count, answer);
        }

        System.out.print(answer);

    }

    private static void bfs(int row, int col, int height) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for(int[] direction : directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];

                if(nR < 0 || nC < 0 || nR >= N || nC >= N) continue;
                if(map[nR][nC] <= height) continue;
                if(visited[nR][nC]) continue;

                queue.offer(new int[]{nR, nC});
                visited[nR][nC] = true;
            }
        }
    }
}
