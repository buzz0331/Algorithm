import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        if (N == 1) {
            System.out.print(1);
            return;
        } else if (N == 2) {
            System.out.print(3);
            return;
        }

        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
        }

        System.out.print(dp[N]);
    }

}
