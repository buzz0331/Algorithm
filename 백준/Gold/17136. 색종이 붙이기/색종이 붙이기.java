import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map = new int[10][10];
    private static boolean[][] visited = new boolean[10][10];
    private static int[] colorPapers = new int[5];

    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.fill(colorPapers, 5);
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
                    r = i;
                    c = j;
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
        // 범위 체크 + map==1 + 미방문 체크
        if (r + size > 10 || c + size > 10) return false;

        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] == 0) return false;
                if (visited[i][j]) return false;
            }
        }
        return true;
    }

    private static void setPaper(int size, int r, int c, boolean value) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                visited[i][j] = value;
            }
        }
    }
}