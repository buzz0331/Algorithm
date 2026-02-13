import java.util.*;
import java.io.*;

public class Main {

    private static int R, C, answer = -1;
    private static char[][] map;

    private static final int[][] directions = {{0, 1}, {-1, 0}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            char[] c = br.readLine().toCharArray();
            for(int j = 0; j < C; j++) {
                map[i][j] = c[j];
            }
        }

        int visited = 1 << (map[0][0] - 'A');
        dfs(0, 0, 1, visited);
        System.out.print(answer);
    }

    private static void dfs(int row, int col, int count, int visited) {
        boolean canMove = false;
        for(int[] direction : directions) {
            int nR = row + direction[0];
            int nC = col + direction[1];

            if(nR < 0 || nC < 0 || nR >= R || nC >= C) continue;
            int cIdx = map[nR][nC] - 'A';
            if((visited & (1 << cIdx)) != 0) continue; // 이미 얻은 알파벳이면 패스

            canMove = true;
            visited |= (1 << cIdx);
            dfs(nR, nC, count + 1, visited);
            visited ^= (1 << cIdx);
        }

        // 더이상 못 움직이면 지금까지 움직인 횟수로 정답 갱신
        if(!canMove) {
            answer = Math.max(answer, count);
        }
    }
}
