import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] dRowCol = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static int N, M;
    private static int[][] map;
    private static boolean[][][] visited; // [row][col][canUseWall?1:0]

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];

        for(int i = 0; i < N; i++) {
            String[] s = br.readLine().split("");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node());
        visited[0][0][1] = true; // 벽이 안 깨진채로 시작
        int answer = -1;

        while(!q.isEmpty()) {
            Node node = q.poll();
            int currentRow = node.row;
            int currentCol = node.col;

            if(currentRow == N - 1 && currentCol == M - 1) {
                answer = node.cost;
                break;
            }

            for (int[] rowCol : dRowCol) {
                int nextRow = currentRow + rowCol[0];
                int nextCol = currentCol + rowCol[1];

                if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;

                // 다음 칸이 벽인데 이미 기회를 썼다면 진행 불가
                if (map[nextRow][nextCol] == 1 && !node.canUseWall) continue;

                boolean nextCanUseWall = node.canUseWall;
                // 벽을 만나고 아직 기회가 남아있다면 여기서 사용
                if (map[nextRow][nextCol] == 1 && node.canUseWall) {
                    nextCanUseWall = false;
                }

                int state = nextCanUseWall ? 1 : 0;
                if (visited[nextRow][nextCol][state]) continue;
                visited[nextRow][nextCol][state] = true;

                q.offer(new Node(nextRow, nextCol, node.cost + 1, nextCanUseWall));
            }
        }

        return answer;
    }

    private static class Node {
        public int row;
        public int col;
        public int cost;
        public boolean canUseWall;

        public Node() {
            this.row = 0;
            this.col = 0;
            cost = 1;
            canUseWall = true;
        }

        public Node(int row, int col, int cost, boolean canUseWall) {
            this.row = row;
            this.col = col;
            this.cost = cost;
            this.canUseWall = canUseWall;
        }
    }
}
