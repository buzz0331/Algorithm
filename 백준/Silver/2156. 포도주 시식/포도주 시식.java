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

        for(int i = 2; i < table.length; i++) {
            dp[i] = Math.max(findMaxDp(i) + table[i], table[i - 1] + table[i]); // 두 칸 왼쪽 수 연속된 수 중 최대 선택
            table[i] += findMaxDp(i); // 두 칸 왼쪽 수는 뒤 점화식에 영향을 주지 않으므로 영구적으로 업데이트
        }
    }

    private static int findMaxDp(int index) {
        int maxDp = -1;

        for(int i = index - 2; i >= 0; i--) {
            maxDp = Math.max(maxDp, dp[i]);
        }

        return maxDp;
    }
}
