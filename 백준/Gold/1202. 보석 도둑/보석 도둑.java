import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Jewel> jewels = new PriorityQueue<>();
        TreeMap<Integer, Integer> bagMap = new TreeMap<>(); // 가방 용량, 갯수

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewels.add(new Jewel(m, v));
        }

        for(int i = 0; i < K; i++) {
            int key = Integer.parseInt(br.readLine());
            bagMap.put(key, bagMap.getOrDefault(key, 0) + 1);
        }

        System.out.println(findMaxValue(jewels, bagMap));
    }

    private static long findMaxValue(PriorityQueue<Jewel> jewels, TreeMap<Integer, Integer> bagMap) {
        long sum = 0;
        int count = 0; // 가방에 들어간 보석 갯수

        while(!jewels.isEmpty()) {
            if(count == K) break;
            Jewel jewel = jewels.poll();

            Integer capacity = bagMap.ceilingKey(jewel.weight); // 현재 보석 무게 이상의 용량을 가진 가방 중 가장 가까운 수
            if(capacity == null) continue; // 만족하는 가방이 없음

            Integer num = bagMap.get(capacity); // 가방 갯수
            if(--num == 0) bagMap.remove(capacity);
            else bagMap.put(capacity, num);

            count++;
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
