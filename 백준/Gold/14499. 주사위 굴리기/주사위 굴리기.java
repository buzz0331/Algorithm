import java.io.*;
import java.util.*;

public class Main {
    static int N, M, x, y, K;
    static int[][] map;
    static int[] dice = new int[6];

    // 방향: 동서북남
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++) {
            int dir = Integer.parseInt(st.nextToken());
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            x = nx;
            y = ny;

            dice = rollDice(dice, dir);

            if (map[x][y] == 0) {
                map[x][y] = dice[0]; // 바닥 → 지도
            } else {
                dice[0] = map[x][y]; // 지도 → 바닥
                map[x][y] = 0;
            }

            System.out.println(dice[5]); // 윗면 출력
        }
    }

    private static int[] rollDice(int[] dice, int dir) {
        int[] newDice = dice.clone();

        switch (dir) {
            case 1: // 동쪽
                newDice[0] = dice[1];
                newDice[1] = dice[5];
                newDice[5] = dice[2];
                newDice[2] = dice[0];
                break;
            case 2: // 서쪽
                newDice[0] = dice[2];
                newDice[2] = dice[5];
                newDice[5] = dice[1];
                newDice[1] = dice[0];
                break;
            case 3: // 북쪽
                newDice[0] = dice[3];
                newDice[3] = dice[5];
                newDice[5] = dice[4];
                newDice[4] = dice[0];
                break;
            case 4: // 남쪽
                newDice[0] = dice[4];
                newDice[4] = dice[5];
                newDice[5] = dice[3];
                newDice[3] = dice[0];
                break;
        }

        return newDice;
    }
}