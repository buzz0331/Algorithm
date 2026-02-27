import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 10001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        int[] dp = new int[k + 1]; // x원을 얻기 위한 동전 최소 개수

        for(int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int coin : coins) {
            for(int j = coin; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }

        System.out.print(dp[k] == INF ? -1 : dp[k]);
    }
}