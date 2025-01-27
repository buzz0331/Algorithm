import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static boolean[] visited;
    private static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());   //수빈이 위치
        int goal = Integer.parseInt(st.nextToken());    //동생 위치

        int min = Integer.MAX_VALUE;
        visited = new boolean[MAX + 1];        //수빈이가 방문한 위치

        min = bfs(X, goal, min);

        System.out.println(min);

    }

    private static int bfs(int X, int goal, int min) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(X, 0));
        visited[X] = true;

        while (!queue.isEmpty()) {
            Position position = queue.poll();
            visited[position.X] = true;

            if (position.X == goal) {
                min = Math.min(min, position.second);
            }

            if(position.X * 2 <= MAX && !visited[position.X * 2])
                queue.offer(new Position(position.X * 2, position.second));
            if(position.X + 1 <= MAX && !visited[position.X + 1])
                queue.offer(new Position(position.X + 1, position.second + 1));
            if (position.X - 1 >= 0 && !visited[position.X - 1])
                queue.offer(new Position(position.X - 1, position.second + 1));

        }
        return min;
    }

    private static class Position {
        public int X;
        public int second;

        public Position(int x, int second) {
            X = x;
            this.second = second;
        }
    }
}
