import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] seq;           // 수열을 저장하는 정수 배열
    private static int N;
    private static int S;
    private static int count = 0;       // 부분 수열의 개수
    private static int sum;             // 현재까지의 부분 수열의 합

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        seq = new int[N + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            sum = seq[i];
            dfs(1, i);
        }

        System.out.println(count);
    }

    private static void dfs(int depth, int root) {
        if (sum == S) {
            count ++;
        }

        if (depth == N) {
            return;
        }

        for (int i = root + 1; i <= N; i++) {
            sum += seq[i];
            dfs(depth + 1, i);
            sum -= seq[i];
        }
    }
}
