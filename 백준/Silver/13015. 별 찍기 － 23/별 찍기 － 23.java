import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 첫줄
        printLine(N, sb);

        // 중간
        for(int row = 1; row < N; row++) {
            printMiddleLine(row, sb, N);
        }

        for(int row = N - 2; row >= 1; row--) {
            printMiddleLine(row, sb, N);
        }



        // 끝줄
        printLine(N, sb);
        System.out.print(sb);
    }

    private static void printMiddleLine(int row, StringBuilder sb, int N) {
        //처음 공백
        for(int i = 0; i < row; i++) {
            sb.append(" ");
        }

        sb.append("*");

        // * 사이
        for(int i = 0; i < N - 2; i++) {
            sb.append(" ");
        }

        sb.append("*");

        // * 중간 공백
        for(int i = 0; i < 2 *(N - row) - 3; i++) {
            sb.append(" ");
        }

        if(row != N - 1) sb.append("*");

        // * 사이
        for(int i = 0; i < N - 2; i++) {
            sb.append(" ");
        }

        sb.append("*");

        sb.append("\n");
    }

    private static void printLine(int N, StringBuilder sb) {
        for(int i = 0; i < N; i++) {
            sb.append("*");
        }

        for(int i = 0; i < (N - 2) * 2 + 1; i++) {
            sb.append(" ");
        }

        for(int i = 0; i < N; i++) {
            sb.append("*");
        }
        sb.append("\n");
    }
}
