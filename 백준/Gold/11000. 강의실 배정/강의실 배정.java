import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Time> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.offer(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        TreeMap<Integer, Integer> room = new TreeMap<>(); // 방 마지막 종료 시각, 방 갯수
        int answer = 0;
        while(!pq.isEmpty()) {
            Time time = pq.poll();

            Integer key = room.floorKey(time.start);// 방에서 현재 스케줄 시작 시간보다 작거나 같은 값 중에서 가장 큰 값
            if (key != null) {
                // 기존 방 사용
                int roomCount = room.get(key);
                if(--roomCount == 0) room.remove(key); // 방 갯수가 0이면 key 제거
                else room.put(key, roomCount); // 아니면 유지
            } else {
                answer++;
            }

            room.put(time.end, room.getOrDefault(time.end, 0) + 1);
        }

        System.out.print(answer);
    }

    private static class Time implements Comparable<Time> {
        private int start;
        private int end;

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time o) {
            if(this.start == o.start) {
                return Integer.compare(o.end - this.end, this.end - this.start);
            }
            return Integer.compare(this.start, o.start);
        }
    }
}
