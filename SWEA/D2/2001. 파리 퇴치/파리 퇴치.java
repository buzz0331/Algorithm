import java.util.*;
import java.io.*;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++)
        {
         
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
             
            int[][] map = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            // 구간 합 구하기
            int[][] sum = new int[N][N];
            sum[0][0] = map[0][0];
            for(int i = 1; i < N; i++) {
                sum[0][i] = sum[0][i - 1] + map[0][i];
                sum[i][0] = sum[i - 1][0] + map[i][0];
            }
             
            for(int i = 1; i < N; i++) {
                for(int j = 1; j < N; j++) {
                    sum[i][j] = map[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                }
            }
             
            int max = -1;
            for(int i = M - 1; i < N; i++) {
                for(int j = M - 1; j < N; j++) {
                    int up = i - M;
                    int left = j - M;
                     
                    int result = sum[i][j];
                    if(up >= 0 && left >= 0) {
                        result -= sum[up][j] + sum[i][left];
                        result += sum[up][left];
                    }
                    else if(up >= 0) result -= sum[up][j];
                    else if(left >= 0) result -= sum[i][left];
                     
                    max = Math.max(max, result);
                }
            }
             
            sb.append("#").append(test_case).append(" ").append(max).append("\n");
        }
         
        System.out.print(sb);
    }
}