import java.util.*;
import java.io.*;

public class Main {

    private static int[][] board = new int[9][9];
    private static List<int[]> blank = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 9; i++) {
            char[] c = br.readLine().toCharArray();
            for(int j = 0; j < 9; j++) {
                board[i][j] = c[j] - '0';
                if(board[i][j] == 0) {
                    blank.add(new int[]{i, j});
                }
            }
        }

        backtracking(0);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static boolean backtracking(int depth) {
//        System.out.println(depth);
//        for(int i = 0; i < 9; i++) {
//            System.out.println(Arrays.toString(board[i]));
//        }

        if(depth == blank.size()) {
            return true;
        }

        int[] current = blank.get(depth);
        int row = current[0]; int col = current[1];
        boolean[] cantInput = new boolean[10]; // 1 ~ 9 중 현재 칸에 넣을 수 없는 숫자 확인

        // 행 확인
        for(int i = 0; i < 9; i++) {
            int x = board[row][i];
            cantInput[x] = true;
        }

        // 열 확인
        for(int i = 0; i < 9; i++) {
            int x = board[i][col];
            cantInput[x] = true;
        }

        // ㅁ 확인
        // 몇번째 줄 사각형인지 확인
        int r = row / 3;
        int c = col / 3;

        for(int i = r * 3; i < r * 3 + 3; i++) {
            for(int j = c * 3; j < c * 3 + 3; j++) {
                if(i == row && j == col) continue;
                int x = board[i][j];
                cantInput[x] = true;
            }
        }

//        System.out.println(Arrays.toString(cantInput));

        // 될 수 있는 숫자 차례대로 넣고 백트래킹
        for(int i = 1; i <= 9; i++) {
            if(cantInput[i]) continue;
            board[row][col] = i;
            boolean result = backtracking(depth + 1);
            if(result) {
                return true;
            }
            board[row][col] = 0;
        }

        return false;
    }
}
