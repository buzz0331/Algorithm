import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        int row_size = Integer.parseInt(size[0]);
        int col_size = Integer.parseInt(size[1]);

        String[] line = new String[row_size];
        for (int i = 0; i < row_size; i++) {
            line[i] = br.readLine();
        }

        int min = 64; //칠하는 최소 정사각형

        for (int start_row = 0; start_row < row_size - 7; start_row++) {
            for (int start_col = 0; start_col < col_size - 7; start_col++) {
                int temp = getMin(start_row, start_col, line);
                min = Math.min(temp, min);
            }
        }

        System.out.println(min);

    }

    private static int getMin(int start_row, int start_col, String[] line) {
        final String WHITE = "WBWBWBWB";
        final String BLACK = "BWBWBWBW";
        int count = 0;

        for (int i = 0; i < 8; i++) { //8x8 정사각형 조회
            String check = line[start_row+i].substring(start_col, start_col +8);
            for (int j = 0; j < 8; j++) {
                count = getCount(i, count, BLACK, j, check, WHITE);
            }
        }

        count = Math.min(count, 64 - count);

        return count;
    }

    private static int getCount(int i, int count, String BLACK, int j, String check, String WHITE) {
        if (i % 2 == 0) {
            count = compareLine(WHITE, j, check, count);
        } else {
            count = compareLine(BLACK, j, check, count);
        }

        return count;
    }

    private static int compareLine(String s, int j, String check, int count) {
        if (s.charAt(j) != check.charAt(j)) {
            count++;
        }
        return count;
    }
}
