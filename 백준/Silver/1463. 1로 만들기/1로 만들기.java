import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static Integer[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp = new Integer[N + 1];
        dp[0] = dp[1] = 0;

        System.out.println(findOne(N));
    }

    private static int findOne(int N) {
//        if (N == 0 || N == 1) {
//            return 0;
//        }
        if (dp[N] == null) {    //아직 방문하지 않은 dp일 때
            if (N % 6 == 0) {   //2와 3 모두 나누어지는 경우
                dp[N] = Math.min(Math.min(findOne(N / 2), findOne(N / 3)), findOne(N - 1)) + 1;
            }
            else if (N % 3 == 0) {  //3으로만 나누어지는 경우
                dp[N] = Math.min(findOne(N / 3), findOne(N - 1)) + 1;
            }
            else if (N % 2 == 0) {  //2으로만 나누어지는 경우
                dp[N] = Math.min(findOne(N / 2), findOne(N - 1)) + 1;
            }
            else {  //2와 3 모두 나누어지지 않는 경우
                dp[N] = findOne(N - 1) + 1;
            }
        }
        return dp[N];
    }
}
