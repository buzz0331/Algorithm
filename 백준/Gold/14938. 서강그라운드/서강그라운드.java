import java.util.*;
import java.io.*;

public class Main {

    private static int[][] dist;
    private static int n, m, r;
    private static int[] values;

    private static final int INF = 1500000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 지역 개수
        m = Integer.parseInt(st.nextToken()); // 수색 범위
        r = Integer.parseInt(st.nextToken()); // 길의 개수

        dist = new int[n + 1][n + 1];
        values = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[x][y] = cost;
            dist[y][x] = cost;
        }

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int max = -1;
        for(int i = 1; i <= n; i++) {
            int sum = values[i];
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                if(dist[i][j] <= m) sum += values[j]; // 수색 범위 안에 있으면 아이템 줍줍
            }

            max = Math.max(max, sum);
        }

        System.out.print(max);
    }
}
