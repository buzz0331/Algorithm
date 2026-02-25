import java.util.*;
import java.io.*;

public class Main {

    private static int[][] cost;
    private static int[][] dp;
    private static int N, INF = 16000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][1 << N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(tsp(0, 1));
    }

    private static int tsp(int current, int visited) {
        if(visited == (1 << N) - 1) {
            if(cost[current][0] == 0) { // 되돌아갈 길이 없을 경우
                return INF;
            }

            return cost[current][0];
        }

        // 이미 계산되었음
        if(dp[current][visited] != -1) {
            return dp[current][visited];
        }

        dp[current][visited] = INF;

        for(int next = 0; next < N; next++) {
            if((visited & (1 << next)) != 0 || cost[current][next] == 0) continue; // 이미 방문했거나 길이 없는 노드 패스

            dp[current][visited] = Math.min(dp[current][visited], cost[current][next] + tsp(next, visited | (1 << next)));
        }

        return dp[current][visited];
    }
}
