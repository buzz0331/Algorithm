import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] num = new int[n];
        for(int i = 0; i < n; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n]; // n번째 문자에서 끝나는 연속합 중 최솟값
        int max = Integer.MIN_VALUE;

        dp[0] = num[0];
        max = Math.max(max, dp[0]);

        for(int i = 1; i < n; i++) {
            dp[i] = Math.max(num[i], dp[i - 1] + num[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.print(max);
    }
}
