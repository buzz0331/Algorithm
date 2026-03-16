import java.util.*;
import java.io.*;

public class Main{
	
	private static int[] nums;
	private static int N, S;
	private static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		//------여기에 솔루션 코드를 작성하세요.------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0, 0, 0);
		System.out.println(answer);
	}
	
	private static void subset(int depth, int sum, int count) {
		if(depth == N) {
			
			if(sum == S && count != 0) {
				answer++;
			}
			
			return;
		}
		
		// 선택 x
		subset(depth + 1, sum, count);
		
		// 선택 o
		subset(depth + 1, sum + nums[depth], count + 1);
	}
}