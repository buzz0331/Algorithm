import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[][] numbers;
    private static int[][] maxDp;
    private static int[][] minDp;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        numbers = new int[N][3];
        maxDp = new int[N][3];
        minDp = new int[N][3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            numbers[i][0] = Integer.parseInt(st.nextToken());
            numbers[i][1] = Integer.parseInt(st.nextToken());
            numbers[i][2] = Integer.parseInt(st.nextToken());
        }

        dp();
        int max = Math.max(maxDp[0][0], Math.max(maxDp[0][1], maxDp[0][2]));
        int min = Math.min(minDp[0][0], Math.min(minDp[0][1], minDp[0][2]));

        StringBuilder sb = new StringBuilder();
        sb.append(max).append(" ").append(min);
        bw.write(sb.toString()); bw.flush();
    }

    private static void dp() {
        for(int i = 0; i < 3; i++) {
            maxDp[N - 1][i] = numbers[N - 1][i];
            minDp[N - 1][i] = numbers[N - 1][i];
        }

        for(int i = N - 2; i >= 0; i--) {
            maxDp[i][0] = numbers[i][0] + Math.max(maxDp[i + 1][0], maxDp[i + 1][1]);
            maxDp[i][1] = numbers[i][1] + Math.max(maxDp[i + 1][0], Math.max(maxDp[i + 1][1], maxDp[i + 1][2]));
            maxDp[i][2] = numbers[i][2] + Math.max(maxDp[i + 1][1], maxDp[i + 1][2]);

            minDp[i][0] = numbers[i][0] + Math.min(minDp[i + 1][0], minDp[i + 1][1]);
            minDp[i][1] = numbers[i][1] + Math.min(minDp[i + 1][0], Math.min(minDp[i + 1][1], minDp[i + 1][2]));
            minDp[i][2] = numbers[i][2] + Math.min(minDp[i + 1][1], minDp[i + 1][2]);
        }
    }
}
