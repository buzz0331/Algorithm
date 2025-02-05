import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Long[] tips = new Long[N];

        for (int i = 0; i < N; i++) {
            tips[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(tips, Comparator.reverseOrder());

        long sum = 0;

        for (int i = 0; i < tips.length; i++) {
            long cal_tip = tips[i] - i;
            if (cal_tip > 0) {
                sum += cal_tip;
            }
        }

        System.out.println(sum);


    }
}
