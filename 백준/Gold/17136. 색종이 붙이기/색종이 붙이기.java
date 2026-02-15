import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map = new int[10][10];
    private static boolean[][] visited = new boolean[10][10];
    private static List<int[]>[] directions = new ArrayList[5];
    private static int[] colorPapers = new int[5];

    private static int ans = Integer.MAX_VALUE;

    private static void init() {
        for (int n = 0; n < 5; n++) {
            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    list.add(new int[]{i, j});
                }
            }
            directions[n] = list;
        }

        Arrays.fill(colorPapers, 5);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init();
        dfs(0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void dfs(int used) {
        if (used >= ans) return; // 가지치기

        // 1) 아직 덮지 않은 1칸 찾기
        int r = -1, c = -1;
        boolean found = false;
        Search:for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    r = i; c = j;
                    found = true;
                    break Search;
                }
            }
        }

        // 2) 더 이상 덮을 1이 없으면 정답 갱신
        if (!found) {
            ans = Math.min(ans, used);
            return;
        }

        // 3) 큰 색종이부터 시도 (5 -> 1)
        for (int size = 5; size >= 1; size--) {
            int idx = size - 1;
            if (colorPapers[idx] == 0) continue;
            if (!canAttach(size, r, c)) continue;

            // 붙이기
            colorPapers[idx]--;
            setPaper(size, r, c, true);

            dfs(used + 1);

            // 복원
            setPaper(size, r, c, false);
            colorPapers[idx]++;
        }
    }

    private static boolean canAttach(int size, int r, int c) {
        List<int[]> direction = directions[size - 1];
        for (int[] d : direction) {
            int nR = r + d[0];
            int nC = c + d[1];
            if (nR < 0 || nC < 0 || nR >= 10 || nC >= 10) return false;
            if (map[nR][nC] == 0) return false;
            if (visited[nR][nC]) return false;
        }
        return true;
    }

    private static void setPaper(int size, int r, int c, boolean value) {
        List<int[]> direction = directions[size - 1];
        for (int[] d : direction) {
            int nr = r + d[0];
            int nc = c + d[1];
            visited[nr][nc] = value;
        }
    }
}