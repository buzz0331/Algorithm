import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] room;
    private static int[][] dxy = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // -> , <- , ^ , v
    private static int R, C;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int airCol = 0;    //공기청정기가 위치한 행 (아래 기준)

        room = new int[R][C];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] == -1) airCol = i;   //공기청정기는 무조건 1열이므로 행만 저장
            }
        }

        while(T --> 0) {
            int[][] newRoom = new int[R][C];
            newRoom[airCol - 1][0] = -1;
            newRoom[airCol][0] = -1;
            room = diffusion(newRoom);
            actionAir(airCol);
        }

        System.out.println(calDust());
    }

    private static int calDust() {
        int sum = 0;

        //미세먼지 총합
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(room[i][j] != -1) sum += room[i][j];
            }
        }

        return sum;
    }

    private static void actionAir(int airCol) {
        //위쪽 시계방향 업데이트
        //아래쪽 시계반대방향 업데이트
        for(int i = airCol - 2; i >= 1; i--) { //공기청정기 위쪽 업데이트
            room[i][0] = room[i - 1][0];
        }

        for(int i = airCol + 1; i < R - 1; i++) { //공기청정기 아래쪽 업데이트
            room[i][0] = room[i + 1][0];
        }

        for(int i = 0; i < C - 1; i++) {
            room[0][i] = room[0][i + 1];  //맨 윗줄 업데이트
            room[R - 1][i] = room[R - 1][i + 1]; //맨 아래줄 업데이트
        }
        for(int i = 0; i <= airCol - 2; i++) {  //맨 오른쪽 업데이트
            room[i][C - 1] = room[i + 1][C - 1];
        }

        for(int i = R - 1; i > airCol; i--) {   //맨 오른쪽 업데이트
            room[i][C - 1] = room[i - 1][C - 1];
        }
        for(int i = C - 1; i > 1; i--) {
            room[airCol - 1][i] = room[airCol - 1][i - 1];  //공기청정기 줄 업데이트
            room[airCol][i] = room[airCol][i - 1];
        }

        //업데이트 안된 공기청정기 바로 오른쪽 업데이트
        room[airCol - 1][1] = 0;
        room[airCol][1] = 0;
    }

    private static int[][] diffusion(int[][] newRoom) {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(room[i][j] > 0) {
                    int diffusionValue = room[i][j] / 5; //확산되는 값
                    int count = 0; //확산되는 방향 수
                    for(int[] d : dxy) {
                        int row = d[1] + i;
                        int col = d[0] + j;

                        if(row < 0 || col < 0 || row >= R || col >= C) {
                            continue;      //방 밖으로 확산 x
                        }
                        if(room[row][col] == -1) {
                            continue;      //공기청정정기로 확산 x
                        }

                        newRoom[row][col] += diffusionValue;
                        count++;
                    }

                    newRoom[i][j] += room[i][j] - diffusionValue * count; //현재 탐색 위치 업데이트
                }
            }
        }

        return newRoom;
    }
}
