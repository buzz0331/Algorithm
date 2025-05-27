import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, L, R;
    private static final int[][] dxy = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int totalPeople;
    private static boolean[][] visited;
    private static ArrayList<Position> union;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N x N
        L = Integer.parseInt(st.nextToken()); // 인구차이 L명 이상
        R = Integer.parseInt(st.nextToken()); // R명 이하

        int[][] map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        while(true) {
            boolean isMove = false;
            visited = new boolean[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(visited[i][j]) continue;
                    totalPeople = 0;        //같은 연합의 총 인구수
                    union = new ArrayList<>(); //같은 연합의 나라 리스트
                    checkMove(i, j, map, 0);

                    if(union.size() == 1) {
                        continue;
                    }

                    int average = totalPeople / union.size();
                    for(Position p : union) {
                        map[p.row][p.col] = average;
                    }
                    isMove = true;
                }
            }

            if(!isMove) {
                break;
            }

            count++;
        }

        System.out.println(count);
    }

    private static void checkMove(int row, int col, int[][] map, int count) {
        visited[row][col] = true;
        union.add(new Position(row, col));
        totalPeople += map[row][col];
        for (int i = 0; i < dxy.length; i++) {
            int moveRow = row + dxy[i][0];  //움직일 행
            int moveCol = col + dxy[i][1];  //움직일 열

            if(moveRow < 0 || moveCol < 0 || moveRow == N || moveCol == N) continue;
            if(visited[moveRow][moveCol]) continue;
            int subtract = Math.abs(map[row][col] - map[moveRow][moveCol]);
            if (subtract <= R && subtract >= L) {
                count++;
                checkMove(moveRow, moveCol, map, count);
            }
        }
    }

    private static class Position {
        public int row;
        public int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
