import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[][] map = new boolean[N][N];

        /**
         * 2차원 배열 초기화
         */
        for (int i = 0; i < N; i++) {
            String[] strArr = br.readLine().split(""); //한줄을 strArr 담음
            for (int j = 0; j < N; j++) {
                map[i][j] = (Integer.parseInt(strArr[j]) == 1); //1이면 true, 0이면 false 삽입
            }
        }

        boolean[][] visited = new boolean[N][N]; //방명록
        ArrayList<Integer> complex = new ArrayList<>(); //아파트 단지 수 담는 리스트

        /**
         * 2차원 배열 BFS 탐색 시작
         */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Queue<Position> queue = new LinkedList<>();
                int count = 0;  //한 단지내 집의 수

                if (checkPosition(map, i, j, visited)) {
                    queue.offer(new Position(i, j));
                    visited[i][j] = true;
                    count++;
                }

                while (!queue.isEmpty()) {
                    Position pos = queue.poll();
                    int forwardX = pos.X + 1;
                    int forwardY = pos.Y + 1;
                    int backwardX = pos.X - 1;
                    int backwardY = pos.Y - 1;

                    if (forwardX < N) {
                        if (checkPosition(map, forwardX, pos.Y, visited)) {
                            queue.offer(new Position(forwardX, pos.Y));
                            visited[forwardX][pos.Y] = true;
                            count++;
                        }
                    }

                    if (backwardX >= 0) {
                        if (checkPosition(map, backwardX, pos.Y, visited)) {
                            queue.offer(new Position(backwardX, pos.Y));
                            visited[backwardX][pos.Y] = true;
                            count++;
                        }
                    }

                    if (forwardY < N) {
                        if (checkPosition(map, pos.X, forwardY, visited)) {
                            queue.offer(new Position(pos.X, forwardY));
                            visited[pos.X][forwardY] = true;
                            count++;
                        }
                    }

                    if (backwardY >= 0) {
                        if (checkPosition(map, pos.X, backwardY, visited)) {
                            queue.offer(new Position(pos.X, backwardY));
                            visited[pos.X][backwardY] = true;
                            count++;
                        }
                    }
                }
                if (count != 0) {
                    complex.add(count);
                }

            }
        }

        Collections.sort(complex);
        StringBuilder sb = new StringBuilder();
        sb.append(complex.size());
        for (int i = 0; i < complex.size(); i++) {
            sb.append("\n" + complex.get(i));
        }

        System.out.println(sb);
    }

    private static boolean checkPosition(boolean[][] map, int i, int j, boolean[][] visited) {
        return map[i][j] && !visited[i][j];
    }

    private static class Position{
        int X;
        int Y;

        public Position(int x, int y) {
            this.X = x;
            this.Y = y;
        }
    }
}
