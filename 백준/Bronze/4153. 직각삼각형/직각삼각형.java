import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    private static final String RIGHT = "right";
    private static final String WRONG = "wrong";
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            ArrayList<Integer> i = new ArrayList<>(3);

            for (int j = 0; j < 3; j++) {
                i.add(Integer.parseInt(st.nextToken()));
            }

            if (i.get(0) == 0 && i.get(1) == 0 && i.get(2) == 0) {
                break;
            }

            Collections.sort(i);

            if (Math.pow(i.get(0), 2) + Math.pow(i.get(1), 2) == Math.pow(i.get(2), 2)) {
                sb.append(RIGHT).append("\n");
            } else {
                sb.append(WRONG).append("\n");
            }
        }

        System.out.println(sb);
    }
}
