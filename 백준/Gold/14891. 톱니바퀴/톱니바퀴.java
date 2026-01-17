import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] cogwheel = new int[4][8];
    private static int[] idx = new int[4];

    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 4; i++) {
            String[] s = br.readLine().split("");
            for(int j = 0; j < 8; j++) {
                cogwheel[i][j] = Integer.parseInt(s[j]);
            }
        }

        int K = Integer.parseInt(br.readLine());
        while(K --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;     // 톱니바퀴 번호
            boolean direction = Integer.parseInt(st.nextToken()) == 1; // 회전 방향 (1: 시계 / -1: 반시계)

            int[] spinInfo = new int[4]; // 이번 라운드에서 회전하는 톱니바퀴 (0 : 회전 x / 1 : 시계 / -1 : 반시계)

            spinInfo[num] = direction ? 1 : -1;
            direction = !direction; // 연쇄적으로 회전하는 톱니바퀴는 반대방향

            if(num == 0) { // 가장 왼쪽
                spinRight(num, spinInfo, direction);

            } else if(num == 3) { // 가장 오른쪽
                spinLeft(num, spinInfo, direction);
            } else if(num == 1) {
                // 왼쪽 톱니바퀴 회전여부 확인
                spinLeft(num, spinInfo, direction);

                spinRight(num, spinInfo, direction);
            } else {
                // 오른쪽 톱니바퀴 회전여부 확인
                spinRight(num, spinInfo, direction);

                spinLeft(num, spinInfo, direction);
            }

            for(int i = 0; i < 4; i++) {
                spin(i, spinInfo[i]);
            }
        }

        int sum = 0;
        for(int i = 0; i < 4; i++) {
            if(cogwheel[i][idx[i]] == 1) {
                if(i == 0) sum += 1;
                else sum += (1 << i);
            }
        }

        System.out.print(sum);
    }

    private static void spinLeft(int x, int[] spinInfo, boolean direction) {
        while(x > 0 && checkLeft(x)) {
            spinInfo[x - 1] = direction ? 1 : -1;
            direction = !direction;
            x--;
        }
    }

    private static void spinRight(int x, int[] spinInfo, boolean direction) {
        while(x < 3 && checkRight(x)) {
            spinInfo[x + 1] = direction ? 1 : -1;
            direction = !direction;
            x++;
        }
    }

    // num번 톱니바퀴를 x만큼 회전 (시계, 반시계)
    private static void spin(int num, int direction) {
        if(direction == -1) { // 시계
            if(++idx[num] == 8) {
                idx[num] = 0;
            }
        } else if (direction == 1) { // 반시계
            if(--idx[num] == -1) {
                idx[num] = 7;
            }
        }

    }

    // num번 톱니바퀴의 서쪽/동쪽의 상태를 확인
    private static int check(int num, boolean isRight) {
        if(isRight) {
            return cogwheel[num][(idx[num] + 2) % 8];
        } else {
            return cogwheel[num][(idx[num] + 6) % 8];
        }
    }

    // 왼쪽 톱니바퀴 회전 여부 확인
    private static boolean checkLeft(int num) {
        return check(num, false) != check(num - 1, true);
    }

    // 오른쪽 톱니바퀴 회전 여부 확인
    private static boolean checkRight(int num) {
        return check(num, true) != check(num + 1, false);
    }



}
