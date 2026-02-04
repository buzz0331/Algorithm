import java.util.*;
import java.io.*;

public class Main {

    private static int[][] directions = {
            {0, 0}, {-10, 0}, {-10, 10}, {0, 10}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[101][101];

        int count = 0;
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            for(int r = row; r < row + 10; r++) {
                for(int c = col; c < col + 10; c++) {
                    if(map[r][c] == 0) {
                        count++;
                        map[r][c] = 1;
                    }
                }
            }
        }

        System.out.print(count);
    }

}
