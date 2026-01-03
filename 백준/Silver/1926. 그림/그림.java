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

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int pictureCount = 0;
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    pictureCount++;
                    int area = dfs(i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        System.out.print(pictureCount + "\n" + maxArea);
    }

    private static int dfs(int row, int col) {
        visited[row][col] = true;
        int area = 1; // 현재 칸 포함

        for (int[] direction : directions) {
            int nr = row + direction[0];
            int nc = col + direction[1];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if (visited[nr][nc] || map[nr][nc] == 0) continue;

            area += dfs(nr, nc);
        }

        return area;
    }
}