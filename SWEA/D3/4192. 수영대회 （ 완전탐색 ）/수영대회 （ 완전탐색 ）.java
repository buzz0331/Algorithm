import java.util.*;
import java.io.*;
 
class Solution
{
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
     
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++)
        {
         
            int N = Integer.parseInt(br.readLine());
            int[][] pool = new int[N][N];
             
            for(int i  = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    pool[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            int[] start = new int[2];
            int[] end = new int[2];
             
            StringTokenizer st = new StringTokenizer(br.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());
             
            st = new StringTokenizer(br.readLine());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());
 
            int result = bfs(pool, start, end);
            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
         
        System.out.print(sb);
    }
     
    private static int bfs(int[][] pool, int[] start, int[] end) {
        int N = pool.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
         
        queue.offer(new int[] {start[0], start[1], 0});
        visited[start[0]][start[1]] = true;
         
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
             
            if(current[0] == end[0] && current[1] == end[1]) return current[2];
             
            for(int[] direction : directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];
                 
                if(nR < 0 || nC < 0 || nR >= N || nC >= N) continue;
                if(visited[nR][nC] || pool[nR][nC] == 1) continue;
                 
                visited[nR][nC] = true;
                queue.offer(new int[] {nR, nC, current[2] + 1});
            }
        }
         
        return -1;
    }
}