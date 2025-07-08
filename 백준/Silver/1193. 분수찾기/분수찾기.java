import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int line = 1;

        // 몇 번째 대각선(line)에 위치하는지 찾기
        while (n > line) {
            n -= line;
            line++;
        }

        int numerator;
        int denominator;

        // 짝수 번째 대각선: 위에서 아래로
        if (line % 2 == 0) {
            numerator = n;
            denominator = line - n + 1;
        } else {
            // 홀수 번째 대각선: 아래에서 위로
            numerator = line - n + 1;
            denominator = n;
        }

        System.out.println(numerator + "/" + denominator);
    }
}