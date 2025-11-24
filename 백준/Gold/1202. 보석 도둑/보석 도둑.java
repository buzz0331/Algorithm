import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        int[] bags = new int[K];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewels[i] = new Jewel(m, v);
        }

        for(int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels, Comparator.comparingInt(j -> j.weight)); // 보석 무게 기준으로 오름차순 정렬
        Arrays.sort(bags); // 가방 용량으로 오름차순 정렬

        System.out.println(findMaxValue(jewels, bags));
    }

    private static long findMaxValue(Jewel[] jewels, int[] bags) {
        PriorityQueue<Jewel> max = new PriorityQueue<>();
        long sum = 0;
        int pointer = 0;

        for(int i = 0; i < bags.length; i++) {
            int capacity = bags[i];

            while(pointer < jewels.length && capacity >= jewels[pointer].weight) { // 해당 가방에 들어갈 수 있는 모든 보석들을 우선순위 큐에 삽입
                max.offer(jewels[pointer]);
                pointer++;
            }

            if(max.isEmpty()) continue;

            Jewel jewel = max.poll(); // 현재 가방에 들어갈 수 있는 보석 중 가치가 가장 높은 보석 선택
            sum += jewel.value;
        }
        return sum;
    }

    private static class Jewel implements Comparable<Jewel> {
        public int weight;
        public int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            return o.value - this.value;
        }
    }
}
