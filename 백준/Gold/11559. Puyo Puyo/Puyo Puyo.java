import java.util.*;
import java.io.*;

public class Main {

    private static char[][] map;
    private static boolean flag = true;
    private static boolean[][] visited;
    private static List<int[]> disappear;
    private static int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 터뜨릴 좌표 찾기
        // 2. 찾은 좌표 터뜨리고 해당 열을 아래로 내리기
        // 3. 연쇄 + 1 반복

        map = new char[12][6];

        for(int i = 0; i < 12; i++) {
            char[] c = br.readLine().toCharArray();
            for(int j = 0; j < 6; j++) {
                map[i][j] = c[j];
            }
        }

        while(true) {
            flag = false;
            for(int i = 0; i < 12; i++) {
                for(int j = 0; j < 6; j++) {
                    if(map[i][j] != '.') {
                        char color = map[i][j];
                        visited = new boolean[12][6];
                        disappear = new ArrayList<>();
                        int connect = bfs(i, j, color);
                        if(connect >= 4) {
                            flag = true;
                            // 터뜨리기
                            for(int[] d : disappear) {
                                map[d[0]][d[1]] = '.';
                            }
                        }
                    }
                }
            }

            if(!flag) break;
            count++;
            mapUpdate();
        }

        System.out.print(count);
    }

    private static void mapUpdate() {
        for(int col = 0; col < 6; col++) {
            for(int row = 10; row >= 0; row--) {
                int down = row + 1;
                while (down < 12 && map[down][col] == '.' ) {
                    map[down][col] = map[down - 1][col];
                    map[down - 1][col] = '.';
                    down++;
                }
            }
        }
    }

    private static int bfs(int row, int col, char color) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited[row][col] = true;
        disappear.add(new int[]{row, col});
        queue.offer(new int[]{row, col});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for(int[] direction : directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];

                if(nR < 0 || nC < 0 || nR >= 12 || nC >= 6) continue;
                if(visited[nR][nC]) continue;

                if(map[nR][nC] == color) {
                    visited[nR][nC] = true;
                    disappear.add(new int[]{nR, nC});
                    queue.offer(new int[]{nR, nC});
                }
            }
        }

        return disappear.size();
    }


}
