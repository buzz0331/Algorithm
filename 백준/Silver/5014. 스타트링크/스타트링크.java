import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //F, S, G, U, D
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        final int[] dPos = {U, -D};
        int min = Integer.MAX_VALUE;

        boolean[] visited = new boolean[F+1];

        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(S, 0));

        if (S == G) {
            System.out.println(0);
            return;
        }

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            for (int i = 0; i < dPos.length; i++) {
                Position newPos = new Position(pos.s + dPos[i], pos.count + 1);
                if (newPos.s <= F && newPos.s >= 1) {
                    if (!visited[newPos.s]) {
                        queue.offer(newPos);
                        visited[newPos.s] = true;
                    }
                }

                if (newPos.s == G) {
                    min = Math.min(min, newPos.count);
                }
            }
        }


        if (min == Integer.MAX_VALUE) {
            System.out.println("use the stairs");
        } else {
            System.out.println(min);
        }

    }

    private static class Position {
        public int s;
        public int count;

        public Position(int s, int count) {
            this.s = s;
            this.count = count;
        }
    }
}
