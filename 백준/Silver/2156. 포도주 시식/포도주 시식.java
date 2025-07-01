import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] table;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        table = new int[n];
        dp = new int[n];

        for(int i = 0; i < n; i++) {
            table[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(table[0]);
            return;
        }
        if (n == 2) {
            System.out.println(table[0] + table[1]);
            return;
        }

        dp();
        System.out.println(Math.max(dp[n - 1], dp[n - 2]));
    }

    private static void dp() {
        dp[0] = table[0];
        dp[1] = table[0] + table[1];

        int[] maxDpUntil = new int[table.length];
        maxDpUntil[0] = dp[0];
        maxDpUntil[1] = Math.max(dp[0], dp[1]);

        for (int i = 2; i < table.length; i++) {
            dp[i] = Math.max(maxDpUntil[i - 2] + table[i], table[i - 1] + table[i]);
            table[i] += maxDpUntil[i - 2];
            maxDpUntil[i] = Math.max(maxDpUntil[i - 1], dp[i]);
        }
    }
}
