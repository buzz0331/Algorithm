import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] dRowCol = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static int[][] map;
    private static int[][] dp;
    private static int M, N;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];
        Arrays.stream(dp).forEach(d -> Arrays.fill(d, -1));

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());;
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        System.out.print(dfs(0, 0));
    }

    private static int dfs(int row, int col) {
        if (row == M - 1 && col == N - 1) {
            return 1;
        }

        if(dp[row][col] != -1) return dp[row][col];

        dp[row][col] = 0; // 현재 위치에서 끝점까지 도달하는 경로의 개수를 0으로 초기화.

        for(int[] rowcol : dRowCol) {
            int nRow = row + rowcol[0];
            int nCol = col + rowcol[1];

            if(nRow < 0 || nCol < 0 || nRow >= M || nCol >= N) continue;
            if(map[row][col] > map[nRow][nCol]) {
                dp[row][col] += dfs(nRow, nCol);
            }
        }

        return dp[row][col];
    }
}
