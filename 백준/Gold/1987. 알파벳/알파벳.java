import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] board;
    private static int R, C;
    private static int answer = 1;

    private static final int[][] dRowCol = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for(int i = 0; i < R; i++) {
            char[] charArray = br.readLine().toCharArray();
            System.arraycopy(charArray, 0, board[i], 0, C);
        }

        Set<Character> set = new HashSet<>();
        set.add(board[0][0]);
        dfs(set, 0, 0, 1);

        System.out.print(answer);
    }

    private static void dfs(Set<Character> set, int row, int col, int count) {
        for(int[] rowCol : dRowCol) {

            int nextRow = row + rowCol[0];
            int nextCol = col + rowCol[1];

            if(nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C) continue;

            char nextChar = board[nextRow][nextCol];
            if (set.contains(nextChar)) {
                answer = Math.max(answer, count);
                continue;
            }

            set.add(nextChar);
            count++;
            dfs(set, nextRow, nextCol, count);
            set.remove(nextChar);
            count--;
        }
    }
}
