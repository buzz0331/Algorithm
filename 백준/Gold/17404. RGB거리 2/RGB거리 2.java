import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] house, dp;

    private static final int INF = 1000 * 1000;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        house = new int[N][3];
        dp = new int[N][3]; // 첫번째 집을 각 색깔(0: RED, 1: GREEN, 2: BLUE)로 고정했을 때 최솟값
        int answer = INF;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // k = 0 -> RED, 1 -> GREEN, 2 -> BLUE로 첫 집을 칠하는 경우
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) { //RED, GREEN, BLUE로 칠하는 경우 각 색을 제외한 나머지는 INF로 초기화
                if(i == j) dp[0][j] = house[0][j];
                else dp[0][j] = INF;
            }

            for(int k = 1; k < N; k++) {
                dp[k][0] = house[k][0] + Math.min(dp[k - 1][1], dp[k - 1][2]);
                dp[k][1] = house[k][1] + Math.min(dp[k - 1][0], dp[k - 1][2]);
                dp[k][2] = house[k][2] + Math.min(dp[k - 1][0], dp[k - 1][1]);
            }

            for(int j = 0; j < 3; j++) {
                if(i != j) answer = Math.min(dp[N - 1][j], answer);
            }
        }

        System.out.print(answer);
    }
}
