import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] buildings;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int answer = -1;

        buildings = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        for(int targetX = 1; targetX <= N; targetX++) {
            int targetY = buildings[targetX];
            int viewCount = 0; // 기준 빌딩에서 볼 수 있는 고층빌딩 수

            for(int checkX = 1; checkX <= N; checkX++) {
                if(targetX == checkX) continue;

                int checkY = buildings[checkX];
                double a = getSlope(targetX, targetY, checkX, checkY); // 기울기
                double b = getConstant(targetX, targetY, a); // 상수

                if(checkCanView(a, b, targetX, checkX))
                    viewCount++;
            }

            answer = Math.max(answer, viewCount);
        }

        System.out.println(answer);
    }

    //기울기 구하는 메서드
    private static double getSlope(int targetX, int targetY, int checkX, int checkY) {
        return (double) (targetY - checkY) / (targetX - checkX);
    }

    //상수 구하는 메서드
    private static double getConstant(int targetX, int targetY, double a) {
        return (double) targetY - (a * targetX);
    }

    private static boolean checkCanView(double a, double b, int targetX, int checkX) {
        int from, to;
        if(targetX < checkX) {
            from = targetX;
            to = checkX;
        } else {
            from = checkX;
            to = targetX;
        }

        for (int i = from + 1; i < to; i++) {
            if(a * i + b <= buildings[i]) {
                return false;
            }
        }
        return true;
    }
}
