import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer s_A = new StringTokenizer(br.readLine());
        StringTokenizer s_B = new StringTokenizer(br.readLine());
        ArrayList<Integer> A = new ArrayList<>(N);
        ArrayList<Integer> B = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(s_A.nextToken()));
            B.add(Integer.parseInt(s_B.nextToken()));
        }

        int sum = 0;

        for (int i = 0; i < N; i++) {
            int minA = Collections.min(A);
            int maxB = Collections.max(B);

            sum += minA * maxB;

            A.remove(Integer.valueOf(minA));
            B.remove(Integer.valueOf(maxB));
        }

        System.out.println(sum);
    }
}
