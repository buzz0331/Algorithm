import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int value = Integer.parseInt(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();
        int maxWidth = -1, maxHeight = -1;
        for(int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == 3 || a == 4) { // 높이
                maxHeight = Math.max(maxHeight, b);
            } else {
                maxWidth = Math.max(maxWidth, b);
            }

            list.add(b);
        }

        int sum = 0;
        list.add(list.get(0));
        for(int i = 0; i < 6; i++) {
            sum += list.get(i) * list.get(i + 1);
        }

        int extent = maxWidth * maxHeight;
        int answer = sum - 2 * extent;

        System.out.print(answer * value);
    }

}
