import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static boolean[] visited;
    private static int N;
    private static int M;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (M == 1) {
            for (int i = 1; i <= N; i++) {
                System.out.println(i);
            }
            return;
        }

        for (int i = 1; i < N + 1; i++) {

            for (int j = 1; j < N + 1; j++) {
                visited = new boolean[N + 1];
                StringBuilder sb = new StringBuilder();
                visited[i] = true;
                sb.append(i).append(" ");
                count = 1;

                if (!visited[j]) {
                    sb.append(j).append(" ");
                    dfs(j, sb);
                }
            }
        }

    }

    private static void dfs(int root, StringBuilder sb) {
        count ++;
//        localSb.append(root).append(" ");    //앞의 단계에서 이어붙인 문자열에서 이어서 작성
        visited[root] = true;

        if (count == M) {
            System.out.println(sb);
            return;
        }

        for (int i = 1; i < N + 1; i++) {
            StringBuilder localSb = new StringBuilder();
//            localSb.append(i).append(" ");
            if (!visited[i]) {
                dfs(i, localSb.append(sb).append(i).append(" "));
                count --;
                visited[i] = false;
            }
        }
    }
}
