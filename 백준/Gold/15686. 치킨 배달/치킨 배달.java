import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static final int HOUSE = 1;
    private static final int CHICKEN = 2;

    private static final ArrayList<Position> chickens = new ArrayList<>();
    private static final ArrayList<Position> chooseChickens = new ArrayList<>();
    private static final ArrayList<Position> houses = new ArrayList<>();

    private static boolean[] visited;

    private static int N;
    private static int M;

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        /**
         * 각 치킨, 집 리스트 초기화
         */
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                assignType(st, i, j);
            }
        }
        visited = new boolean[chickens.size()];
        for (int i = 0; i < chickens.size(); i++) {
            startDfs(i);
        }


        System.out.println(min);
    }

    private static void dfs(int currentChicken) {
        if (chooseChickens.size() == M) {    // M개의 조합을 완성했다면
            // 치킨거리 계산 시작
            calculateChickenDistance();
            return;
        }

        for (int i = currentChicken + 1; i < chickens.size(); i++) {
            if (!visited[i]) {
                startDfs(i);
            }
        }
    }

    private static void startDfs(int i) {
        visited[i] = true;
        chooseChickens.add(chickens.get(i));
        dfs(i);
        visited[i] = false;
        chooseChickens.remove(chooseChickens.size() - 1);
    }

    private static void calculateChickenDistance() {
        int sum = 0;
        int chickenDistance;
        for (Position house : houses) {
            chickenDistance = Integer.MAX_VALUE;
            for (Position chicken : chooseChickens) {
                // 가장 최소인 치킨거리 탐색
                chickenDistance = Math.min(Math.abs(house.X - chicken.X) + Math.abs(house.Y - chicken.Y), chickenDistance);
            }

            if (min < chickenDistance) {
                // 이미 현재 최소 치킨거리를 넘으면 백트래킹
                return;
            }

            sum += chickenDistance;
        }

        min = Math.min(min, sum);
    }


    private static void assignType(StringTokenizer st, int i, int j) {
        int type = Integer.parseInt(st.nextToken());
        if (type == CHICKEN) {
            chickens.add(new Position(i, j));
        }
        if (type == HOUSE) {
            houses.add(new Position(i, j));
        }
    }

    private static class Position {
        public int X;
        public int Y;

        public Position(int x, int y) {
            X = x;
            Y = y;
        }
    }
}
