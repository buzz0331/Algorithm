import java.util.*;
import java.io.*;

class Solution
{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws Exception
    {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");
           int N = Integer.parseInt(br.readLine());
           StringTokenizer st = new StringTokenizer(br.readLine());
           int[] trees = new int[N];
           int max = -1;

           for(int i = 0; i < N; i++) {
               trees[i] = Integer.parseInt(st.nextToken());
               max = Math.max(max, trees[i]);
           }

           int count1 = 0, count2 = 0;
           for(int i = 0; i < N; i++) {
               int gap = max - trees[i];
                count2 += gap / 2;
                count1 += gap % 2;
           }

            // count1이 더 많아질 때까지 반복
           while(count2 > count1 + 1) {
               count2--;
               count1 += 2;
           }
            
            if(count1 > count2) { // 홀수가 더 많은 경우
                sb.append(count1 * 2 - 1);
            } else { // 짝수가 더 많은 경우
                sb.append(count2 * 2);
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}