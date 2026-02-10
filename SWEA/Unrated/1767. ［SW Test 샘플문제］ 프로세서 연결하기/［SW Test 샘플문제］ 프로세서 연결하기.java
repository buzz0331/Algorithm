import java.util.*;
import java.io.*;

class Solution
{
    private static int N, connectMax, answer;
    private static List<int[]> processors;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String args[]) throws Exception
    {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            connectMax = -1; // 연결된 프로세서 개수
            answer = Integer.MAX_VALUE; // 최소 길이
            map = new int[N][N];
            visited = new boolean[N][N];

            processors = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) { // 프로세서라면
                        visited[i][j] = true;
                        if(i == 0 || j == 0 || i == N - 1 || j == N - 1) continue; // 가장자리에 붙은 프로세서 패스
                        processors.add(new int[]{i, j});
                    }
                }
            }

            backtracking(0, 0, 0);
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    private static void backtracking(int depth, int connectCount, int lengthSum) {
        if(depth == processors.size()) {
            if(connectMax < connectCount) {
                answer = lengthSum;
                connectMax = connectCount;
            } else if(connectMax == connectCount) {
                answer = Math.min(answer, lengthSum);
            }
            return;
        }

        // 선택하지 않은 경우
        backtracking(depth + 1, connectCount, lengthSum);

        // 선택한 경우
        int[] current = processors.get(depth);
        for(int[] direction : directions) {
            int nR = current[0];
            int nC = current[1];
            int count = 0;
            boolean possible = true;

            //방문 체크
            while(true) {
                nR += direction[0];
                nC += direction[1];

                if(nR < 0 || nC < 0 || nR >= N || nC >= N) break;

                if(visited[nR][nC]) {
                    possible = false;
                    break;
                }
            }

            // 가능한 경우 방문체크
            if(possible) {
                int distance= fill(current, direction, true);
                backtracking(depth + 1, connectCount + 1, lengthSum + distance);
                fill(current, direction, false);
            }
        }

    }

    private static int fill(int[] current, int[] direction, boolean flag) {
        int nR = current[0];
        int nC = current[1];
        int count = 0 ;

        while(true) {
            nR += direction[0];
            nC += direction[1];
            if(nR < 0 || nC < 0 || nR >= N || nC >= N) break;

            visited[nR][nC] = flag;
            count++;
        }
        return count;
    }
}