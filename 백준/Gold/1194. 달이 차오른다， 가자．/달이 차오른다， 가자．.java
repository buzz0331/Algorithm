import java.util.*;
import java.io.*;

public class Main {

    private static char[][] map;
    private static int[] start;
    private static int N, M;

    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        start = new int[2];
        for(int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                map[i][j] = c[j];
                if(map[i][j] == '0') {
                    start[0] = i; start[1] = j;
                    map[i][j] = '.';
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][1 << 6]; // 2^6
        queue.offer(new Node(start[0], start[1], 0, 0));
        visited[start[0]][start[1]][0] = true;

        while(!queue.isEmpty()) {
            Node current = queue.poll();
//            System.out.println("key: " + current.key);
//            System.out.println("row: " + current.row + " col: " + current.col);

            for(int[] direction : directions) {
                int nR = current.row + direction[0];
                int nC = current.col + direction[1];
                int nKey = current.key;

                if(nR < 0 || nC < 0 || nR >= N || nC >= M) continue;
                if(map[nR][nC] == '#') continue;

                char cell = map[nR][nC];

                // 목적지 도착
                if(cell == '1') {
                    return current.count + 1;
                }

                // 열쇠
                if(cell >= 'a' && cell <= 'z') {
                    int keyIdx = cell - 'a';
                    nKey = current.key | (1 << keyIdx);
                }

                // 문
                if (cell >= 'A' && cell <= 'Z') {
                    int keyIdx = cell - 'A';
                    int currentKey = current.key;
                    boolean hasKey = (currentKey & (1 << keyIdx)) != 0;
                    if(!hasKey) continue; // 열쇠 가지고 있지 않으면 못 지나감
                }

                if(visited[nR][nC][nKey]) continue;
                visited[nR][nC][nKey] = true;
                queue.offer(new Node(nR, nC, nKey, current.count + 1));
            }
        }
        return -1;
    }

    private static class Node {
        public int row, col;
        public int key; // key를 비트로 표현
        public int count; // 동작 횟수

        Node(int row, int col, int key, int count) {
            this.row = row;
            this.col = col;
            this.key = key;
            this.count = count;
        }
    }
}
