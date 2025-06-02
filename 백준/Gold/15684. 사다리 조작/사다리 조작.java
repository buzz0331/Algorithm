import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] ladder;
    private static boolean isComplete;
    private static int N, M, H;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = 1;
            ladder[a][b + 1] = -1;
        }
        System.out.println(ladderManipulate());
    }

    private static int ladderManipulate() {
        int rowCount = 0;

        while(rowCount <= 3) {
            if(chooseLadderRow(rowCount, 0)) {
                break;
            }
            rowCount++;
        }
        return rowCount > 3 ? -1 : rowCount;
    }

    private static boolean chooseLadderRow(int rowCount, int depth) {
        // count개의 가로선을 선택
        if(depth == rowCount) {
            isComplete = startGame();
            return isComplete;
        }

        if(isComplete) {
            return true;
        }

        for(int i = 1; i <= H; i++) {
            for(int j = 1; j <= N; j++) {
                if(j != N && ladder[i][j] != 1 && ladder[i][j] != -1 && ladder[i][j + 1] != 1) {
                    ladder[i][j] = 1;
                    ladder[i][j + 1] = -1;
                    if(chooseLadderRow(rowCount, depth + 1)) {
                        return true;
                    }
                    ladder[i][j] = 0;
                    ladder[i][j + 1] = 0;
                }
            }
        }

        return isComplete;
    }

    private static boolean startGame() {
        for(int i = 1; i <= N; i++) {
            int col = i;
            int row = 1;
            while(row <= H) {
                col += ladder[row][col];
                row++;
            }

            if(col != i) return false;  //조건에 충족하지 못하면 다른 경우 탐색
        }

        return true; //조건에 충족하면 게임 끝!
    }

}
