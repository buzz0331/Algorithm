import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        TreeSet<String> treeSet = new TreeSet<>();
        for(int i = 0; i < N; i++) {
            treeSet.add(br.readLine());
        }

        int count = 0;
        for(int i = 0; i < M; i++) {
            String s = br.readLine();
            String ceiling = treeSet.ceiling(s);

            if(ceiling != null && ceiling.startsWith(s)) {
                count++;
            }
        }

        System.out.print(count);
    }
}
