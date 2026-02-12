import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static int N, M;
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                map[i][j] = c[j] - '0';
            }
        }

        System.out.print(bfs());
    }

    private static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2]; // 0: 벽 사용 x / 1: 벽 사용 o
        queue.offer(new Node(0, 0, false, 1));
        visited[0][0][0] = true;

        while(!queue.isEmpty()) {
            Node current = queue.poll();
//            System.out.println("row: " + current.row + " col: " + current.col);

            if(current.row == N - 1 && current.col == M - 1) {
                return current.count;
            }

            for(int[] direction : directions) {
                int nR = current.row + direction[0];
                int nC = current.col + direction[1];
                boolean usedWall = current.usedWall;

                if(nR < 0 || nC < 0 || nR >= N || nC >= M) continue;

                if(map[nR][nC] == 1) {
                    if(usedWall) continue; // 이미 벽을 부순 경우
                    usedWall = true;
                }

                if(visited[nR][nC][usedWall ? 1 : 0]) continue;
                visited[nR][nC][usedWall ? 1 : 0] = true;
                queue.offer(new Node(nR, nC, usedWall, current.count + 1));
            }
        }

        return -1;
    }

    private static class Node {
        public int row, col;
        public boolean usedWall;
        public int count;

        public Node(int row, int col, boolean usedWall, int count) {
            this.row = row;
            this.col = col;
            this.usedWall = usedWall;
            this.count = count;
        }
    }
}
