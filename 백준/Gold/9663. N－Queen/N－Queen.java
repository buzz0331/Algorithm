import java.util.*;
import java.io.*;

public class Main {

    private static int N, totalCnt;
    private static boolean[] col, slash, bSlash;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        totalCnt = 0;

        col = new boolean[N + 1];
        slash = new boolean[2 * N + 1];
        bSlash = new boolean[2 * N];

        nQueen(1);
        System.out.println(totalCnt);
    }

    public static void nQueen(int row) {
        if(row > N) {
            totalCnt++;
            return;
        }

        for(int c = 1; c <= N; c++) {
            if(col[c] || slash[row + c] || bSlash[(row - c) + N]) continue;

            col[c] = slash[row + c] = bSlash[(row - c) + N] = true;
            nQueen(row + 1);
            col[c] = slash[row + c] = bSlash[(row - c) + N] = false;
        }
    }
}
