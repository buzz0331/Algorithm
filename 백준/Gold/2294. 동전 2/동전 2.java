import java.util.*;
import java.io.*;

public class Main {
    
    private static final int INF = 10_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Set<Integer> coinSet = new HashSet<>();
        List<Integer> coins = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int c = Integer.parseInt(br.readLine());
            if(!coinSet.contains(c)) {
                coins.add(c);
                coinSet.add(c);
            }
        }

        long[] dp = new long[k + 1]; // x원을 얻기 위한 동전 최소 개수
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 1; i <= k; i++) {
            for(int c : coins) {
                if(i - c >= 0) dp[i] = Math.min(dp[i], dp[i - c] + 1);
            }
        }

        System.out.print(dp[k] == INF ? -1 : dp[k]);
    }
}

//2 8
//8
//19