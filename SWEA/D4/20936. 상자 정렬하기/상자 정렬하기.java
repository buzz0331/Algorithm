import java.util.*;
import java.io.*;

class Solution
{
    private static StringBuilder sb = new StringBuilder();
    private static int count, emptyIdx;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{		
			int N = Integer.parseInt(br.readLine());
            int[] box = new int[N + 1]; // 인덱스: 상자 번호, 값: 보관함 번호
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
				int boxNum = Integer.parseInt(st.nextToken());
                box[boxNum] = i;
            }
            
			sortBox(box);
		}
        System.out.println(sb.toString());
	}
    
    private static void sortBox(int[] box) {
        count = 0;
        emptyIdx = box.length;
        StringBuilder traceRoute = new StringBuilder();
		while(true) {
			int index = checkSort(box); // 정렬이 안되어 있는 상자 번호
            if(index == -1) break;
            
            int temp = box[index];
            box[index] = emptyIdx;
            emptyIdx = temp;
            
			count++;
            traceRoute.append(emptyIdx + " ");
            dfs(box, traceRoute);
        }
        sb.append(count + "\n");
        sb.append(traceRoute.toString());
        sb.append("\n");
    }
    
    private static void dfs(int[] box, StringBuilder traceRoute) {
        if(emptyIdx == box.length) return;
        count++;
        
		int temp = box[emptyIdx];
        box[emptyIdx] = emptyIdx;
        emptyIdx = temp;
        
        traceRoute.append(emptyIdx + " ");
        dfs(box, traceRoute);
    }
    
    private static int checkSort(int[] box) {
        for(int i = 1; i < box.length; i++) {
            if(box[i] != i) {
                return i;
            }
        }
        
        return -1;
    }
}