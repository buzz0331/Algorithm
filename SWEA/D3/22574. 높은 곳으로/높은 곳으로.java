import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            
            sb.append(simulate(N, P)).append("\n");
		}
        
        System.out.println(sb.toString());
	}
    
    private static int simulate(int N, int P) {
        int min = Integer.MAX_VALUE;
        int current = 0;
        
        for(int i = 1; i <= N; i++) {
            current += i;
            min = Math.min(i, min);
            if(current == P) {
                current -= min;
                min++;
            }
        }
        
        return current;
    }
}