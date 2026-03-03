import java.util.*;
import java.io.*;

public class Solution {
	
	private static int[][] map;
	private static int N, K;

	public static void main(String[] args) throws IOException {
		//---------솔루션 코드를 작성하세요.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine());

		for(int t = 1; t <= test_case; t++) {
			sb.append("#").append(t).append(" ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			// 행 탐색
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(map[r][c] == 1) {
						int length = 0;				
						while(c < N && map[r][c] == 1) {
							c++; length++;
						}
					
						if(length == K) count++;
					}
				}
			}
			
			// 열 탐색
			for(int c = 0; c < N; c++) {
				for(int r = 0; r < N; r++) {
					if(map[r][c] == 1) {
						int length = 0;			
						while(r < N && map[r][c] == 1) {
							r++; length++;
						}
						
						if(length == K) count++;
					}
				}
			}
			
			sb.append(count).append("\n");
		}

		System.out.println(sb);
		
	}


}
