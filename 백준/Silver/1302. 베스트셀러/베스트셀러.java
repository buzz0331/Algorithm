import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Map<String, Integer> bookMap = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        int max = -1;
        String maxKey = null;
        for(int i = 0; i < N; i++) {
            String title = br.readLine();

            Integer count = bookMap.getOrDefault(title, 0) + 1;
            if(count > max || (count == max && title.compareTo(maxKey) < 0)) {
                max = count;
                maxKey = title;
            }

            bookMap.put(title, count);
        }

        System.out.print(maxKey);
    }

}
