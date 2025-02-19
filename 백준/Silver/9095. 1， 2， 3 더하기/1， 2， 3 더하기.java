import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static Integer[] dp = new Integer[11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());

//            dp = new Integer[n + 1];
            dp[3] = 4;  //3, 1+1+1, 2+1, 1+2
            dp[2] = 2;  //2, 1+1
            dp[1] = 1;  //1

            sb.append(recursion(n)).append("\n");
        }
        System.out.println(sb);
    }

    private static int recursion(int n) {
        if (dp[n] == null) {
            dp[n] = recursion(n - 3) + recursion(n - 2) + recursion(n - 1);
        }

        return dp[n];
    }
}
