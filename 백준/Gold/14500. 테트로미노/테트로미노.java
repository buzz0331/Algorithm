import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int N, M;
    static int max = 0;

    static int[] dx = {0, 0, 1, -1}; // 동, 서, 남, 북
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); 
        M = Integer.parseInt(st.nextToken()); 

        map = new int[N][M];
        visited = new boolean[N][M];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 전체 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, map[i][j], 1);
                visited[i][j] = false;
                checkExtraShape(i, j); // ㅗ, ㅓ, ㅏ, ㅜ 
            }
        }

        System.out.println(max);
    }

    // DFS로 4칸까지 이동하면서 최대값 갱신
    static void dfs(int x, int y, int sum, int depth) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, sum + map[nx][ny], depth + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }

    // DFS로는 만들 수 없는 'ㅗ', 'ㅜ', 'ㅏ', 'ㅓ' 모양 따로 처리
    static void checkExtraShape(int x, int y) {
        // 중심 + 3방향
        int center = map[x][y];

        for (int i = 0; i < 4; i++) {
            int sum = center;
            boolean isValid = true;

            for (int j = 0; j < 3; j++) {
                int dir = (i + j) % 4;
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    sum += map[nx][ny];
                } else {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                max = Math.max(max, sum);
            }
        }
    }
}