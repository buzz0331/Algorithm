import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static int[] A, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        A = new int[M];
        Arrays.sort(arr);
        combi(0, 0);
    }

    private static void combi(int depth, int start) {
        if(depth == M) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < M; i++) {
                sb.append(A[i]).append(" ");
            }

            System.out.println(sb);
            return;
        }

        int prev = 0;
        for(int i = start; i < N; i++) {
            if(arr[i] == prev) continue;
            A[depth] = arr[i];
            prev = arr[i];
            combi(depth + 1, i);
            A[depth] = 0;
        }
    }
}
