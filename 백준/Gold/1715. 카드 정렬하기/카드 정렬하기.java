import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int count = 0;

        while(pq.size() > 1) {
            Integer a = pq.poll();
            Integer b = pq.poll();

            count += a + b;

            pq.offer(a + b);
        }

        System.out.println(count);
    }
}