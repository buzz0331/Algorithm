import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t --> 0) {
            int n = Integer.parseInt(br.readLine());
            TreeSet<String> set = new TreeSet<>();

            for(int i = 0; i < n; i++) {
                String x = br.readLine();

                String lower = set.lower(x);
                String higher = set.higher(x);

                if ((lower != null && x.startsWith(lower)) || (higher != null && higher.startsWith(x))) {
                    sb.append("NO").append("\n");
                    while(++i < n) br.readLine();
                    break;
                }

                set.add(x);

                if (i == n - 1) {
                    sb.append("YES").append("\n");
                }
            }
        }

        System.out.print(sb);
    }
}
