import java.io.*;
import java.util.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    private static int[] A = new int[9];
    private static int[] B = new int[9];

    private static int[] bSelected = new int[9];

    private static boolean[] check = new boolean[19];

    private static int aWinCount, bWinCount;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            aWinCount = 0;
            bWinCount = 0;

            for(int i = 0; i < 9; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                check[A[i]] = true;
            }

            int idx = 0;
            for(int i = 1; i <= 18; i++) {
                if(!check[i]) {
                    B[idx++] = i;
                } else {
                    check[i] = false;
                }
            }

            permB(0);
            sb.append("#").append(test_case).append(" ").append(aWinCount).append(" ").append(bWinCount).append("\n");
        }

        System.out.print(sb);
    }

    // B 순열
    private static void permB(int depth) {
        if(depth == 9) { // A와 B 모두 순열 완성
            checkWinner();
            return;
        }

        for(int i = 0; i < 9; i++) {
            int select = B[i];
            if(check[select]) continue;
            bSelected[depth] = select;
            check[select] = true;
            permB(depth + 1);
            bSelected[depth] = 0;
            check[select] = false;
        }
    }

    private static void checkWinner() {
        int aScore = 0, bScore = 0;
        for(int i = 0; i < 9; i++) {
            if(A[i] < bSelected[i]) bScore += A[i] + bSelected[i];
            else aScore += A[i] + bSelected[i];
        }

        if(aScore < bScore) bWinCount++;
        else aWinCount++;
    }
}