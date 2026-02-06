import java.io.*;
import java.util.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{

    private static int[][] map, max;
    private static int[] selected = new int[2];
    private static int N, M, C;
    private static int answer = -1;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            max = new int[N][N - M + 1]; // 벌통이 선택되었을 때 최댓값 저장
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = -1;
            calMax();

//            for(int i = 0; i < N; i++) {
//                System.out.println(Arrays.toString(max[i]));
//            }

            selected = new int[2];
            select(0, 0, 0);
            sb.append("#").append(test_case)
                    .append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    private static void calMax() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < max[i].length; j++) {
                int[] honey = new int[M];
                for(int h = 0; h < M; h++) {
                    honey[h] = map[i][j + h];
                }

                max[i][j] = subSet(honey, -1, 0, 0, 0);
            }
        }
    }

    private static int subSet(int[] honey, int max, int result, int depth, int sum) {
        if(sum > C) return -1; // 가지치기

        if(depth == M) {
            max = Math.max(max, result);
            return max;
        }

        int select = max = subSet(honey, max, result + (honey[depth] * honey[depth]), depth + 1, sum + honey[depth]);
        int notSelect = subSet(honey, max, result, depth + 1, sum);
        return Math.max(select, notSelect);
    }

    private static void select(int depth, int start, int sum) {
        if(depth == 2) {
            answer = Math.max(answer, sum);
            return;
        }

        for(int i = start; i < N * (N - M + 1); i++) {
            int[] rowcol = findIdx(i);

            if (depth == 1) {
                int prevIdx = selected[0];
                int prevR = prevIdx / (N - M + 1);
                int prevC = prevIdx % (N - M + 1);

                // 같은 행이면서 벌통 영역이 겹치면 건너뜁니다.
                if (rowcol[0] == prevR && rowcol[1] < prevC + M) continue;
            }

            selected[depth] = i;
            select(depth + 1, i + 1, sum + max[rowcol[0]][rowcol[1]]);
        }
    }

    // 1차원 -> 2차원
    private static int[] findIdx(int idx) {
        int row = idx / (N - M + 1);
        int col = idx % (N - M + 1);
        return new int[]{row, col};
    }
}