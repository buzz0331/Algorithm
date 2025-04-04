import java.io.*;
import java.util.*;

public class Main {

    private static boolean[] isNotPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        isNotPrime = new boolean[N + 1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;

        for (int i = 2; i * i <= N; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        for (int i = M; i <= N; i++) {
            if (!isNotPrime[i]) {
                sb.append(i).append("\n");
            }
        }

        System.out.print(sb);
    }
}