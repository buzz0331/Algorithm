import java.util.*;

class Solution {
    
    private char[][] array;
    private int n, m;
    
    private final int[][] dRowCol = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    
    public int solution(String[] storage, String[] requests) {
        
        n = storage.length;
        m = storage[0].length();
        
        int answer = n * m;
        
        array = new char[n][m];
        
        for(int i = 0; i < n; i++) {
            array[i] = storage[i].toCharArray();
        }
        
        for(int i = 0; i < requests.length; i++) {
            if(requests[i].length() == 1) {
                //지게차
                answer -= forkCar(requests[i].toCharArray()[0]);
            }
            else {
                //크레인
                answer -= crain(requests[i].toCharArray()[0]);
            }
        }
        
        return answer;
    }
    
    private int forkCar(char c) {
        int sum = 0;
        List<Position> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(array[i][j] == c) {
                    if(isEdge(i, j)) {
                        sum++;
                        list.add(new Position(i, j));
                    }
                }
            }
        }
        
        for(Position p : list) {
            array[p.row][p.col] = 0;
        }
        System.out.println(sum);
        return sum;
    }
    
    private boolean isEdge(int row, int col) {
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        queue.offer(new Position(row, col));
        visited[row][col] = true;
        
        while(!queue.isEmpty()) {
            Position currentPos = queue.poll();
            int currentRow = currentPos.row;
            int currentCol = currentPos.col;
            
            for(int i = 0; i < dRowCol.length; i++) {
                int dRow = currentRow + dRowCol[i][0];
                int dCol = currentCol + dRowCol[i][1];

                if(dRow < 0 || dCol < 0 || dRow >= n || dCol >= m) {
                    return true;
                }
                
                if(visited[dRow][dCol]) continue;

                
                if(array[dRow][dCol] == 0) {
                    queue.offer(new Position(dRow, dCol));
                    visited[dRow][dCol] = true;
                }
            }   
        }
        
        return false;
    }
    
    private int crain(char c) {
        int sum = 0;
        List<Position> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(array[i][j] == c) {
                    sum++;
                    list.add(new Position(i, j));
                }
            }
        }
        
        for(Position p : list) {
            array[p.row][p.col] = 0;
        }
        System.out.println(sum);
        return sum;
    }
    
    private class Position {
        public int row;
        public int col;
        
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}