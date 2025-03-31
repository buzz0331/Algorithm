import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[][] S;
    private static int numOfEachTeam;

    private static boolean[] team;

    private static final boolean START = true;
    private static final boolean LINK = false;

    private static int minGap = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numOfEachTeam = N / 2;       // 한 팀에 선수 명수
        S = new int[N + 1][N + 1];
        team = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(minGap);
    }

    private static void dfs(int depth, int nextIdx) {

        if (depth == numOfEachTeam) {
            calculateSkillGap();
            return;
        }

        for (int i = nextIdx + 1; i <= N; i++) {
            if (!team[i]) {
                team[i] = START;
                dfs(depth + 1, i);
                team[i] = LINK;
            }
        }

    }

    private static void calculateSkillGap() {
        int sumOfStart = 0;
        int sumOfLink = 0;

        for (int i = 1; i <= N; i++) {
            if (team[i] == START) {
                sumOfStart = getSum(i, sumOfStart, START);
            } else {
                sumOfLink = getSum(i, sumOfLink, LINK);
            }
        }

        int gap = Math.abs(sumOfStart - sumOfLink);
        minGap = Math.min(gap, minGap);
    }

    private static int getSum(int i, int sum, final boolean teamColor) {
        for (int j = i + 1; j <= N; j++) {
            if (team[j] == teamColor) {
                sum += S[i][j] + S[j][i];
            }
        }
        return sum;
    }
}
