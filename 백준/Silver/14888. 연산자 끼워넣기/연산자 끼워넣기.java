import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] sequence;
    private static int[] op = new int[4];

    private static long max = Long.MIN_VALUE;
    private static long min = Long.MAX_VALUE;
    private static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        sequence = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken()); //수열 초기화
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, sequence[1], op);

        StringBuilder sb = new StringBuilder();

        sb.append(max).append("\n");
        sb.append(min).append("\n");
        System.out.println(sb);
    }

    private static void dfs(int currentSeq, long sum, int[] op) {

        for (int i = 0; i < 4; i++) {
            int[] temp = op.clone();
            if (temp[i] == 0) {
                continue;
            } else {
                temp[i] -= 1;
            }

            long result = operation(i, sum, sequence[currentSeq + 1]);   //현재 결과 연산


            currentSeq += 1;

            if (currentSeq == N) {
                max = Math.max(result, max);
                min = Math.min(result, min);
                return;
            }

            dfs(currentSeq, result, temp);
            currentSeq -= 1;
        }

    }

    private static long operation(int opIndex, long op1, int op2) {

        if (opIndex == 0) {
            return op1 + op2;
        }
        if (opIndex == 1) {
            return op1 - op2;
        }
        if (opIndex == 2) {
            return op1 * op2;
        }
        if (opIndex == 3) {
            return op1 / op2;
        }

        return 0;
    }
}
