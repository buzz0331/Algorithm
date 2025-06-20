import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map;
    private static int babyRow, babyCol, N;

    private static int[][] dRowCol = {{-1,0}, {0,-1}, {0,1}, {1,0}};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int shark = Integer.parseInt(st.nextToken());
                if(shark == 9) {
                    babyRow = i;
                    babyCol = j;
                }
                map[i][j] = shark;
            }
        }

        System.out.println(babyShark());

    }

    private static int babyShark() {
        int dayCount = 0;
        int babySize = 2;

        int eatCount = 0;
        Position eatableShark = bfs(babySize);
        while(eatableShark != null) {
            map[babyRow][babyCol] = 0;

            babyRow = eatableShark.row;
            babyCol = eatableShark.col;

            map[babyRow][babyCol] = 9;
            eatCount++;
            if(eatCount == babySize) {
                babySize++;
                eatCount = 0;
            }
            dayCount += eatableShark.count;
            eatableShark = bfs(babySize);
        }

        return dayCount;
    }

    private static Position bfs(int babySize) {
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(new Position(babyRow, babyCol, 0));
        visited[babyRow][babyCol] = true;

        List<Position> eatable = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;

        while(!queue.isEmpty()) {
            Position currentPos = queue.poll();

            for(int i = 0; i < 4; i++) {
                int newRow = currentPos.row + dRowCol[i][0];
                int newCol = currentPos.col + dRowCol[i][1];

                if(newRow < 0 || newCol < 0 || newRow >= N || newCol >= N) continue;
                if(visited[newRow][newCol]) continue;
                if(map[newRow][newCol] > babySize) continue;

                visited[newRow][newCol] = true;
                int nextCount = currentPos.count + 1;

                if(map[newRow][newCol] != 0 && map[newRow][newCol] < babySize) {
                    if(nextCount <= minDist) {
                        minDist = nextCount;
                        eatable.add(new Position(newRow, newCol, nextCount));
                    }
                } else {
                    queue.offer(new Position(newRow, newCol, nextCount));
                }
            }
        }

        if (eatable.isEmpty()) return null;

        eatable.sort((a, b) -> {
            if (a.row != b.row) return Integer.compare(a.row, b.row);
            return Integer.compare(a.col, b.col);
        });

        return eatable.get(0);
    }

    private static class Position {
        public int row;
        public int col;
        public int count;

        public Position(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }
}
