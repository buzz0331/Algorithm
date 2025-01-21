import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int NO_TOMATO = -1;        //토마토 없음
    private static final int RIPE_TOMATO = 1;       //익은 토마토
    private static final int UNRIPE_TOMATO = 0;     //안익은 토마토

    private static int[][] box;
    private static boolean[][] visited;

    private static final int[] dX= {1, 0, -1, 0};
    private static final int[] dY= {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int day = 0;    //몇 일차인지
        int size = 0;

        box = new int[N][M];
//        visited = new boolean[N][M];

        Queue<RipeTomato> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == RIPE_TOMATO) {
                    queue.offer(new RipeTomato(i, j, 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            RipeTomato ripeTomato = queue.poll();
            if (ripeTomato.day != day) {
                day++;
            }

            int X = ripeTomato.X;
            int Y = ripeTomato.Y;

            for (int i = 0; i < 4; i++) {
                int check_X = X + dX[i];
                int check_Y = Y + dY[i];

                if (check_X < N && check_X >= 0 && check_Y < M && check_Y >= 0) {
                    if (box[check_X][check_Y]==UNRIPE_TOMATO) {

                        queue.offer(new RipeTomato(check_X, check_Y, day+1));
                        box[check_X][check_Y] = RIPE_TOMATO;
                    }
                }

            }

        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }


        System.out.println(day);




    }

    private static class RipeTomato {
        public int X;
        public int Y;
        public int day;     //몇일차

        public RipeTomato(int x, int y, int day) {
            X = x;
            Y = y;
            this.day = day;
        }
    }
}
