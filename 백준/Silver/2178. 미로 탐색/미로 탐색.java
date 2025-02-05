import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] maze = new int[N][M];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split("");
            for (int j = 0; j < s.length; j++) {
                maze[i][j] = Integer.parseInt(s[j]);
            }
        }

        queue.offer(new Position(0, 0, 1));
        maze[0][0] = 0;

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            if (pos.X == N - 1 && pos.Y == M - 1) {
                min = Math.min(min, pos.count);
            }

            for (int i = 0; i < dx.length; i++) {
                Position newPos = new Position(pos.X + dx[i], pos.Y + dy[i], pos.count + 1);
                if (newPos.X >= 0 && newPos.X < N && newPos.Y >= 0 && newPos.Y < M) {
                    if (maze[newPos.X][newPos.Y] == 1) {
                        queue.offer(newPos);
                        maze[newPos.X][newPos.Y] = 0;
                    }
                }
            }
        }

        System.out.println(min);
    }
}
