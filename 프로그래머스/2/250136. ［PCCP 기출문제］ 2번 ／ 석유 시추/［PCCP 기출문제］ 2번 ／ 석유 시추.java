import java.util.*;

class Solution {
    
    private int n, m;
    private final int[][] dRowCol = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    
    public int solution(int[][] land) {
        int answer = 0;
        n = land.length; // 세로
        m = land[0].length; // 가로
        
        boolean[][] visited = new boolean[n][m];
        int[] petroleum = new int[m];
        
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j] && land[i][j] == 1) {
                    Set<Integer> rowSet = new HashSet<>();
                    int sum = bfs(visited, land, i, j, rowSet);
                    
                    for(Integer row : rowSet) {
                        petroleum[row] += sum;
                    }
                }
            }
        }
        answer = Arrays.stream(petroleum).max().getAsInt();
        return answer;
    }
    
    private int bfs(boolean[][] visited, int[][] land, int startRow, int startCol, Set<Integer> rowSet) {
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        rowSet.add(startCol);
            
        int count = 0;
        count++;
        
        while(!queue.isEmpty()) {
            int[] currPos = queue.poll();
            int currRow = currPos[0];
            int currCol = currPos[1];
            
            for(int i = 0; i < dRowCol.length; i++) {
                int newRow = currRow + dRowCol[i][0];
                int newCol = currCol + dRowCol[i][1];
                
                if(newRow < 0 || newRow >= n) continue;
                if(newCol < 0 || newCol >= m) continue;
                if(visited[newRow][newCol]) continue;
                if(land[newRow][newCol] != 1) continue;
                
                count++;
                queue.offer(new int[]{newRow, newCol});
                visited[newRow][newCol] = true;
                rowSet.add(newCol);
            }
        }
        
        return count;
    }
}