import java.util.*;
import java.io.*;

public class Main {

    private static int[] numbers, op = new int[4];
    private static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    private static int[] p;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        p = new int[N - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        backTracking(0);
        System.out.println(max);
        System.out.println(min);
    }

    private static void backTracking(int depth) {
        if(depth == N - 1) {
            calculate();
            return;
        }

        for(int i = 0; i < 4; i++) {
            if(op[i] <= 0) continue;

            op[i]--;
            p[depth] = i;
            backTracking(depth + 1);
            op[i]++;
        }
    }

    private static void calculate() {
        int result = numbers[0];
        for(int i = 0; i < N - 1; i++) {
            int num = numbers[i + 1];
            switch(p[i]) {
                case 0: // 덧셈
                    result += num;
                    break;
                case 1: // 뺄셈
                    result -= num;
                    break;
                case 2: // 곱셈
                    result *= num;
                    break;
                case 3: // 나눗셈
                    result /= num;
                    break;
            }
        }

        max = Math.max(max, result);
        min = Math.min(min, result);
    }
}
