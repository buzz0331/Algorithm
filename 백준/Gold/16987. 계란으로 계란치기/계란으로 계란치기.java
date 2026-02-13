import java.util.*;
import java.io.*;

public class Main {

    private static int[][] eggs;
    private static int N, answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int depth, int count) {
        if(depth == N) {
            answer = Math.max(answer, count);
            return;
        }

        int s = eggs[depth][0]; // 내구도
        int w = eggs[depth][1]; // 무게

        // 자기가 이미 깨졌으면 제거
        if(s <= 0) {
            dfs(depth + 1, count);
            return;
        };

        boolean canHit = false;
        for(int i = 0; i < N; i++) {
            if(i == depth) continue; // 자기 자신 제외
            if(eggs[i][0] <= 0) continue; // 이미 깨진 계란 제외

            eggs[i][0] -= w; // 계란 내구도 감소
            eggs[depth][0] -= eggs[i][1];
            canHit = true;

            if(eggs[depth][0] <= 0 && eggs[i][0] <= 0) { // 기준 계란 & 내려친 계란 모두 깨짐
                dfs(depth + 1, count + 2);
            } else if(eggs[i][0] <= 0) { // 내리친 계란 깨짐
                dfs(depth + 1, count + 1);
            }
            else if(eggs[depth][0] <= 0){
                dfs(depth + 1, count + 1);
            } else {
                dfs(depth + 1, count);
            }
            eggs[i][0] += w;
            eggs[depth][0] += eggs[i][1];
        }

        // 한번도 못 친 경우
        if(!canHit) {
            dfs(depth + 1, count);
        }
    }
}
