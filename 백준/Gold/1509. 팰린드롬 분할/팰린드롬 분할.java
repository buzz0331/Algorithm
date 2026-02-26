import java.io.*;
import java.util.*;

public class Main {

    private static int length;
    private static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        length = s.length();

        boolean[][] pal = createPalindromeTable();
        System.out.print(dp(pal));
    }

    private static boolean[][] createPalindromeTable() {
        boolean[][] pal = new boolean[length][length];

        // 길이 1
        for(int i = 0; i < length; i++) {
            pal[i][i] = true;
        }

        // 길이 2
        for(int i = 0; i < length - 1; i++) {
            if(s.charAt(i) == s.charAt(i + 1)) {
                pal[i][i + 1] = true;
            }
        }

        // 길이 3
        for(int len = 3; len <= length; len++) {
            for(int i = 0; i + len - 1 < length; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && pal[i + 1][j - 1]) {
                    pal[i][j] = true;
                }
            }
        }

        return pal;
    }

    private static int dp(boolean[][] pal) {
        int[] dp = new int[length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for(int i = 1; i <= length; i++) {
            for(int j = 0; j < i; j++) {
                if(pal[j][i - 1] && dp[j] != Integer.MAX_VALUE) dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }

        return dp[length];
    }
}
