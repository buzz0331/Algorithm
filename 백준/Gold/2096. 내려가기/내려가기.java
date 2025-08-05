import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[][] maxDp = new int[2][3];
    private static int[][] minDp = new int[2][3];
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 3; j++) {
            int val = Integer.parseInt(st.nextToken());
            maxDp[0][j] = val;
            minDp[0][j] = val;
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            maxDp[1][0] = a + Math.max(maxDp[0][0], maxDp[0][1]);
            maxDp[1][1] = b + Math.max(Math.max(maxDp[0][0], maxDp[0][1]), maxDp[0][2]);
            maxDp[1][2] = c + Math.max(maxDp[0][1], maxDp[0][2]);

            minDp[1][0] = a + Math.min(minDp[0][0], minDp[0][1]);
            minDp[1][1] = b + Math.min(Math.min(minDp[0][0], minDp[0][1]), minDp[0][2]);
            minDp[1][2] = c + Math.min(minDp[0][1], minDp[0][2]);

            for (int j = 0; j < 3; j++) {
                maxDp[0][j] = maxDp[1][j];
                minDp[0][j] = minDp[1][j];
            }
        }

        int max = Math.max(maxDp[0][0], Math.max(maxDp[0][1], maxDp[0][2]));
        int min = Math.min(minDp[0][0], Math.min(minDp[0][1], minDp[0][2]));

        bw.write(max + " " + min);
        bw.flush();
    }
}