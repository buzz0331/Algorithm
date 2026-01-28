import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] p;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = new int[M];

        StringBuilder sb = new StringBuilder();
        perm(sb, 0);

        System.out.print(sb);
    }

    private static void perm(StringBuilder sb, int count) {
        if(count == M) {
            for(int i = 0; i < p.length; i++) {
                sb.append(p[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= N; i++) {
            p[count] = i;
            perm(sb, count + 1);
            p[count] = 0;
        }
    }
}
