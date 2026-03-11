import java.util.*;
import java.io.*;

public class Main {

    private static int[] dices = new int[10], sequences = new int[10];
    private static final Map<Integer, Path> pathMap =  // 길이, 한 칸 거리, 시작 점수
            Map.of(
                    1, new Path(3, 3, 13),
                    2, new Path(2, 2, 22),
                    3, new Path(3, -1, 28)
            );
    private static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i =  0; i < 10; i++) {
            dices[i] = Integer.parseInt(st.nextToken());
        }

        makeSequence(0, 0);
        System.out.println(max);
    }

    private static void makeSequence(int depth, int usedCount) {
        if (depth == 10) {
            startGame(); // 10개를 다 골랐을 때만 게임 시작
            return;
        }

        int limit = Math.min(usedCount + 1, 4);

        for (int i = 0; i < limit; i++) {
            sequences[depth] = i;
            if (i == usedCount) {
                makeSequence(depth + 1, usedCount + 1);
            } else {
                makeSequence(depth + 1, usedCount);
            }
        }
    }

    private static void startGame() {
        Player[] players = new Player[4]; // 4개의 말을 상태 관리
        boolean[][] visited = new boolean[41][5];
        int sum = 0;

        for(int i = 0; i < 4; i++) {
            players[i] = new Player(0, 0, 0);
        }

        for(int idx = 0; idx < 10; idx++) {
            int dice = dices[idx]; // 움직여야할 주사위 개수
            int seq = sequences[idx]; // 움직여야할 말 순서
            Player player = players[seq];
            int previous = player.current;
            int previousMode = player.mode;

            if(player.isFinish) { // 이미 도착위치에 도착했으면 불가능한 경우
                return;
            }

            // 1. mode 전환
            if(player.mode != 4) player.switchMode();

            // 2. mode에 따른 주사위 이동
            player.movePlayer(dice);

            // 말이 아직 도착하지 않았으면
            if(!player.isFinish) {
                // 3. 이동시 말이 겹치면 return

                if(visited[player.current][player.mode]) return;
                visited[player.current][player.mode] = true;

                // 4. 이동을 마치면 해당 말의 점수 합치기
                sum += player.current;
            }

            visited[previous][previousMode] = false;
        }

        max = Math.max(max, sum);
    }

    private static class Path {
        int length;
        int distance;
        int start;

        Path(int length, int distance, int start) {
            this.length = length;
            this.distance = distance;
            this.start = start;
        }
    }

    private static class Player {
        int mode; // 0: 일반 / 1: 10만남 / 2: 20만남 / 3: 30만남
        int pathIdx; // 지름길에서 어느정도 갔는지 저장
        int current;
        boolean isFinish = false;

        private Player(int mode, int pathIdx, int current) {
            this.mode = mode;
            this.pathIdx = pathIdx;
            this.current = current;
        }

        private void switchMode() {
            if(this.current == 10) mode = 1;
            if(this.current == 20) mode = 2;
            if(this.current == 30) mode = 3;
        }

        private void movePlayer(int dice) {
            if(mode == 0) { // 일반
                this.current += dice * 2;
            } else if(mode != 4) { // 지름길

                Path path = pathMap.get(this.mode);

                // pathIdx가 0이라면 시작위치 초기화
                if(pathIdx == 0) {
                    current = path.start;
                    pathIdx = 1;
                    dice--; // 한칸 움직인 것
                }

                while(dice-- > 0) {
                    current += path.distance;
                    pathIdx++;
                    if(pathIdx > path.length) {
                        current = 25;
                        mode = 4;
                        pathIdx = 0;
                        break;
                    }
                }
            }

            if(mode == 4) current += dice * 5;
            if(current == 40) mode = 4;

            if(this.current > 40) isFinish = true;
        }
    }
}
