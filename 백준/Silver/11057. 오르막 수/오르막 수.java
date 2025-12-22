import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][10];
        Arrays.fill(dp[0], 1);

        for(int i = 1; i < N+1; i++) {
            for(int j = 0; j < 10; j++) {
                for(int k = j; k < 10; k++) {
                    dp[i][j] += dp[i-1][k];
                    dp[i][j] %= 10007;
                }
            }
        }

        System.out.println(dp[N][0] % 10007);
    }
}
