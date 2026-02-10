import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static List<int[]> viruses = new ArrayList<>();
    private static int N, M;
    private static int maxArea = -1, wallCnt = 3;

    private static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) viruses.add(new int[]{i, j}); // 바이러스 위치
                if(map[i][j] == 1) wallCnt++;
            }
        }

        setWall(0, 0, 0);
        System.out.print(maxArea);

    }

    private static void setWall(int row, int col, int depth) {
        if(depth == 3) {
            int virusCount = spreadVirus();
            int safeArea = (N * M) - wallCnt - virusCount;
            maxArea = Math.max(maxArea, safeArea);
//            for (int i = 0; i < N; i++) System.out.println(Arrays.toString(map[i]));
//            System.out.println();
            return;
        }

        for(int i = 0; i < N * M; i++) {
            int r = i / M;
            int c = i % M;
            if(map[r][c] == 0) {
                map[r][c] = 1;
                setWall(r, c, depth + 1);
                map[r][c] = 0;
            }
        }
    }

    private static int spreadVirus() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int count = 0;
        for(int[] virus : viruses) {
            queue.offer(virus);
            visited[virus[0]][virus[1]] = true;
            count++;
        }

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for(int[] direction : directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];

                if(nR < 0 || nC < 0 || nR >= N || nC >= M) continue;
                if(visited[nR][nC] || map[nR][nC] != 0) continue;

                visited[nR][nC] = true;
                queue.offer(new int[]{nR, nC});
                count++;
            }
        }

        return count;
    }
}
