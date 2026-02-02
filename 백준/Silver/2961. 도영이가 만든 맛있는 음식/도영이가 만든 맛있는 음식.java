import java.util.*;
import java.io.*;

public class Main {

    private static int min = Integer.MAX_VALUE;
    private static int N;
    private static int[] S, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        S = new int[N];
        B = new int[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        subSet(1, 0, 0, true);
        System.out.print(min);
    }

    private static void subSet(int mul, int tot, int depth, boolean nothing) {
        if(depth == N) {
            int result = Math.abs(mul - tot);
            if(!nothing) min = Math.min(min, result);
            return;
        }

        subSet(mul * S[depth], tot + B[depth], depth + 1, false);
        subSet(mul, tot, depth + 1, nothing);
    }
}
