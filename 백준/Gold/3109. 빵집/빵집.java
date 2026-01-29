import java.util.*;
import java.io.*;

public class Main {
	
	private static final int[][] directions = {
			{-1, 1}, {0, 1}, {1, 1}
	};
	private static int[][] map;
	private static int R, C;
	private static int count;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			String[] s = br.readLine().split("");
			for(int j = 0; j < C; j++) {
				map[i][j] = (s[j].equals("x")) ? 1 : 0;
			}
		}

		// 파이프라인 시작 위치
		for(int i = 0; i < R; i++) {
			if(dfs(i,0)) count++;
			
		}
		
		System.out.print(count);
		
	}
	
	private static boolean dfs(int row, int col) {
		if(col == C - 1) {
			map[row][col] = 2;
//			print();
			return true;
		}
		
//		print();
		
		for(int[] direction : directions) {
			int nR = row + direction[0];
			int nC = col + direction[1];
			
			if(isOut(nR, nC)) continue;
			
			if(map[nR][nC] == 0) {
				if(nC == C - 1) {
					return true;
				}
				
				map[nR][nC] = 2;
				
				if(dfs(nR, nC)) {
					return true;
				}
			}
		}
		
//		visited[row][col] = 0;
		return false;
	}
	
	private static boolean isOut(int row, int col) {
		return row < 0 || col < 0 || row >= R || col >= C;
	}
	
	private static void print() {
		for(int i = 0; i < R; i++) {
			System.out.println(Arrays.toString(map[i]));
			
		}System.out.println();
	}

}
