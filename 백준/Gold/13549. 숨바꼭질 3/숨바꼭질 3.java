

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int MAX = 100001;
    private static final boolean[] visited = new boolean[MAX];
    private static final Queue<Position> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int result = Integer.MAX_VALUE;

        queue.add(new Position(N, 0));

        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            visited[pos.position] = true;

            int multiply = pos.position * 2;

            if (pos.position == K) {
                result = Math.min(result, pos.count);
            }

            if (multiply < MAX) {
                if (!visited[multiply]) {
                    queue.add(new Position(multiply, pos.count));
                }
            }

            if (pos.position + 1 < MAX) {
                if (!visited[pos.position + 1]) {
                    queue.add(new Position(pos.position + 1, pos.count + 1));
                }
            }

            if (pos.position - 1 >= 0) {
                if (!visited[pos.position - 1]) {
                    queue.add(new Position(pos.position - 1, pos.count + 1));
                }
            }

        }

        System.out.println(result);
    }

    private static class Position {
        public int position;
        public int count;

        public Position(int position, int count) {
            this.position = position;
            this.count = count;
        }
    }
}
