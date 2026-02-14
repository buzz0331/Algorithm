import java.util.*;
import java.io.*;

public class Main {

    private static int[] sequence = new int[9];
    private static boolean[] visited = new boolean[9];
    private static int N, answer = 0, score;
    private static int[][] inning;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inning = new int[N][10];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                inning[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sequence[3] = 0; // 4번 타자는 1번 선수로 고정
        visited[0] = true;
        align(0);
        System.out.print(answer);
    }

    private static void align(int depth) {
        if(depth == 9) {
            simulation();
            return;
        }

        if(depth == 3) {
            align(depth + 1);
            return;
        }

        for(int i = 0; i < 9; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            sequence[depth] = i;
            align(depth + 1);
            visited[i] = false;
            sequence[depth] = 0;
        }
    }

    private static void simulation() {
        int sIdx = 0;
        score = 0;
        for (int i = 0; i < N; i++) {
            sIdx = startInning(sIdx, i);
        }
        answer = Math.max(answer, score);
    }

    private static int startInning(int sIdx, int i) {
        int outCount = 0; // 아웃 카운트
        int field = 0;
        while (outCount < 3) {
            int currentPlayer = sequence[sIdx];
            int result = inning[i][currentPlayer];

            if(result == 0) {
                outCount++;
                sIdx = (sIdx + 1) % 9;
                continue;
            }

            field <<= result; // 기존 진루 주자 이동
            field |= 1 << (result - 1); // 타자 진출

            score += Integer.bitCount(field >> 3);
            field &= 7;

            sIdx = (sIdx + 1) % 9;
        }
        return sIdx;
    }
}
