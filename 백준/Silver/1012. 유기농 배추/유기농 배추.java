import java.util.*;
import java.io.*;

public class Main{
	
	private static int[][] map;
	private static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static int N, M;
	
	public static void main(String[] args) throws IOException {
		//------여기에 솔루션 코드를 작성하세요.------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				
				map[row][col] = -1;
			}
			
			
			int groupNum = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == -1) {
						floodFill(++groupNum, i, j);
					}
				}
			}
			sb.append(groupNum).append("\n");
		}
		
		System.out.print(sb);
	}
	
	private static void floodFill(int groupNum, int row, int col) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {row, col});
		map[row][col] = groupNum;
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for(int[] direction : directions) {
				int nR = current[0] + direction[0];
				int nC = current[1] + direction[1];
				
				if(nR < 0 || nC < 0 || nR >= N || nC >= M) continue;
				if(map[nR][nC] == -1) {
					queue.offer(new int[] {nR, nC});
					map[nR][nC] = groupNum;
				}
			}
			
		}
		
		
	}
	

}