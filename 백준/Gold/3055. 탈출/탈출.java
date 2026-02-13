import java.util.*;
import java.io.*;

public class Main {

    private static char[][] map;
    private static int[] D = new int[2]; // 고슴도치, 비버
    private static boolean[][] visited;
    private static Queue<int[]> waters = new ArrayDeque<>(), moveQ = new ArrayDeque<>();
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            char[] c = br.readLine().toCharArray();
            for(int j = 0; j < C; j++) {
                map[i][j] = c[j];
                if(map[i][j] == 'S') {
                    moveQ.offer(new int[]{i, j});
                    visited[i][j] = true;
                } else if(map[i][j] == 'D') {
                    D[0] = i; D[1] = j;
                } else if(map[i][j] == '*') {
                    waters.offer(new int[]{i, j});
                }
            }
        }

        int time = 0;
        while(true) {
            if(moveQ.isEmpty()) { // 더이상 이동할 수 없음
                System.out.println("KAKTUS");
                return;
            }

            boolean result = bfs(); // true: 비버 소굴 도착 / false : 도착 x
            if(result) break; // 소굴 도착했으면 종료
            time++;
        }

        System.out.println(time);
    }

    private static boolean bfs() {
        updateWater();
        int size = moveQ.size();
        while(size --> 0) {
            int[] current = moveQ.poll();

            if(current[0] == D[0] && current[1] == D[1]) {
                return true;
            }

            for(int[] direction : directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];

                if(nR < 0 || nC < 0 || nR >= R || nC >= C) continue;
                if(visited[nR][nC] || map[nR][nC] == '*' || map[nR][nC] == 'X') continue;

                visited[nR][nC] = true;
                moveQ.offer(new int[]{nR, nC});
            }
        }

        return false;
    }

    private static void updateWater() {
        int size = waters.size();
        while(size --> 0) {
            int[] current = waters.poll();

            for(int[] direction : directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];

                if(nR < 0 || nC < 0 || nR >= R || nC >= C) continue;
                if(map[nR][nC] != '.') continue;

                map[nR][nC] = '*';
                waters.offer(new int[]{nR, nC});
            }
        }
    }
}
