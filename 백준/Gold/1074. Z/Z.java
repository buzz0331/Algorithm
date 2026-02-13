import java.util.*;
import java.io.*;

public class Main {

    private static int count = 0, N, r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int size = 1 << N;

        z(0, 0, size);
    }

    private static void z(int row, int col, int size) {
        if(size == 1) {
            if(row == r && col == c) {
                System.out.println(count);
                return;
            }
            count++;
            System.out.println("row: " + row + " col: " + col);
            return;
        }

        int half = size / 2;
        if(checkIn(row, col, half)) {
            z(row, col, half);
            return;
        }
        count += half * half;

        if(checkIn(row, col + half, half)) {
            z(row, col + half, half);
            return;
        }
        count += half * half;

        if(checkIn(row + half, col, half)) {
            z(row + half, col, half);
            return;
        }
        count += half * half;

        if(checkIn(row + half, col + half, half)) {
            z(row + half, col + half, half);
        }
    }

    private static boolean checkIn(int row, int col, int size) {
        return row <= r && col <= c && r <= row + size - 1 && c <= col + size - 1;
    }
}
