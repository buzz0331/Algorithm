import java.util.*;
import java.io.*;

public class Main {

    private static int[][] map;
    private static boolean[][] visited;
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static int N, M;

    private static PriorityQueue<Edge> pq = new PriorityQueue<>();
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
            Edge current = pq.poll();

            if (union(current.from, current.to)) {
                sum += current.cost;
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
        int prevGroupNum, distance;

        // 행 기준 탐색
        for(int r = 0; r < N; r++) {
            prevGroupNum = 0;
            distance = 0;

            for(int c = 0; c < M; c++) {
                if(map[r][c] == 0) {
                    if(prevGroupNum != 0) distance++;
                    continue;
                }

                // 그룹 o -> 이전 그룹 있는 경우
                if(map[r][c] > 0 && prevGroupNum != 0) {
                    if(prevGroupNum == map[r][c]) { // 같은 그룹 내부
                        distance = 0;
                        continue;
                    }

                    if(distance > 1) {
                        int a = prevGroupNum;
                        int b = map[r][c];
                        pq.offer(new Edge(a, b, distance));
                    }

                    prevGroupNum = map[r][c];
                    distance = 0;
                    continue;
                }

                // 그룹 o -> 이전 그룹 없는 경우
                if(map[r][c] > 0 && prevGroupNum == 0) {
                    prevGroupNum = map[r][c];
                    distance = 0;
                }
            }
        }

        // 열 기준 탐색
        for(int c = 0; c < M; c++) {
            prevGroupNum = 0;
            distance = 0;

            for(int r = 0; r < N; r++) {
                if(map[r][c] == 0) {
                    if(prevGroupNum != 0) distance++;
                    continue;
                }

                // 그룹 o -> 이전 그룹 있는 경우
                if(map[r][c] > 0 && prevGroupNum != 0) {
                    if(prevGroupNum == map[r][c]) { // 같은 그룹 내부
                        distance = 0;
                        continue;
                    }

                    if(distance > 1) {
                        int a = prevGroupNum;
                        int b = map[r][c];
                        pq.offer(new Edge(a, b, distance));
                    }

                    prevGroupNum = map[r][c];
                    distance = 0;
                    continue;
                }

                // 그룹 o -> 이전 그룹 없는 경우
                if(map[r][c] > 0 && prevGroupNum == 0) {
                    prevGroupNum = map[r][c];
                    distance = 0;
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

    private static class Edge implements Comparable<Edge> {
        public int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
