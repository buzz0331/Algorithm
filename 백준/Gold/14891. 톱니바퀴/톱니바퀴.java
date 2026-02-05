import java.io.*;
import java.util.*;

class Main
{

    private static int[][] magnetic, spinInfo;
    private static int[] top;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        top = new int[4];

        magnetic = new int[4][8];
        for (int i = 0; i < 4; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                magnetic[i][j] = c[j] - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        spinInfo = new int[K][2];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            spinInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
            spinInfo[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int[] spin : spinInfo) {
            bfs(spin[0], spin[1]);
//                System.out.println(Arrays.toString(top));
        }

        int sum = 0;
        double mul = 0.5;
        for (int i = 0; i < 4; i++) {
            mul *= 2;
            if (magnetic[i][top[i]] == 1) {
                sum += mul;
            }
        }

        sb.append(sum).append("\n");

        System.out.print(sb);
    }

    private static void bfs(int num, int spinDirection) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] spin = new int[4];

        queue.offer(num);
        spin[num] = spinDirection;

        while(!queue.isEmpty()) {
            int current = queue.poll();

            int next = current - 1;
            if(next >= 0 && spin[next] == 0 && checkDifferent(next, current)) {
                spin[next] = (spin[current] == 1) ? -1 : 1;
                queue.offer(next);
            }

            next = current + 1;
            if(next < 4 && spin[next] == 0 && checkDifferent(current, next)) {
                spin[next] = (spin[current] == 1) ? -1 : 1;
                queue.offer(next);
            }
        }

        for(int i = 0; i < 4; i++) {
            if(spin[i] == 0) continue;
            top[i] += (spin[i] == 1) ? -1 : 1;
            if(top[i] < 0) {
                top[i] += 8;
            } else if(top[i] >= 8) {
                top[i] = 0;
            }
        }
    }

    // 왼쪽 톱니바퀴와 오른쪽 톱니바퀴의 자성 같은지 여부
    private static boolean checkDifferent(int a, int b) {
        int aIdx = (top[a] + 2) % 8;
        int bIdx = (top[b] + 6) % 8;

        return magnetic[a][aIdx] != magnetic[b][bIdx];
    }
}