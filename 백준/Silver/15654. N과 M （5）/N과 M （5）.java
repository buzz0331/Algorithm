import java.util.*;
import java.io.*;

public class Main {

    private static int[] arr, seq;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        seq = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        perm(0);
        System.out.print(sb);
    }

    private static void perm(int depth) {
        if(depth == M) {
            for(int i = 0; i < M; i++) {
                sb.append(seq[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;
            seq[depth] = arr[i];
            visited[i] = true;
            perm(depth + 1);
            seq[depth] = 0;
            visited[i] = false;
        }
    }
}
