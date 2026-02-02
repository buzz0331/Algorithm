import java.util.*;
import java.io.*;
 
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{   
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
     
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        /*
           여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
        */
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int num = Integer.parseInt(br.readLine());
            int[][] maze = new int[16][16];
            int[] start = new int[2];
             
            for(int i = 0; i < 16; i++) {
                char[] c = br.readLine().toCharArray();
                for(int j = 0; j < 16; j++) {
                    maze[i][j] = c[j] - '0';
                    if(maze[i][j] == 2) {
                        start[0] = i;
                        start[1] = j;
                    }
                }
            }
             
            int result = bfs(maze, start);
            sb.append("#").append(num).append(" ").append(result).append("\n");
        }
         
        System.out.print(sb);
    }
     
    private static int bfs(int[][] maze, int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[16][16];
         
        queue.offer(new int[] {start[0], start[1]});
        visited[start[0]][start[1]] = true;
         
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
             
            if(maze[current[0]][current[1]] == 3) return 1;
             
            for(int[] direction : directions) {
                int nR = current[0] + direction[0];
                int nC = current[1] + direction[1];
                 
                if(nR < 0 || nC < 0 || nR >= 16 || nC >= 16) continue;
                if(visited[nR][nC] || maze[nR][nC] == 1) continue;
                 
                visited[nR][nC] = true;
                queue.offer(new int[] {nR, nC});
            }
        }
        return 0;
    }
}