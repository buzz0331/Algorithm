import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        if (N == 1 || N == 2) {
            System.out.println(N);
            return;
        }

        dp[1] = 1;
        dp[2] = 2;

        System.out.println(fib(N));
    }

    private static int fib(int n) {
        for (int i = 3; i < n + 1; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        return dp[n];
    }
}
