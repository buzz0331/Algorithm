import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] star;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        star = new char[N][2 * N - 1];

        for(int i = 0; i < N; i++) {
            Arrays.fill(star[i], ' ');
        }

        printStart(0, N - 1, N);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(star[i][j]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void printStart(int row, int col, int n) {
        if(n == 3) {
            star[row][col] = '*';
            star[row + 1][col - 1] = star[row + 1][col + 1] = '*';
            star[row + 2][col - 2] = star[row + 2][col - 1] = star[row + 2][col] = star[row + 2][col + 1] = star[row + 2][col + 2] = '*';
            return;
        }

        int ns = n / 2;
        printStart(row, col , ns);
        printStart(row + ns, col + ns, ns);
        printStart(row + ns, col - ns, ns);
    }
}
