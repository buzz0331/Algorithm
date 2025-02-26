import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int[][] fib = new int[41][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        fib[0][0] = 1;
        fib[1][1] = 1;

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            for (int j = 2; j < N + 1; j++) {
                fib[j][0] = fib[j - 1][0] + fib[j - 2][0];
                fib[j][1] = fib[j - 1][1] + fib[j - 2][1];
            }

            sb.append(fib[N][0]).append(" ").append(fib[N][1]).append("\n");
        }

        System.out.println(sb);
    }
}
