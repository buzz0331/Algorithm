import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] x = new int[N];
        int[] y = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0;

        for (int i = 0; i < N ; i++) {
            int j = (i + 1) % N;
            sum += (long) x[i] * y[j] - (long) y[i] * x[j];
        }

        double answer = (double) Math.abs(sum) / 2;

        System.out.printf("%.1f\n", answer);
    }
}
