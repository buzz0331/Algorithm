import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        while(T --> 0) {
            int x = Integer.parseInt(br.readLine());

            if(x == 0) {
                if(!pq.isEmpty()) {
                    sb.append(pq.poll()).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else {
                pq.offer(x);
            }
        }

        System.out.print(sb);
    }
}
