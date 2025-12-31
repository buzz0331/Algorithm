import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map;
    private static int N, M;
    private static int answer = 0;

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
        System.out.print(answer);
    }

    private static void solve() {
        while (true) {
            int[][] exposure = new int[N][M];      // 이번 턴에서 외부공기와 맞닿은 면 수
            boolean[][] visited = new boolean[N][M];

            bfsOutsideAir(exposure, visited);

            List<Point> toMelt = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (map[r][c] == 1 && exposure[r][c] >= 2) {
                        toMelt.add(new Point(r, c));
                    }
                }
            }

            if (toMelt.isEmpty()) {
                return; // 더 이상 녹을 치즈가 없으면 종료
            }

            for (Point p : toMelt) {
                map[p.row][p.col] = 0;
            }

            answer++;
        }
    }

    private static void bfsOutsideAir(int[][] exposure, boolean[][] visited) {
        Queue<Point> q = new LinkedList<>();

        // 외부 공기 시작점: (0,0)에서 시작 (문제 조건상 테두리는 공기라고 보는 경우가 일반적)
        q.offer(new Point(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int[] d : directions) {
                int nr = cur.row + d[0];
                int nc = cur.col + d[1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if (map[nr][nc] == 1) {
                    // 치즈면: 외부 공기와 맞닿는 면 카운트만 올리고, 큐에는 넣지 않음
                    exposure[nr][nc]++;
                    continue;
                }

                // 공기(0)면: BFS 확장
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                }
            }
        }
    }

    private static class Point {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}