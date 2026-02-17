import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static int N, M, minTime = Integer.MAX_VALUE, wallCnt = 0, totalCnt;
    private static List<int[]> virusList = new ArrayList<>();
    private static List<int[]> activeVirusList;
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        activeVirusList = new ArrayList<>(M);
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virusList.add(new int[]{i, j});
                if(map[i][j] == 1) wallCnt++;
            }
        }

        totalCnt = N * N - wallCnt; // BFS가 탐색해야될 총 노드
        if(totalCnt == virusList.size()) { // 빈칸이 존재하지 않는 경우
            System.out.println(0);
            return;
        }
        selectVirus(0, 0);
        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }

    private static void selectVirus(int depth, int start) {
        if(depth == M) {
            simulation();
            return;
        }

        for(int i = start; i < virusList.size(); i++) {
            activeVirusList.add(virusList.get(i));
            selectVirus(depth + 1, i + 1);
            activeVirusList.remove(depth);
        }
    }

    private static void simulation() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        int max = -1, count = 0;

        for(int[] activeVirus : activeVirusList) {
            int row = activeVirus[0], col = activeVirus[1];
            queue.add(new int[]{row, col, 0});
            visited[row][col] = true;
        }

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            if(map[current[0]][current[1]] == 0) max = Math.max(max, current[2]);
            count++;

            for(int[] direction : directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];

                if(nR < 0 || nC < 0 || nR >= N || nC >= N) continue;
                if(visited[nR][nC] || map[nR][nC] == 1) continue;

                queue.offer(new int[]{nR, nC, current[2] + 1});
                visited[nR][nC] = true;
            }
        }
        
        if(count == totalCnt) { // 바이러스 모두 퍼졌을 경우만 최소 시간 업데이트
            minTime = Math.min(max, minTime);
        }
    }
}

