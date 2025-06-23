import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs(0, 0);

    }

    private static void dfs(int depth, int start) {
        if(depth == M) {
            System.out.println(sb);
            return;
        }

        for(int i = start + 1; i <= N; i++) {
            sb.append(i).append(" ");
            dfs(depth + 1, i);
            sb.deleteCharAt(depth * 2 + 1);
            sb.deleteCharAt(depth * 2);
        }
    }

}
