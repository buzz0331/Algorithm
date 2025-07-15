import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] home;
    private static int[][][] dp; // 마지막 index 0: 가로 / 1: 대각선 / 2: 세로
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        home = new int[N + 1][N + 1];

        for(int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N + 1; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if (home[N][N] == 1) {
            System.out.println(0);
            return;
        }

        System.out.println(dfs(N, N, 0) + dfs(N, N, 1) + dfs(N, N, 2));
    }

    private static int dfs(int row, int col, int type) {
        if(row == 1 && col == 2 && type == 0) {
            return 1;
        }

        int sum = 0;

        switch(type) {
            case 0: //가로
                if (col - 2 > 0 && home[row][col - 1] == 0 && home[row][col - 2] == 0) { // 왼쪽 추적
                    sum += dfs(row, col - 1, 0);
                }
                if (row - 1 > 0 && col - 2 > 0 && home[row][col - 1] == 0 && canMoveDiagonal(row, col - 1)) { // 대각선 추적
                    sum += dfs(row , col - 1, 1);
                }
                break;
            case 1: //대각선
                if (row - 1 > 0 && col - 2 > 0 && home[row - 1][col - 2] == 0 && canMoveDiagonal(row, col)) { // 왼쪽 추적
                    sum += dfs(row - 1, col - 1, 0);
                }
                if (row - 2 > 0 && col - 2 > 0 && canMoveDiagonal(row, col) && canMoveDiagonal(row - 1, col - 1)) { // 대각선 추적
                    sum += dfs(row - 1, col - 1, 1);
                }
                if (row - 2 > 0 && col - 1 > 0 && canMoveDiagonal(row, col) && home[row - 2][col - 1] == 0) { // 위쪽 추적
                    sum += dfs(row - 1, col - 1, 2);
                }
                break;
            case 2: //세로
                if (row - 2 > 0 && col - 1 > 0 && canMoveDiagonal(row - 1, col) && home[row - 1][col] == 0) { // 대각선 추적
                    sum += dfs(row - 1, col, 1);
                }
                if (row - 2 > 0 && home[row - 1][col] == 0 && home[row - 2][col] == 0) { // 위쪽 추적
                    sum += dfs(row - 1, col, 2);
                }
                break;
        }
        return sum;
    }

    private static boolean canMoveDiagonal(int row, int col) {
        return home[row - 1][col - 1] == 0 && home[row - 1][col] == 0 && home[row][col - 1] == 0;
    }


}
