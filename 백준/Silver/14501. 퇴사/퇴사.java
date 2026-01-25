import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        int[] t = new int[N ];
        int[] p = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            if(i + t[i] <= N) {
                dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
            }

            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        System.out.println(dp[N]);

    }

}
