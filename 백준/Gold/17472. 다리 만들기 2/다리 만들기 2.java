import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static boolean[][] visited;
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static int N, M;

    private static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
    private static int[] parents;

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
            }
        }

        int groupNum = 0;
        visited = new boolean[N][M];
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(map[r][c] ==  0 || visited[r][c]) continue;
                makeGroup(r, c, ++groupNum);
            }
        }

        getBridgeLength();

        parents = new int[groupNum + 1];
        for(int i = 1; i <= groupNum; i++) {
            parents[i] = i;
        }

        System.out.print(kruskal(groupNum));
    }

    private static int kruskal(int groupCount) {
        int count = 0;
        int sum = 0;
        while(!pq.isEmpty()) {
            int[] current = pq.poll();

            if (union(current[0], current[1])) {
                sum += current[2];
                count++;
            }

            if(count == groupCount - 1) return sum;
        }

        return -1;
    }

    private static int find(int x) {
        if(parents[x] == x) return x;

        return parents[x] = find(parents[x]);
    }

    private static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return false;

        if(rootX < rootY) {
            parents[rootY] = rootX;
        } else {
            parents[rootX] = rootY;
        }

        return true;
    }

    // 그룹 간 놓을 수 있는 다리 길이 계산
    private static void getBridgeLength() {
        int prevGroupNum, prevGroupLocation; // 이전에 마주친 그룹 정보
        // 행 기준 탐색
        for(int r = 0; r < N; r++) {
            prevGroupNum = 0;
            prevGroupLocation = 0;
            for(int c = 0; c < M; c++) {

                // 그룹 o -> 이전 그룹 있는 경우
                if(map[r][c] > 0 && prevGroupNum != 0) {
                    if(prevGroupNum == map[r][c]) prevGroupLocation = c; // 같은 그룹 내부
                    int distance = Math.abs(c - prevGroupLocation) - 1;
                    if(distance > 1) pq.offer(new int[]{prevGroupNum, map[r][c], distance});
                    prevGroupNum = 0;
                    prevGroupLocation = 0;
                }

                // 그룹 o -> 이전 그룹 없는 경우
                if(map[r][c] > 0 && prevGroupNum == 0) {
                    prevGroupLocation = c;
                    prevGroupNum = map[r][c];
                }
            }
        }

        // 행 기준 탐색
        for(int c = 0; c < M; c++) {
            prevGroupNum = 0;
            prevGroupLocation = 0;
            for(int r = 0; r < N; r++) {

                // 그룹 o -> 이전 그룹 있는 경우
                if(map[r][c] > 0 && prevGroupNum != 0) {
                    if(prevGroupNum == map[r][c]) prevGroupLocation = r; // 같은 그룹 내부
                    int distance = Math.abs(r - prevGroupLocation) - 1;
                    if(distance > 1) pq.offer(new int[]{prevGroupNum, map[r][c], distance});
                    prevGroupNum = 0;
                    prevGroupLocation = 0;
                }

                // 그룹 o -> 이전 그룹 없는 경우
                if(map[r][c] > 0 && prevGroupNum == 0) {
                    prevGroupLocation = r;
                    prevGroupNum = map[r][c];
                }
            }
        }
    }

    private static void makeGroup(int row, int col, int groupNum) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            map[current[0]][current[1]] = groupNum;
            for(int[] direction : directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];

                if(nR < 0 || nC < 0 || nR >= N || nC >= M) continue;
                if(visited[nR][nC] || map[nR][nC] == 0) continue;

                visited[nR][nC] = true;
                queue.offer(new int[]{nR, nC});
            }
        }
    }
}
