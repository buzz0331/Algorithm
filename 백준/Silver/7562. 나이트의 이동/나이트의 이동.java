import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = {2, 2, -2, -2, 1, -1, 1, -1};
    private static final int[] dy = {1, -1, 1, -1, 2, 2, -2, -2};

    private static final Queue<Position> queue = new LinkedList<>();

    private static class Position {
        public int X;
        public int Y;
        public int count;

        public Position(int x, int y, int count) {
            X = x;
            Y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
//        int[] results = new int[T];

        for (int i = 0; i < T; i++) {
            int size = Integer.parseInt(br.readLine()); //체스판 크기 (size x size)
            boolean[][] visited = new boolean[size][size];

            int min = Integer.MAX_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());
            Position start = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

            st = new StringTokenizer(br.readLine());
            Position end = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

            if (start.X == end.X && start.Y == end.Y) {
                System.out.println(start.count);
                continue;
            }

            queue.offer(start);
            visited[start.X][start.Y] = true;

            while (!queue.isEmpty()) {
                Position pos = queue.poll();
                int count = pos.count;

                for (int j = 0; j < dx.length; j++) {
                    Position nextPos = new Position(pos.X + dx[j], pos.Y + dy[j], count + 1);
//                    System.out.println(nextPos.count);

                    if (nextPos.X >= 0 && nextPos.X < size && nextPos.Y >= 0 && nextPos.Y < size) {
                        if (!visited[nextPos.X][nextPos.Y]) {
                            queue.offer(nextPos);
                            visited[nextPos.X][nextPos.Y] = true;
                        }
                    }

                    if (nextPos.X == end.X && nextPos.Y == end.Y) {
                        min = Math.min(min, nextPos.count);
                    }
                }

            }

            System.out.println(min);


        }
    }

}
