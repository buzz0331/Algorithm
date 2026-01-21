import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;

    private static final char BLANK = '.';
    private static final char DOC = '$';
    private static final char WALL = '*';

    private static final int[][] directions = {
            {0, 1}, {1, 0}, {-1, 0}, {0, -1}
    };

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            char[][] map = new char[N + 2][M + 2];
            boolean[] hasKey = new boolean[26]; //가지고 있는 키 유무

            for(int i = 0; i < N + 2; i++) Arrays.fill(map[i], BLANK);

            for(int i = 1; i <= N; i++) {
                String line = br.readLine();
                for(int j = 1; j <= M; j++) {
                    map[i][j] = line.charAt(j - 1);
                }
            }

            char[] keys = br.readLine().toCharArray();
            if(keys[0] != '0') {
                for(char k : keys) {
                    hasKey[k - 'a'] = true;
                }
            }

            sb.append(bfs(0, 0, map, hasKey)).append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs(int row, int col, char[][] map, boolean[] hasKey) {
        int H = map.length;
        int W = map[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 2][M + 2];
        List<int[]>[] waitingDoor = new ArrayList[26];
        for(int i = 0; i < 26; i++) {
            waitingDoor[i] = new ArrayList<>();
        }

        queue.offer(new int[] {row, col});
        visited[row][col] = true;
        int count = 0;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for(int[] direction : directions) {
                int nRow = current[0] + direction[0];
                int nCol = current[1] + direction[1];

                if(nRow < 0 || nCol < 0 || nRow >= H || nCol >= W) continue;
                if(visited[nRow][nCol] || map[nRow][nCol] == WALL) continue;

                char block = map[nRow][nCol];

                if(map[nRow][nCol] == BLANK) { // 이동 가능
                    queue.offer(new int[] {nRow, nCol});
                    visited[nRow][nCol] = true;
                } else if(Character.isUpperCase(block)) { // 문
                    if(hasKey[block - 'A']) { //문에 맞는 열쇠가 존재
                        map[nRow][nCol] = BLANK;
                        visited[nRow][nCol] = true;
                        queue.offer(new int[]{nRow, nCol});
                    } else {
                        waitingDoor[block - 'A'].add(new int[]{nRow, nCol});
                    }
                } else if(Character.isLowerCase(block)) { // 열쇠
                    int k = block - 'a';
                    hasKey[k] = true;
                    map[nRow][nCol] = BLANK;
                    visited[nRow][nCol] = true;
                    queue.offer(new int[]{nRow, nCol});

                    for(int[] door : waitingDoor[k]) {
                        if(visited[door[0]][door[1]]) continue;
                        visited[door[0]][door[1]] = true;
                        map[door[0]][door[1]] = BLANK;
                        queue.offer(new int[]{door[0], door[1]});
                    }
                    waitingDoor[k].clear();

                } else if(block == DOC) { // 문서
                    map[nRow][nCol] = BLANK;
                    count++;
                    visited[nRow][nCol] = true;
                    queue.offer(new int[]{nRow, nCol});
                }
            }
        }

        return count;
    }
}
