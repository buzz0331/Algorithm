import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 첫째 줄 입력 받기
        int N = sc.nextInt();
        int X = sc.nextInt();
        
        // 둘째 줄 입력 받기
        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            if (num < X) {
                System.out.print(num + " ");
            }
        }
    }
}