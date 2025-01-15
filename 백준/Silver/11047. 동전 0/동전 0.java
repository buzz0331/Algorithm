import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[N-1-i] = Integer.parseInt(br.readLine()); //동전의 종류를 내림차순으로 담음
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            int coin = coins[i];

            while (K - coin >= 0) {
                count++;
                K -= coin;
            }

        }

        System.out.println(count);

    }
}
