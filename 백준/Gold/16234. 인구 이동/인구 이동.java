import java.util.*;
import java.io.*;

public class Main {
    private static int N, L, R;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;
        while(true){
            visited = new boolean[N][N];
            boolean moved = false;

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(!visited[i][j]){
                        if(bfs(i, j))
                            moved = true;
                    }
                }
            }

            if(!moved) break;
            days++;
        }

        System.out.println(days);
    }

    private static boolean bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        List<int[]> union = new ArrayList<>();

        q.offer(new int[]{x, y});
        union.add(new int[]{x, y});
        visited[x][y] = true;

        int sum = map[x][y];

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];

            for(int d = 0; d < 4; d++){
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(visited[nx][ny]) 
                    continue;

                int diff = Math.abs(map[cx][cy] - map[nx][ny]);
                if(diff >= L && diff <= R){
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                    union.add(new int[]{nx, ny});
                    sum += map[nx][ny];
                }
            }
        }

        if(union.size() == 1) return false;

        int avg = sum / union.size();
        for(int[] pos : union){
            map[pos[0]][pos[1]] = avg;
        }

        return true;
    }
}