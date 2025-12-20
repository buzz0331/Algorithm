import java.util.*;
import java.io.*;

public class Main{

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, groupNum = 0;
    private static int[][] visited; // 0 : 한번도 방문 안함 / 1 : 현재 DFS 경로에서 방문 / 2 : 완전히 방문 끝
    private static char[][] directionMap;

    private static final Map<Character, int[]> direction = Map.of(
            'D', new int[]{1, 0},
            'U', new int[]{-1, 0},
            'L', new int[]{0, -1},
            'R', new int[]{0, 1}
            );

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new int[N][M];
        directionMap = new char[N][M];

        for(int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                directionMap[i][j] = c[j];
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visited[i][j] == 0) {
                    dfs(i, j);
                }
            }
        }

        System.out.print(groupNum);
    }

    private static void dfs(int row, int col) {
        visited[row][col] = 1; //이번 dfs 경로에서 방문 표시
        int[] dRowCol = direction.get(directionMap[row][col]);
        int nRow = row + dRowCol[0];
        int nCol = col + dRowCol[1];

        int next = visited[nRow][nCol];

        if(next == 0) { // 한번도 가지 못한 칸
            dfs(nRow, nCol);
        } else if(next == 1) { // 사이클 발생 -> groupNum 증가
            groupNum++;
        }

        visited[row][col] = 2;  // 방문완료 표시

    }
}
