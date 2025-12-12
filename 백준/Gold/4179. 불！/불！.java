import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] dRowCol = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int[][] maze;
    private static int R, C;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maze = new int[R][C];
        Point startPoint = null;
        List<Point> firePoints = new ArrayList<>();

        for(int i = 0; i < R; i++) {
            String[] s = br.readLine().split("");

            for(int j = 0; j < C; j++) {
                switch(s[j]) {
                    case "#":
                        maze[i][j] = -1;
                        break;
                    case "J":
                        startPoint = new Point(i, j);
                        break;
                    case "F":
                        firePoints.add(new Point(i, j));
                        break;
                    default:
                }
            }
        }

        calFireTime(firePoints);
//        for(int[] m : maze) {
//            Arrays.stream(m).forEach(System.out::print);
//            System.out.println();
//        }
        int answer = calEscapeTime(startPoint);
        System.out.print(answer == -1 ? "IMPOSSIBLE" : answer);
    }

    private static int calEscapeTime(Point startPoint) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        queue.offer(new int[]{startPoint.row, startPoint.col, 0});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentRow = current[0];
            int currentCol = current[1];
            int currentTime = current[2];

            if(visited[currentRow][currentCol]) continue;
            if(maze[currentRow][currentCol] != 0 && currentTime >= maze[currentRow][currentCol]) continue; // 이미 불이 난 곳

            visited[currentRow][currentCol] = true;

            if(currentRow == 0 || currentCol == 0 || currentRow == R - 1 || currentCol == C - 1)
                return currentTime + 1;

            for(int[] rowcol : dRowCol) {
                int nextRow = currentRow + rowcol[0];
                int nextCol = currentCol + rowcol[1];

                if(nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C) continue;
                if(maze[nextRow][nextCol] == -1) continue;

                if(!visited[nextRow][nextCol] && (maze[currentRow][currentCol] == 0 || currentTime + 1 < maze[nextRow][nextCol]))
                    queue.offer(new int[]{nextRow, nextCol, currentTime + 1});
            }
        }

        return -1;
    }

    private static void calFireTime(List<Point> firePoints) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        for(Point firePoint : firePoints) {
            queue.offer(new int[]{firePoint.row, firePoint.col, 0});
        }

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentRow = current[0];
            int currentCol = current[1];
            int currentTime = current[2];

            if(visited[currentRow][currentCol]) continue;
            maze[currentRow][currentCol] = currentTime;
            visited[currentRow][currentCol] = true;

            for(int[] rowcol : dRowCol) {
                int nextRow = currentRow + rowcol[0];
                int nextCol = currentCol + rowcol[1];

                if(nextRow < 0 || nextCol < 0 || nextRow >= R || nextCol >= C) continue;
                if(maze[nextRow][nextCol] == -1) continue;

                if(!visited[nextRow][nextCol]) queue.offer(new int[]{nextRow, nextCol, currentTime + 1});
            }
        }

        firePoints.stream().forEach(f -> maze[f.row][f.col] = -1); // 처음 발화지점은 벽으로 간주
    }

    private static class Point {
        public int row;
        public int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
