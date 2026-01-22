import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int width = 4 * N - 3;
        int height = 4 * N - 1;

        if(N == 1) {
            System.out.print("*");
            return;
        }

        char[][] c = new char[height][width];
        for(int i = 0; i < c.length; i++) {
            Arrays.fill(c[i], ' ');
        }

        int x = width;
        int y = 0;

//        s[y][x] = "*";
        int round = N - 1;
        while(round --> 0) {
//            width--;

            for(int i = 0; i < width; i++) {
                c[y][--x] = '*';
            }

            height--;

            for(int i = 0; i < height; i++) {
                c[++y][x] = '*';
            }

            width--;

            for(int i = 0; i < width; i++) {
                c[y][++x] = '*';
            }

            height -= 2;

            for(int i = 0; i < height; i++) {
                c[--y][x] = '*';
            }

            c[y][--x] = '*';
            width -= 3;
            height--;
        }

        x--;
        for(int i = 0; i < 3; i++) {
            c[y++][x] = '*';
        }

        StringBuilder sb = new StringBuilder();
        for (char[] row : c) {
            int end = row.length - 1;
            while (end >= 0 && row[end] == ' ') end--;  // 오른쪽 공백 제거
            for (int i = 0; i <= end; i++) {
                sb.append(row[i]);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
