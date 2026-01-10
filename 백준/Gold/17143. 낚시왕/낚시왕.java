import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int R, C;
    private static Shark[][] map;

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new Shark[R][C];

        int minRow = Integer.MAX_VALUE;
        boolean sharkExisted = false;
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            map[row][col] = new Shark(
                    row, col,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );

            if(col == 0) {
                minRow = Math.min(minRow, row);
                sharkExisted = true;
            }
        }

        int sum = 0;
        // 낚시왕 이동
        for(int i = 0; i < C; i++) {
            // 1. 상어 낚시
            if (sharkExisted) { // 잡을 상어가 있다면
                sum += map[minRow][i].size;
                map[minRow][i] = null;
                minRow = Integer.MAX_VALUE;
                sharkExisted = false;
            }

            // 2. 상어 모두 이동
            Queue<Shark> queue = new ArrayDeque<>();
            for(int row = 0; row < R; row++) { // 맵 재구성을 위해 모든 상어 큐에 삽입
                for(int col = 0; col < C; col++) {
                    if(map[row][col] != null) { // 상어가 존재한다면
                        queue.offer(map[row][col]);
                        map[row][col] = null;
                    }
                }
            }

            while(!queue.isEmpty()) {
                Shark shark = queue.poll();
                shark.move();

                // 상어 넣기
                int row = shark.row;
                int col = shark.col;
                if(map[row][col] != null && map[row][col].size > shark.size) { // 상어 겹치면서 더 작으면 삽입 x
                    continue;
                }

                map[row][col] = shark;
                if(col == i + 1) { // 다음 잡힐 상어 준비
                    minRow = Math.min(minRow, row);
                    sharkExisted = true;
                }
            }

        }

        System.out.print(sum);

    }

    private static class Shark {
        int row;
        int col;
        public int speed;
        public int direction; // 1: 위, 2: 아래, 3: 오른, 4: 왼
        public int size;

        Shark(int row, int col, int speed, int direction, int size) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.direction = direction - 1;
            this.size = size;
        }

        // 1초 뒤의 상어 위치 이동
        public void move() {
            int move = speed;

            // 순환 제거
            if(direction == 0 || direction == 1) { // 상하 이동
                move %= (R - 1) * 2;
            }
            else { // 좌우 이동
                move  %= (C - 1) * 2;
            }

            for(int i = 0; i < move; i++) {
                int newRow = this.row + directions[direction][0];
                int newCol = this.col + directions[direction][1];

                // 범위 벗어나면
                if(newRow < 0 || newCol < 0 || newRow >= R || newCol >= C) {
                    this.row -= directions[direction][0]; // 돌려주기
                    this.col -= directions[direction][1];
                    switch(direction) { // 방향 바꾸기
                        case 0:
                            this.direction = 1;
                            break;
                        case 1:
                            this.direction = 0;
                            break;
                        case 2:
                            this.direction = 3;
                            break;
                        case 3:
                            this.direction = 2;
                            break;
                    }
                    continue;
                }

                this.row = newRow;
                this.col = newCol;
            }


        }
    }
}
