import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dates = new int[366];
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for (int j = start; j <= end; j++) {
                dates[j]++;
            }
        }
        checkDates();
        System.out.println(result);

    }

    private static void checkDates() {
        for (int i = 1; i <= 365; i++) {
            if (dates[i] > 0) {
                i = calSquare(i);
            }
        }
    }

    private static int calSquare(int idx) {
        int height = 0;
        int j = idx;
        while (j <= 365 && dates[j] > 0) {
            height = Math.max(height, dates[j]);
            j++;
        }
        int width = j - idx;
        result += width * height;
        return j;
    }
}
