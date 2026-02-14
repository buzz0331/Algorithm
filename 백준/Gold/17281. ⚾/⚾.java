import java.util.*;
import java.io.*;

public class Main {

    private static int[] sequence = new int[9];
    private static boolean[] visited = new boolean[9];
    private static int N, answer = 0;
    private static int[][] ining;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ining = new int[N][10];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                ining[i][j] = Integer.parseInt(st.nextToken());
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
        int totalScore = 0;
        for(int i = 0; i < N; i++) {
            int outCount = 0; // 아웃 카운트
            boolean[] field = new boolean[3]; // 각 루의 진출 여부
            int score = 0;
            int[] iningInfo = ining[i];
            while(outCount < 3) {
                int currentPlayer = sequence[sIdx];

                switch(iningInfo[currentPlayer]) {
                    case 0: // 아웃
                        outCount++;
                        break;
                    case 1: // 안타
                        score += move(field, 1);
                        break;
                    case 2: // 2루타
                        score += move(field, 2);
                        break;
                    case 3:
                        score += move(field, 3);
                        break;
                    case 4:
                        int count = 1;
                        for(int f = 0; f < 3; f++) {
                            if(field[f]) {
                                count++;
                                field[f] = false;
                            }
                        }
                        score += count;
                        break;
                }

                sIdx = (sIdx + 1) % 9;
            }
            totalScore += score;
        }
        answer = Math.max(answer, totalScore);

    }

    private static int move(boolean[] field, int power) {
        int score = 0;

        // 홈 이동 가능한 진루 주자 이동
        for(int i = 0; i < power; i++) {
            if(field[2 - i]) {
                score++;
                field[2 - i] = false;
            }
        }

        // 진루 이동
        for(int f = 2; f >= power; f--) {
            field[f] = field[f - power];
            field[f - power] = false;
        }

        // 타자 이동
        field[power - 1] = true;
        return score;
    }
}
