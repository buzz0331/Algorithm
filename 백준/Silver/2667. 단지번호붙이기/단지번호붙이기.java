import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map, indexMap;
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        indexMap = new int[N][N];

        for(int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for(int j = 0; j < N; j++) {
                map[i][j] = c[j] - '0';
            }
        }

        int num = 0;
        List<Integer> group = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1 && indexMap[i][j] == 0) {
                    group.add(bfs(i, j, ++num));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Collections.sort(group);

        sb.append(num).append("\n");
        for(int g : group) {
            sb.append(g).append("\n");
        }

        System.out.print(sb);
    }

    private static int bfs(int row, int col, int num) {
        Queue<int[]> queue = new LinkedList<>();
        int count = 0;

        queue.offer(new int[]{row, col});
        indexMap[row][col] = num;
        count++;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for(int[] direction : directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];

                if(isOut(nR, nC)) continue;

                if (indexMap[nR][nC] == 0 && map[nR][nC] == 1) {
                    queue.offer(new int[]{nR, nC});
                    indexMap[nR][nC] = num;
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean isOut(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= N;
    }
}
