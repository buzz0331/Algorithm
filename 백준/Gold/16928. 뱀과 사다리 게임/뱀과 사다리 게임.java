import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final Map<Integer, Integer> map = new HashMap<>();
    private static final Queue<Position> queue = new LinkedList<>();

    private static final int GOAL = 100;

    private static class Position {
        public int position;
        public int count;

        public Position(int position, int count) {
            this.position = position;
            this.count = count;
        }
    }

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        queue.offer(new Position(1, 0));

        bfs();

        System.out.println(min);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Position player = queue.poll();
            int nextCount = player.count + 1;

            if (nextCount > min) {       //백트래킹 (가지치기)
                continue;
            }

            if (player.position == GOAL) {
                min = Math.min(min, player.count);
            }

            boolean flag = true;
            for (int i = 6; i >= 1; i--) {
                int movePlayer = player.position + i;

                if (movePlayer > GOAL) {
                    continue;
                }

                if (movePlayer == GOAL) {
                    queue.offer(new Position(GOAL, nextCount));
                }
                else if (map.containsKey(movePlayer)) {
                    Integer move = map.get(movePlayer); // 사다리 또는 뱀을 통해 이동

                    queue.offer(new Position(move, nextCount));
                }
                else if (flag){
                    queue.offer(new Position(movePlayer, nextCount));
                    flag = false;
                }
            }
        }
    }
}
