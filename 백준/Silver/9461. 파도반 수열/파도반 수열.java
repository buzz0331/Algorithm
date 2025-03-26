import java.io.*;
import java.util.*;

public class Main {

    private static final long[] givenInput = {1, 1, 1, 2, 2, 3, 4, 5, 7, 9};
    private static final long[] dp = new long[101];
    private static int current = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        System.arraycopy(givenInput, 0, dp, 1, current);

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            updateDp(N);
            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb);
    }

    private static void updateDp(int N) {
        if (N > current) {
            for (int j = current + 1; j <= N; j++) {
                dp[j] = dp[j - 1] + dp[j - 5];
            }
            current = N;
        }
    }
}
