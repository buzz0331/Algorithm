import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map, bridge;
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

        bridge = new int[groupNum + 1][groupNum + 1];
        for(int i = 1; i <= groupNum; i++) {
            Arrays.fill(bridge[i], Integer.MAX_VALUE);
        }

        getBridgeLength(groupNum);

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
    private static void getBridgeLength(int groupCount) {
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(map[r][c] != 0) {
                    searchForDiffGroup(r, c);
                }
            }
        }

        for(int i = 1; i <= groupCount; i++) {
            for(int j = i + 1; j <= groupCount; j++) {
                if(bridge[i][j] != Integer.MAX_VALUE) {
                    pq.offer(new int[]{i, j, bridge[i][j]});
                }
            }
        }
    }

    // 4방향으로 타그룹 탐색
    private static void searchForDiffGroup(int r, int c) {
        int groupNum = map[r][c];

        for(int[] direction : directions) {
            int nR = r, nC = c;
            boolean reach = false; // 다른 그룹에 도달했는지 여부
            while(true) {
                nR += direction[0];
                nC += direction[1];

                if(nR < 0 || nC < 0 || nR >= N || nC >= M) break;
                if(map[nR][nC] == groupNum) break;
                if(map[nR][nC] != 0) {
                    reach = true;
                    break;
                }
            }

            if(reach) { // 다른 그룹에 도달하면 거리 계산
                int distance = Math.abs(r - nR) + Math.abs(c - nC) - 1;
                if(distance > 1) {
                    int diffGroup = map[nR][nC];
                    bridge[groupNum][diffGroup] = Math.min(bridge[groupNum][diffGroup], distance);
                    bridge[diffGroup][groupNum] = Math.min(bridge[diffGroup][groupNum], distance);
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
