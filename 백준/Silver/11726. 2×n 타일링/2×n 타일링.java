import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int[] fib = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n == 1 || n == 2) {
            System.out.println(n);
            return;
        }

        fib[1] = 1;
        fib[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            fib[i] = (fib[i - 1] + fib[i - 2]) % 10007;
        }

        System.out.println(fib[n]);

    }
}
