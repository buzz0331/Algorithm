

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());

        int[] dp = new int[X + 1];
        for (int i = 0; i < X + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[X] = 0;

        for (int i = X; i >= 1; i--) {
            if (i % 3 == 0) {
                dp[i / 3] = Math.min(dp[i] + 1, dp[i / 3]);
            }
            if (i % 2 == 0) {
                dp[i / 2] = Math.min(dp[i] + 1, dp[i / 2]);
            }
            dp[i - 1] = Math.min(dp[i] + 1, dp[i - 1]);
        }

        System.out.println(dp[1]);
    }
}
