import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static int white = 0, blue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cut(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    private static void cut(int r, int c, int size) {
        int sum = 0;
        for(int i = r; i < r + size; i++) {
            for(int j = c; j < c + size; j++) {
                sum += map[i][j];
            }
        }

        if(sum == size * size) {
            blue++;
        } else if(sum == 0) {
            white++;
        } else { // 색상이 섞여 있는 경우
            int half = size / 2;
            cut(r, c, half);
            cut(r + half, c, half);
            cut(r, c + half, half);
            cut(r + half, c + half, half);
        }
    }
}
