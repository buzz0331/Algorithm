import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    private static int[][] field;

    private static int N;
    private static int M;
    private static int B;

    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;

    private static int result = Integer.MAX_VALUE;
    private static int blockResult;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //세로
        M = Integer.parseInt(st.nextToken());   //가로
        B = Integer.parseInt(st.nextToken());   //인벤토리에 있는 블록 갯수

        field = new int[N][M];

        initializedField(br);

        startBruteFore();

        System.out.println(result + " " + blockResult);
    }

    private static void startBruteFore() {
        BruteFore:for (int idx = min; idx <= max; idx++) {
            int tempB = B;
            int time = 0;
            for (int i = 0; i < N; i++) {   //빼낼 블록 먼저 파내기
                for (int j = 0; j < M; j++) {
                    if (field[i][j] > idx) {
                        int gap = field[i][j] - idx;
                        tempB += gap;
                        time += gap * 2;
                    }
                }
            }

            for (int i = 0; i < N; i++) {   //추가할 블록 설치
                for (int j = 0; j < M; j++) {
                    if (field[i][j] < idx) {
                        int gap = idx - field[i][j];
                        if (tempB < gap) {
                            continue BruteFore;
                        }
                        time += gap;
                        tempB -= gap;
                    }
                }
            }

            if (time <= result) {
                result = time;
                blockResult = idx;
            }
        }


    }

    private static void initializedField(BufferedReader br) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int numOfBlock = Integer.parseInt(st.nextToken());
                field[i][j] = numOfBlock;
                max = Math.max(max, numOfBlock);
                min = Math.min(min, numOfBlock);
            }
        }
    }
}
