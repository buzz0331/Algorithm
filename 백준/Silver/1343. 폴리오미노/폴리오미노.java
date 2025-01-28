import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), ".", true);

        StringBuilder sb = new StringBuilder();

        while (st.hasMoreTokens()) {

            String s = st.nextToken();

            if (s.equals(".")) {
                sb.append(".");
                continue;
            }

            int length = s.length();

            if (length % 2 != 0) {      //덮을 수 없는 경우 -1 출력
                System.out.println(-1);
                return;
            }

            while (length - 4 >= 0) {
                sb.append("AAAA");
                length -= 4;
            }

            while (length - 2 >= 0) {
                sb.append("BB");
                length -= 2;
            }

        }

        System.out.println(sb);
    }
}
