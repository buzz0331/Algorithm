import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static int[] babyShark;
    private static int N, sharkSize = 2;

    private static final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        babyShark = new int[2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    babyShark[0] = i; babyShark[1] = j;
                }
            }
        }

        int time = 0;
        int eatCount = 0;
        while(true) {
            // 1. 먹을 수 있는 상어 뽑기
            Fish shark = findShark();
            if(shark == null) break;

            // 2. 물고기 먹기
            eatCount++;
            if(eatCount == sharkSize) { // 몸집 키우기
                sharkSize++;
                eatCount = 0;
            }
            time += shark.distance;
            map[babyShark[0]][babyShark[1]] = 0;
            babyShark[0] = shark.row;
            babyShark[1] = shark.col;
            map[babyShark[0]][babyShark[1]] = 9;
        }

        System.out.print(time);
    }

    private static Fish findShark() {
        Queue<Fish> queue = new PriorityQueue<>();
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(new Fish(babyShark[0], babyShark[1], 0));
        visited[babyShark[0]][babyShark[1]] = true;

        while(!queue.isEmpty()) {
            Fish current = queue.poll();
//            for(int i = 0; i < N; i++) {
//                System.out.println(Arrays.toString(visited[i]));
//            }
//            System.out.println();

            for(int[] direction : directions) {
                int nR = current.row + direction[0];
                int nC = current.col + direction[1];

                if(nR < 0 || nC < 0 || nR >= N || nC >= N) continue;
                if(visited[nR][nC]) continue;
                visited[nR][nC] = true;

                if(map[nR][nC] == 0) {
                    queue.offer(new Fish(nR, nC, current.distance + 1));
                    continue;
                }
                if(sharkSize < map[nR][nC]) continue; // 더큰 상어는 못지나감
                if(sharkSize == map[nR][nC]) { // 크기가 같은 상어는 지나갈 수는 있음
                    queue.offer(new Fish(nR, nC, current.distance + 1));
                    continue;
                }
                if(sharkSize > map[nR][nC]){
                    pq.offer(new Fish(nR, nC, current.distance + 1));
                }
            }
        }

        if(pq.isEmpty()) return null;
        return pq.poll();
    }

    private static class Fish implements Comparable<Fish> {
        public int row, col;
        public int distance;

        public Fish(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }

        @Override
        public int compareTo(Fish o) {
            if(this.distance != o.distance) {
                return this.distance - o.distance;
            }

            if(this.row != o.row) {
                return this.row - o.row;
            }

            return this.col - o.col;
        }
    }
}
