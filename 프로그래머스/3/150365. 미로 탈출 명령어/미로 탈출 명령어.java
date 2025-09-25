import java.util.*;

class Solution {
    
    // 사전 순: d < l < r < u
    private final int[] dx = { 1, 0, 0, -1 };
    private final int[] dy = { 0, -1, 1, 0 };
    private final char[] dc = { 'd', 'l', 'r', 'u' };
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if (dist > k || ((k - dist) & 1) == 1) {
            return "impossible";
        }

        StringBuilder ans = new StringBuilder();
        int cx = x, cy = y;

        for (int step = 0; step < k; step++) {
            // 남은 스텝 수
            int left = k - step;

            boolean moved = false;
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 1) 격자 범위
                if (nx < 1 || nx > n || ny < 1 || ny > m) continue;

                // 2) 남은 스텝으로 목표까지 도달 가능?
                int rest = left - 1; // 이번에 1칸 이동 후 남는 스텝
                int ndist = Math.abs(nx - r) + Math.abs(ny - c);
                if (ndist > rest) continue;

                // 3) 홀짝 일치
                if (((rest - ndist) % 1) == 1) continue;

                // 선택
                ans.append(dc[i]);
                cx = nx; cy = ny;
                moved = true;
                break;
            }

            if (!moved) return "impossible";
        }

        return ans.toString();
    }
}