import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map;
    private static int N, M;

    private static int[][] dRowCol = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            String[] s = br.readLine().split("");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }

        System.out.println(bfs());
//        System.out.println(dijkstra()[N - 1][M - 1]);

    }

    private static int bfs() {
        boolean[][] visited = new boolean[N][M];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(0, 0, 0));
        visited[0][0] = true;

        int result = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if(current.row == N - 1 && current.col == M - 1){
                result = current.count;
                break;
            }

            for(int[] rowCol : dRowCol) {
                int neighborRow = current.row + rowCol[0];
                int neighborCol = current.col + rowCol[1];

                if(neighborRow < 0 || neighborCol < 0 || neighborRow >= N || neighborCol >= M) continue;
                if (visited[neighborRow][neighborCol]) continue;

                visited[neighborRow][neighborCol] = true;

                int newCount = current.count + map[neighborRow][neighborCol];
                pq.offer(new Node(neighborRow, neighborCol, newCount));
            }

        }

        return result;  //map[N - 1][M - 1]의 count
    }

    private static int[][] dijkstra() {
        int[][] dist = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++ ){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[0][0] = 0;
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if(current.row == N - 1 && current.col == M - 1) return dist;

            if(visited[current.row][current.col]) continue;
            visited[current.row][current.col] = true;

            for(int[] rowCol : dRowCol) {
                int neighborRow = current.row + rowCol[0];
                int neighborCol = current.col + rowCol[1];

                if(neighborRow < 0 || neighborCol < 0 || neighborRow >= N || neighborCol >= M) continue;

                int newDist = dist[current.row][current.col] + map[neighborRow][neighborCol];
                if (dist[neighborRow][neighborCol] > newDist) {
                    dist[neighborRow][neighborCol] = newDist;
                    pq.offer(new Node(neighborRow, neighborCol, newDist));
                }
            }

        }

        return dist;
    }

    private static class Node implements Comparable<Node> {
        public int row;
        public int col;
        public int count;

        public Node(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.count, o.count);
        }
    }
}
