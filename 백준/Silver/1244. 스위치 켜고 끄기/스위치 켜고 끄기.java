import java.util.*;
import java.io.*;

public class Main {
	
	private static int[] LEDs;
	private static int N, M;

	public static void main(String[] args) throws IOException {
		//---------솔루션 코드를 작성하세요.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // LED 개수
		LEDs = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			LEDs[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine()); // 사람 수
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 성별
			int y = Integer.parseInt(st.nextToken()); // 받은 수
			changeLeds(x, y);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(LEDs[i]).append(" ");

			if((i + 1) % 20 == 0) sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void changeLeds(int gender, int number) {
		if(gender == 1) { // 남자
			
			for(int i = 0; i < N; i++) {
				if((i + 1) % number == 0) { // 받은 수의 배수일 때
					LEDs[i] = (LEDs[i] == 1)? 0 : 1;
				}
			}
			
		} else {
			
			female(number - 1);
			
		}
	}
	
	private static void female(int number) {
		int start = number - 1, end = number + 1;
		LEDs[number] = (LEDs[number] == 1)? 0 : 1;
		
		while(start >= 0 && end < N) {
			if(LEDs[start] != LEDs[end]) {
				break;
			}
			
			// 대칭인 경우
			LEDs[start] = (LEDs[start] == 1)? 0 : 1;
			LEDs[end] = (LEDs[end] == 1)? 0 : 1;
			
			start--; end++;
		}
		
	}

}
