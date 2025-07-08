import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int n = s1.length();
        int m = s2.length();

        // dp[i][j] = s1의 i번째까지, s2의 j번째까지의 LCS 길이
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 문자가 같을 때
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // 다를 때
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}