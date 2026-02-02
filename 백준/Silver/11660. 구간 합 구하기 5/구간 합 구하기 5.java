import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] sum = new int[N + 1][N + 1];
        sum[1][1] = map[0][0];
        for(int i = 2; i <= N; i++) {
            sum[1][i] = sum[1][i - 1] + map[0][i - 1];
            sum[i][1] = sum[i - 1][1] + map[i - 1][0];
        }

        for(int i = 2; i <= N; i++) {
            for(int j = 2; j <= N; j++) {
                sum[i][j] = map[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sR = Integer.parseInt(st.nextToken());
            int sC = Integer.parseInt(st.nextToken());
            int eR = Integer.parseInt(st.nextToken());
            int eC = Integer.parseInt(st.nextToken());

            int result = sum[eR][eC] - sum[sR - 1][eC] - sum[eR][sC - 1] + sum[sR - 1][sC - 1];
            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}
