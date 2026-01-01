import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            lectures[i] = new Lecture(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lectures, (l1, l2) -> l1.start == l2.start ? Integer.compare(l1.end, l2.end) : Integer.compare(l1.start, l2.start));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].end);

        for(int i = 1; i < N; i++) {
            // 끝나는 시각이 가장 작은 강의실보다 현재 강의의 시작시간이 더 크거나 같다면 해당 강의실 사용 가능
            if(pq.peek() <= lectures[i].start) {
                pq.poll();
            }
            pq.offer(lectures[i].end);
        }

        System.out.print(pq.size());
    }

    private static class Lecture {
        public int number;
        public int start;
        public int end;

        public Lecture(int number, int start, int end) {
            this.number = number;
            this.start = start;
            this.end = end;
        }
    }
}
