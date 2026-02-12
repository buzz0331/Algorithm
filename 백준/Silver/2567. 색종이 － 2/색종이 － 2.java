import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map = new int[101][101];
    private static int[][] directions = {{0, 1}, {-1, 0}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());

            for(int r = row; r < row + 10; r++) {
                for(int c = col; c < col + 10; c++) {
                    map[r][c] = 1;
                }
            }
        }

        int count = 0;
        for(int i = 1; i <= 100; i++) {
            for(int j = 1; j <= 100; j++) {
                if(map[i][j] == 1) {
                    for(int[] direction : directions) {
                        int nR = i + direction[0];
                        int nC = j + direction[1];

                        if(isOut(nR, nC)) {
                            count++;
                        } else if(map[nR][nC] == 0) {
                            count++;
                        }

                    }

                }
            }
        }

        System.out.print(count);
    }

    private static boolean isOut(int row, int col) {
        return row < 1 || col < 1 || row > 100 || col > 100;
    }
}
