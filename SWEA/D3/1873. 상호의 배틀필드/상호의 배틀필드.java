import java.util.*;
import java.io.*;

public class Solution {
	
	private static char[][] map;
	private static int H, W, N;
	private static int look = 0; // 로빈이 바라보는 방향
	private static char[] commands;
	private static int row, col; // 로빈 현재 위치
	
	private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	private static final Map<Character, Integer> directionMap = new HashMap<>();
	private static final Map<Integer, Character> lookMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		//---------솔루션 코드를 작성하세요.
		
		directionMap.put('U', 0);
		directionMap.put('D', 1);
		directionMap.put('R', 2);
		directionMap.put('L', 3);
		
		directionMap.put('^', 0);
		directionMap.put('v', 1);
		directionMap.put('>', 2);
		directionMap.put('<', 3);
		
		lookMap.put(0, '^');
		lookMap.put(1, 'v');
		lookMap.put(2, '>');
		lookMap.put(3, '<');

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			
			for(int h = 0; h < H; h++) {
				char[] c = br.readLine().toCharArray();
				for(int w = 0; w < W; w++) {
					map[h][w] = c[w];
					
					char ch = map[h][w];
	
					if(ch == '^' || ch == 'v' || ch == '<' || ch == '>') {
						look = directionMap.get(ch);
						row = h;
						col = w;
					}
						
				}
			}
			
			N = Integer.parseInt(br.readLine());
//			commands = new char[N];
			commands = br.readLine().toCharArray();
			gameStart();
			
			sb.append("#").append(t).append(" ");
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	private static void gameStart() {
		for(int i = 0; i < N; i++) {
			char command = commands[i];
//			System.out.println(command);
			
		
			if(command == 'S') { // 화살 발사
				shoot();
			} else {
				move(command);
			}
			
//			for(int h = 0; h < H; h++) {
//				System.out.println(Arrays.toString(map[h]));
//			}
//			System.out.println();
		}
	}
	
	private static void move(char command) {
		look = directionMap.get(command); // 고개는 돌려.
		int[] direction = directions[look];
		map[row][col] = lookMap.get(look);
		
		int nR = row + direction[0];
		int nC = col + direction[1];
		
		if(isOut(nR, nC)) return; // 맵 밖으로 나가는 경우
		if(map[nR][nC] != '.') return; // 연못이라 이동 불가

		map[row][col] = '.';
		row = nR; col = nC; // 이동 확정
		map[row][col] = lookMap.get(look);
	}
	
	private static void shoot() {
		int nR = row;
		int nC = col;
		int[] direction = directions[look];
		
		while(true) {
			nR +=  direction[0];
			nC += direction[1];
			
			
			if(isOut(nR, nC)) return;;
			if(map[nR][nC] == '#') return;
			
			if(map[nR][nC] == '*') {
				map[nR][nC] = '.';
				return;
			}
		}
	}
	
	private static boolean isOut(int nR, int nC) {
		return nR < 0 || nC < 0 || nR >= H || nC >= W;
	}

}
