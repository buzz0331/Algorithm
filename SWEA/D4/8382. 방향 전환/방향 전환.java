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
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int R = Math.abs(x1 - x2); // 높이 차
            int C = Math.abs(y1 - y2); // 너비 차

            int gap = Math.abs(R - C);
            if(gap % 2 == 0) { // 짝수
                sb.append(Math.max(R, C) * 2);
            } else { // 홀수
                sb.append(Math.max(R, C) * 2 - 1);
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}