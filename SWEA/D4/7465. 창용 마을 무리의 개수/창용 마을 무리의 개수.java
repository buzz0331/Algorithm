import java.util.*;
import java.io.*;

class Solution
{

    private static int[] parents;
    private static int count;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            parents = new int[N + 1];
            count = N;

            for(int i = 1; i <= N; i++) {
                parents[i] = i;
            }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                boolean result = union(x, y);
                if(result) count--;
            }

            sb.append(count);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int find(int x) {
        if(x == parents[x]) return parents[x];

        return parents[x] = find(parents[x]);
    }

    private static boolean union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX == parentY) return false;

        if(parentX <= parentY) {
            parents[parentY] = parentX;
        } else {
            parents[parentX] = parentY;
        }

        return true;
    }
}