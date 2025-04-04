import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        StringBuilder sb = new StringBuilder();
//
//        int M = Integer.parseInt(st.nextToken());
//        int N = Integer.parseInt(st.nextToken());
//
//        for (int i = M; i <= N; i++) {
//            if (checkPrimeNumber(i, sb) && i != 1) {
//                sb.append(i).append("\n");
//            }
//        }
//
//        System.out.println(sb);
//    }
//
//    private static boolean checkPrimeNumber(int i, StringBuilder sb) {
//        for (int j = 2; j <= (int) Math.sqrt(i); j++) {
//            if (i % j == 0) {
//                return false;
//            }
//        }
//        return true;
//    }
//}

/**
 * 에라토스테네스의 체
 */
public class Main {

    private static int[] prime;
    private static final int PRIME = 0;
    private static final int NOT_PRIME = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        prime = new int[N + 1];
        prime[1] = NOT_PRIME;

        for (int i = 2; i <= N; i++) {
            if (prime[i] == NOT_PRIME) {
                continue;
            }

            if (i >= M) {
                sb.append(i).append("\n");
            }

            int primeNumber = i;
            int multiply = 2;
            while (primeNumber * multiply <= N) {
                prime[primeNumber * multiply] = NOT_PRIME;
                multiply++;
            }
        }

        System.out.println(sb);

    }
}
