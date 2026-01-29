import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int k = Math.min(N, M) / 2; // 껍질 수

        for(int i = 0; i < k; i++) {
            int r = i; // 시작 위치
            int c = i;

            int row = N - 2 * i;
            int col = M - 2 * i;

            List<Integer> list = new ArrayList<>();

            // 위 (왼쪽 -> 오른쪽)
            for(int j = 0; j < col - 1; j++) {
                list.add(arr[r][c++]);
            }

            for(int j = 0; j < row - 1; j++) {
                list.add(arr[r++][c]);
            }

            for(int j = 0; j < col - 1; j++) {
                list.add(arr[r][c--]);
            }

            for(int j = 0; j < row - 1; j++) {
                list.add(arr[r--][c]);
            }

            int size = list.size();
            int idx = R % size;

            for(int j = 0; j < col - 1; j++) {
                arr[r][c++] = list.get(idx++ % size);
            }

            for(int j = 0; j < row - 1; j++) {
                arr[r++][c] = list.get(idx++ % size);
            }

            for(int j = 0; j < col - 1; j++) {
                arr[r][c--] = list.get(idx++ % size);
            }

            for(int j = 0; j < row - 1; j++) {
                arr[r--][c] = list.get(idx++ % size);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
