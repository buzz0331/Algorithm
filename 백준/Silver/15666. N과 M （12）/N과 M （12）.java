import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static int[] A, arr;
    private static Set<String> answer = new HashSet<>();

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

            String result = sb.toString();
            if(answer.contains(result)) return;

            answer.add(result);
            System.out.println(result);
            return;
        }

        for(int i = start; i < N; i++) {
            A[depth] = arr[i];
            combi(depth + 1, i);
            A[depth] = 0;
        }
    }
}
