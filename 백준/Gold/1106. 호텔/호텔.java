import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int C, N;
    private static int[][] hotels;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        hotels = new int[N][2];
        dp = new int[C + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            hotels[i][0] = Integer.parseInt(st.nextToken()); // 홍보 비용
            hotels[i][1] = Integer.parseInt(st.nextToken()); // 홍보 결과 (사람 수)
        }

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i < N; i++) {
            int cost = hotels[i][0];
            int gain = hotels[i][1];
            
            for (int j = 1; j <= C; j++) {
                int prev = Math.max(0, j - gain);
                dp[j] = Math.min(dp[j], dp[prev] + cost);
            }
        }

        System.out.println(dp[C]);
    }

}
