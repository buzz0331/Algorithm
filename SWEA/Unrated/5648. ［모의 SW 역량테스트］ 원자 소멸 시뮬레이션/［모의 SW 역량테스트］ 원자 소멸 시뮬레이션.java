import java.util.*;
import java.io.*;

class Solution
{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N = 4001;
    private static int[][] map = new int[N][N];
    private static int[][] directions = {
            {1, 0}, {-1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String args[]) throws Exception
    {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");

            int count = Integer.parseInt(br.readLine());
            int totalEnergy = 0;

            ArrayDeque<Unit> dq = new ArrayDeque<>(); // 살아있는 원자 리스트
            for (int i = 0; i < count; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken()) + 1000 << 1;
                int r = Integer.parseInt(st.nextToken()) + 1000 << 1;
                int dir = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                map[r][c] = e;
                dq.addLast(new Unit(r, c, dir, e));
            }

            while (!dq.isEmpty()) {
                Unit cur = dq.pollFirst();

                // 1. 충돌 확인
                if(map[cur.row][cur.col] != cur.e) {
                    totalEnergy += map[cur.row][cur.col]; // 이미 충돌된 원자여도 0이므로 중복으로 더해지지 않음
                    map[cur.row][cur.col] = 0;
                    continue;
                }

                // 2. 충돌되지 않았으면 다음 방향에 기록 후 덱에 넣기
                map[cur.row][cur.col] = 0;
                int nR = cur.row + directions[cur.dir][0];
                int nC = cur.col + directions[cur.dir][1];

                if (nR >= 0 && nR < N && nC >= 0 && nC < N) {
                    cur.row = nR;
                    cur.col = nC;
                    map[cur.row][cur.col] += cur.e;
                    dq.addLast(cur);
                }

            }
            sb.append(totalEnergy);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static class Unit {
        public int row, col, dir, e;

        public Unit(int row, int col, int dir, int e) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.e = e;
        }
    }
}