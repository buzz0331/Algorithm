import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static boolean[][] board;
    private static int offset;

    private static int N;
    private static int answerBlack = 0;
    private static int answerWhite = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        board = new boolean[N + 1][N + 1];
        boolean hasOne = false;

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                boolean canBishop = Integer.parseInt(st.nextToken()) == 1;
                board[i][j] = canBishop;
                if (canBishop) hasOne = true;
            }
        }

        if (!hasOne) {
            System.out.print(0);
            return;
        }

        offset = N - 1;

        // 검은 칸 (1,1) 기준: (row+col)%2 == 0
        boolean[] sumBlack = new boolean[2 * N + 2];
        boolean[] subBlack = new boolean[2 * N - 1];
        dfsColor(1, 1, sumBlack, subBlack, 0, 0);

        // 흰 칸 (1,2) 기준: (row+col)%2 == 1
        boolean[] sumWhite = new boolean[2 * N + 2];
        boolean[] subWhite = new boolean[2 * N - 1];
        if (N >= 2) {
            dfsColor(1, 2, sumWhite, subWhite, 0, 1);
        }

        System.out.print(answerBlack + answerWhite);
    }

    // colorFlag: 0 -> 검은칸, 1 -> 흰칸
    private static void dfsColor(int row, int col, boolean[] sumArray, boolean[] subArray, int count, int colorFlag) {
        boolean isEnd = row > N;
        if (isEnd) {
            if (colorFlag == 0) answerBlack = Math.max(answerBlack, count);
            else answerWhite = Math.max(answerWhite, count);
            return;
        }

        int sum = row + col;
        int sub = row - col + offset;

        if (board[row][col] && !(sumArray[sum] || subArray[sub])) {
            sumArray[sum] = true;
            subArray[sub] = true;

            dfsColorNext(row, col, sumArray, subArray, count + 1, colorFlag);

            sumArray[sum] = false;
            subArray[sub] = false;
        }

        dfsColorNext(row, col, sumArray, subArray, count, colorFlag);
    }

    // 같은 색 칸만 순회하도록 "2칸 점프"로 다음 좌표를 구한다.
    private static void dfsColorNext(int row, int col, boolean[] sumArray, boolean[] subArray, int count, int colorFlag) {
        int[] next = findNextRowColColor(row, col, colorFlag);
        dfsColor(next[0], next[1], sumArray, subArray, count, colorFlag);
    }

    // colorFlag에 맞는 칸만 방문하도록 다음 좌표를 만든다.
    // (row,col)에서 같은 색으로 가려면 col을 +2 한다.
    // 줄 끝을 넘기면 다음 row로 내려가되, row의 parity에 따라 시작 col이 1 또는 2가 되도록 맞춘다.
    private static int[] findNextRowColColor(int row, int col, int colorFlag) {
        col += 2;

        if (col > N) {
            row++;

            if (row > N) return new int[]{row, 1};

            // (row + col) % 2 == colorFlag 를 만족하는 첫 col은 다음과 같다.
            // row가 홀수면 col flag는 colorFlag^1, row가 짝수면 col flag는 colorFlag
            // 1-index에서 col의 홀짝으로 맞추기:
            int wantColOdd = (row % 2 == 1) ? (colorFlag == 0 ? 1 : 0) : (colorFlag == 0 ? 0 : 1);
            // wantColOdd==1이면 col=1, wantColOdd==0이면 col=2
            col = (wantColOdd == 1) ? 1 : 2;

            // N==1인 경우 흰칸 시작이 불가능할 수 있으니 안전 처리
            if (col > N) {
                row++;
                col = 1;
            }
        }

        return new int[]{row, col};
    }
}