import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static Boolean[] rock_game;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int t1 = N - 1;
        int t2 = N - 3;

        if (t1 % 4 == 0 || t2 % 4 == 0) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }

    }
}
