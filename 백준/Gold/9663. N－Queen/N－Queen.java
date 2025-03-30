import java.util.*;
import java.io.*;

public class Main {

    private static int[] chess; // 인덱스 : 몇 번째 열 | 값 : 몇 번째 행
    private static int N;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chess = new int[N + 1];

        dfs(1);

        System.out.println(result);
    }

    private static void dfs(int row) {  // 체스판에서 행을 순차적으로 내려가면서 탐색
        if (row == N + 1) {
            result++;
            return;
        }

        for (int i = 1; i <= N; i++) {  // 하나의 행에서 순차적으로 열을 오른쪽으로 이동시키면서 탐색
            chess[row] = i;
            if (canBeQueen(row)) {
                dfs(row + 1);
            }
        }
    }

    private static boolean canBeQueen(int row) {
        // 현재 퀸의 위치가 선택된 다른 퀸들과 충돌이 없는지 확인
        for (int i = 1; i < row; i++) {
            // 같은 열에 위치해 있는 퀸이 존재하는 경우
            if (chess[i] == chess[row]) {
                return false;
            }

            // 같은 대각선에 위치한 퀸이 존재하는 경우 (= 행의 차이와 열의 차이가 일치하는 경우)
            if (Math.abs(row - i) == Math.abs(chess[row] - chess[i])) {
                return false;
            }
        }
        return true;
    }
}
