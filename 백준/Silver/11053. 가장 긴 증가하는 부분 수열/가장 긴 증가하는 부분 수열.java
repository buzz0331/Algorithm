

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] seq;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        seq = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;
        int min = seq[0];
        int result = 1;

        for (int i = 1; i < N; i++) {
            if (seq[i] < min) {
                dp[i] = 1;
                min = seq[i];
                continue;
            }

            for (int j = 0; j < i; j++) {
                if (seq[j] < seq[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }

            result = Math.max(result, dp[i]);

        }
        System.out.println(result);

    }
}
