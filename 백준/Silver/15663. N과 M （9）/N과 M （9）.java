import java.util.*;
import java.io.*;

public class Main {

    private static int[] arr, select;
    private static boolean[] visited;
    private static int N, M;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        select = new int[M];
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
                if(i == M - 1) {
                    sb.append(select[i]);
                    break;
                }
                sb.append(select[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prev = 0;
        for(int i = 0; i < N; i++) {
            if(prev == arr[i] || visited[i]) continue;

            prev = arr[i];
            visited[i] = true;
            select[depth] = arr[i];
            perm(depth + 1);
            select[depth] = 0;
            visited[i] = false;
        }
    }
}
