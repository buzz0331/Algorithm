import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[][] arr;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        int row = N / 2;
        int col = N / 2;

        int spinCount = N / 2;
        int num = 1;

        arr[row][col] = num++;
        row--;

        for(int spin = 1; spin <= spinCount; spin++) {
            int r = row - spin + 1;
            int c = col - spin + 1;
//            System.out.print(r + " " + c + " ");
            arr[r][c] = num++;

            for(int j = 0; j < spin * 2 - 1; j++) {
                arr[r][++c] = num++;
            }

            for(int j = 0; j < spin * 2; j++) {
                arr[++r][c] = num++;
            }

            for(int j = 0; j < spin * 2; j++) {
                arr[r][--c] = num++;
            }

            for(int j = 0; j < spin * 2; j++) {
                arr[--r][c] = num++;
            }

//            for(int j = 0; j < spinCount - 1; j++) {
//                arr[r][++c] = num++;
//            }
        }

        printArr();
    }

    private static void printArr() {
        StringBuilder sb = new StringBuilder();
        int[] m_location = {0, 0};

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[i][j] == M) m_location = new int[] {i, j};
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append(m_location[0] + 1).append(" ").append(m_location[1] + 1);
        System.out.print(sb);
    }

}
