import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {       //Test case 시작

            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int count = 0;

            boolean[][] field = new boolean[M][N];

            for (int j = 0; j < K; j++) {   //배추 위치 조회 시작

                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                field[x][y] = true;
            }

            boolean[][] visited = new boolean[M][N]; //방문 기록 조회용

            for (int j = 0; j < M; j++) { //배추 탐색 시작
                for (int k = 0; k < N; k++) {

                    if (checkValid(field, j, k, visited)) {     //배추가 없는 곳이거나 방문한 곳이면
                        continue;
                    }

                    Queue<Position> queue = new LinkedList<>();
                    queue.offer(new Position(j, k));
                    count++; //배추 흰 지렁이 갯수 1 증가

                    while (!queue.isEmpty()) {
                        Position position = queue.poll();
                        int forwardX = position.X + 1;
                        int forwardY = position.Y + 1;
                        int backwardX = position.X - 1;
                        int backwardY = position.Y - 1;

                        if (forwardX < M) {
                            if (!checkValid(field, forwardX, position.Y, visited)) { //배추가 있는 곳이고 방문한 적이 없는 곳
                                queue.offer(new Position(forwardX, position.Y));
                                visited[forwardX][position.Y] = true;
                            }
                        }

                        if (backwardX >= 0) {
                            if (!checkValid(field, backwardX, position.Y, visited)) { //배추가 있는 곳이고 방문한 적이 없는 곳
                                queue.offer(new Position(backwardX, position.Y));
                                visited[backwardX][position.Y] = true;
                            }
                        }

                        if (forwardY < N) {
                            if (!checkValid(field, position.X, forwardY, visited)) { //배추가 있는 곳이고 방문한 적이 없는 곳
                                queue.offer(new Position(position.X, forwardY));
                                visited[position.X][forwardY] = true;
                            }
                        }

                        if (backwardY >= 0) {
                            if (!checkValid(field, position.X, backwardY, visited)) { //배추가 있는 곳이고 방문한 적이 없는 곳
                                queue.offer(new Position(position.X, backwardY));
                                visited[position.X][backwardY] = true;
                            }
                        }

                    }

                }
            }

            sb.append(count + "\n");
        }

        System.out.print(sb);
    }

    private static boolean checkValid(boolean[][] field, int j, int k, boolean[][] visited) {
        //배추가 없는 곳이거나 방문한 곳이면
        return !field[j][k] || visited[j][k];
    }

    private static class Position {
        public int X;
        public int Y;

        public Position(int x, int y) {
            this.X = x;
            this.Y = y;
        }
    }

}
