import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map;
    private static boolean[][] visited;
    private static int n, m;

    private static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    max = Math.max(bfs(i, j), max);
                    count++;
                }
                visited[i][j] = true;
            }
        }

        System.out.print(count + "\n" + max);
    }

    private static int bfs(int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        int count = 1;
        visited[row][col] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for(int[] direction : directions) {
                int nr = current[0] + direction[0];
                int nc = current[1] + direction[1];

                if(nr >= n || nc >= m || nr < 0 || nc < 0) continue;
                if(visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
                count++;
            }
        }

        return count;
    }
}
