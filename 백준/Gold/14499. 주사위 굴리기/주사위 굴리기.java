import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] map;

    private static final Map<Integer, int[]> directions = Map.of(
            1, new int[]{0, 1},
            2, new int[]{0, -1},
            3, new int[]{-1, 0},
            4, new int[]{1, 0}
            );

    private static int N, M, col, row, K;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        //첫째 줄에 지도의 세로 크기 N, 가로 크기 M (1 ≤ N, M ≤ 20), 주사위를 놓은 곳의 좌표 x, y(0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1), 그리고 명령의 개수 K (1 ≤ K ≤ 1,000)가 주어진다.
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        rollDice(st);

        bw.write(sb.toString());
        bw.flush(); bw.close();
    }

    private static void rollDice(StringTokenizer st) {
        int[] dice = new int[6];

        while(K --> 0) {
            int direction = Integer.parseInt(st.nextToken());
            int[] dRowCol = directions.get(direction);
            int newRow = row + dRowCol[0];
            int newCol = col + dRowCol[1];

            if(newRow < 0 || newRow >= N || newCol < 0 || newCol >= M) continue;

            row = newRow;
            col = newCol;

            updateDiceIdx(dice, direction);
            updateDiceOrMap(dice);
            sb.append(dice[5]).append("\n");
        }
    }

    private static void updateDiceOrMap(int[] dice) {
        if(map[row][col] == 0) {
            map[row][col] = dice[0];
            return;
        }
        if (map[row][col] != 0) {
            dice[0] = map[row][col];
            map[row][col] = 0;
        }

    }

    private static void updateDiceIdx(int[] dice, int direction) {
        int temp = dice[0];
        switch(direction) {
            case 1:
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[2];
                dice[2] = temp;
                break;
            case 2:
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[1];
                dice[1] = temp;
                break;
            case 3:
                dice[0] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[4];
                dice[4] = temp;
                break;
            case 4:
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[3];
                dice[3] = temp;
                break;
        }
    }


}
