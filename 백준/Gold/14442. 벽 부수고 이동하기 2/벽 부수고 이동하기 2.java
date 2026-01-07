import java.util.*;
import java.io.*;

public class Main
{
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map;
    private static int N, M, K, answer = -1;
    
    private static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
		    String[] s = br.readLine().split("");
		    for(int j = 1; j <= M; j++) {
		        map[i][j] = Integer.parseInt(s[j - 1]);
		    }
		}
		
		bfs();
		System.out.print(answer);
	}
	
	private static void bfs() {
	    Queue<Point> pq = new ArrayDeque<>();
	    boolean[][][] visited = new boolean[N + 1][M + 1][K + 1];
	    
	    pq.offer(new Point(1, 1, K, 1));
	    visited[1][1][K] = true;
	    
	    while(!pq.isEmpty()) {
	        Point current = pq.poll();
	        int cR = current.row;
	        int cC = current.col;
	        int rest = current.rest; // 남은 벽 깰 수 있는 횟수
	        int count = current.count; 
	        
	        if(cR == N && cC == M) {
	            answer = count;
	            return;
	        }
	        
	        for(int[] direction : directions) {
	            int nR = cR + direction[0];
	            int nC = cC + direction[1];
	            
	            if(nR < 1 || nC < 1 || nR > N || nC > M) continue;
	            if(visited[nR][nC][rest]) continue;
	            
	            if(map[nR][nC] == 1) { // 벽이 있는 곳
	                if(rest == 0) continue; // 남은 벽 깰수 있는 횟수 없음
	                
	                pq.offer(new Point (nR, nC, rest - 1, count + 1));
	                visited[nR][nC][rest] = true;
	            } else {    // 벽 없는 곳
	                pq.offer(new Point (nR, nC, rest, count + 1));
	                visited[nR][nC][rest] = true;
	            }
	        }
	        
	    }
	    
	}
	
	private static class Point {
	    public int row;
	    public int col;
	    public int rest;
	    public int count;
	    
	    Point(int row, int col, int rest, int count) {
	        this.row = row;
	        this.col = col;
	        this.rest = rest;
	        this.count = count;
	    }
	    
	}
}
