import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 0: 벽(#) / 1: 빈칸(.) / -1: 구멍(O)
    private static int[][] board;

    // 하, 상, 우, 좌
    private static final int[][] directions = {
            { 1, 0},
            {-1, 0},
            { 0, 1},
            { 0,-1}
    };

    private static int N, M;
    private static boolean[][][][] visited; // (빨강 방문 체크, 파란 방문 체크)

    private static class State {
        int rr, rc, br, bc, depth;
        State(int rr, int rc, int br, int bc, int depth) {
            this.rr = rr;
            this.rc = rc;
            this.br = br;
            this.bc = bc;
            this.depth = depth;
        }
    }

    private static class RollResult {
        int r, c;
        int steps;
        boolean inHole;
        RollResult(int r, int c, int steps, boolean inHole) {
            this.r = r;
            this.c = c;
            this.steps = steps;
            this.inHole = inHole;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        int rr = -1, rc = -1, brR = -1, brC = -1;

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                char ch = line[j];

                if (ch == '#') {
                    board[i][j] = 0;
                } else if (ch == '.' ) {
                    board[i][j] = 1;
                } else if (ch == 'R') {
                    board[i][j] = 1;
                    rr = i; rc = j;
                } else if (ch == 'B') {
                    board[i][j] = 1;
                    brR = i; brC = j;
                } else if (ch == 'O' || ch == '0') {
                    board[i][j] = -1;
                } else {
                    board[i][j] = 1;
                }
            }
        }

        visited = new boolean[N][M][N][M];
        int ans = bfs(rr, rc, brR, brC);
        System.out.print(ans);
    }

    private static int bfs(int srR, int srC, int sbR, int sbC) {
        Queue<State> q = new ArrayDeque<>();
        visited[srR][srC][sbR][sbC] = true;
        q.add(new State(srR, srC, sbR, sbC, 0));

        while (!q.isEmpty()) {
            State currentState = q.poll();

            if (currentState.depth >= 10) continue;

            for (int d = 0; d < 4; d++) {
                RollResult red = roll(currentState.rr, currentState.rc, d);
                RollResult blue = roll(currentState.br, currentState.bc, d);

                // 파란 구슬이 구멍이면 이 방향은 실패
                if (blue.inHole) continue;

                // 빨간 구슬만 구멍이면 성공 (최단이므로 즉시 반환)
                if (red.inHole) return currentState.depth + 1;

                // 둘 다 구멍이 아니고, 같은 칸에 겹치면 이동거리로 뒤에 있던 구슬을 한 칸 뒤로
                if (red.r == blue.r && red.c == blue.c) {
                    if (red.steps > blue.steps) {
                        red.r -= directions[d][0];
                        red.c -= directions[d][1];
                    } else {
                        blue.r -= directions[d][0];
                        blue.c -= directions[d][1];
                    }
                }

                if (!visited[red.r][red.c][blue.r][blue.c]) {
                    visited[red.r][red.c][blue.r][blue.c] = true;
                    q.add(new State(red.r, red.c, blue.r, blue.c, currentState.depth + 1));
                }
            }
        }

        return -1;
    }

    // d 방향으로 굴려서 "벽 직전" 혹은 "구멍"까지 이동한 결과를 반환
    private static RollResult roll(int row, int col, int direction) {
        int steps = 0;

        while (true) {
            int nextRow = row + directions[direction][0];
            int nextCol = col + directions[direction][1];

            // 벽이면 멈춤
            if (board[nextRow][nextCol] == 0) break;

            row = nextRow;
            col = nextCol;
            steps++;

            // 구멍이면 즉시 종료
            if (board[row][col] == -1) {
                return new RollResult(row, col, steps, true);
            }
        }

        return new RollResult(row, col, steps, false);
    }
}