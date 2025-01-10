import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[] charges = {5,2};
        int num_of_charge = 0;

        num_of_charge += n / charges[0];
        n %= charges[0];

        if (num_of_charge != 0 && n % 2 != 0) { //5로 나눠졌을 때, 나머지가 홀수인 경우
            num_of_charge --;
            n += 5;
        }

        num_of_charge += n / charges[1];
        n %= charges[1];

        if (n != 0) {
            bw.write(String.valueOf(-1));
            bw.flush();
            return;
        }

        bw.write(String.valueOf(num_of_charge));
        bw.flush();

    }
}
