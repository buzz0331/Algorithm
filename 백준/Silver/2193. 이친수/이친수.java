import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int N;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(1);
            return;
        }

        long[] p = new long[N + 1];
        p[1] = 1;
        p[2] = 1;

        for (int i = 2; i < N + 1; i++) {
            p[i] = p[i - 2] + p[i - 1];
        }

        System.out.println(p[N]);

    }

}
