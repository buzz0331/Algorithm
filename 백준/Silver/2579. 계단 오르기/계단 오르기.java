import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[] stairs;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        stairs = new int[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(stairs[1]);
            return;
        } else if (N == 2) {
            System.out.println(stairs[1] + stairs[N]);
            return;
        }

        dp[1] = stairs[1];
        dp[2] = stairs[1] + stairs[2];

        for (int i = 3; i < N + 1; i++) {
            dp[i] = stairs[i] + Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]);
        }

        System.out.println(dp[N]);
    }
}
