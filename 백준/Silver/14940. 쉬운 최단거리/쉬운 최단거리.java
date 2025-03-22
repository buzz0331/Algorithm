import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static Position goal;
    private static int[][] map;

    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    private static boolean[][] visited;
    private static int n;
    private static int m;

    private static final Queue<Position> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int parseInt = Integer.parseInt(st.nextToken());

                if (parseInt == 2) {
                    goal = new Position(i, j, 0);  // goal 초기화
//                    map[i][j] = 0;
                }
//                if (parseInt == 0) {
//                    map[i][j] = 0;
//                }
                if (parseInt == 1) {
                    map[i][j] = -1;  // 우선 갈 수 없는 곳으로 초기화!
                }
            }
        }

        queue.offer(goal);
        visited[goal.X][goal.Y] = true;
        bfs();

        printMap();
    }

    private static void bfs() {

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            for (int i = 0; i < 4; i++) {

                int moveX = pos.X + dx[i];
                int moveY = pos.Y + dy[i];

                if (validatePosition(moveX, n) && validatePosition(moveY, m)) {
                    if (map[moveX][moveY] != 0 && !visited[moveX][moveY]) {
                        visited[moveX][moveY] = true;
                        queue.offer(new Position(moveX, moveY, pos.count + 1));
                        map[moveX][moveY] = pos.count + 1;
                    }
                }
            }
        }

    }

    private static boolean validatePosition(int pos, int length) {
        return pos >= 0 && pos < length;
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

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
}
