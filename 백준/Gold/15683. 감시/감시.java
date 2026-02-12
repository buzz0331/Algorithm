import java.util.*;
import java.io.*;

public class Main {

    private static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static Map<Integer, List<int[]>> cctvMap = new HashMap<>();
    private static List<Cctv> cctvList = new ArrayList<>();
    private static int[][] map, watch;
    private static int N, M;
    private static int minArea = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        watch = new int[N][M];

        cctvMap.put(1, Arrays.asList(new int[]{0}, new int[]{1}, new int[]{2}, new int[]{3}));
        cctvMap.put(2, Arrays.asList(new int[]{0, 1}, new int[]{2, 3}));
        cctvMap.put(3, Arrays.asList(new int[]{0, 2}, new int[]{2, 1}, new int[]{1, 3}, new int[]{3, 0}));
        cctvMap.put(4, Arrays.asList(new int[]{0, 2, 3}, new int[]{0, 1, 2}, new int[]{1, 2, 3}, new int[]{0, 1, 3}));
        cctvMap.put(5, Arrays.asList(new int[]{0, 1, 2, 3}));

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5) cctvList.add(new Cctv(i, j, map[i][j]));
            }
        }

        setCctv(0);
        System.out.println(minArea);
    }

    private static void setCctv(int depth) {
        if(depth == cctvList.size()) { // cctv 세팅 완료
            minArea = Math.min(minArea, calArea());
            return;
        }

//        for(int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }
//        System.out.println();

        Cctv cctv = cctvList.get(depth);
//        System.out.println(cctv.type);
        List<int[]> cctvDirections = cctvMap.get(cctv.type);
        for(int[] cd : cctvDirections) {
            for (int i : cd) {
                int[] direction = directions[i];
                // cctv 세팅
                fill(cctv, direction, 1);
            }

            setCctv(depth + 1);

            for (int i : cd) {
                int[] direction = directions[i];
                // cctv 복원
                fill(cctv, direction, -1);
            }
        }

    }

    private static void fill(Cctv cctv, int[] direction, int flag) {
        int row = cctv.row;
        int col = cctv.col;

        while(true) {
            row += direction[0];
            col += direction[1];

            if(row < 0 || col < 0 || row >= N || col >= M) break;
            if(map[row][col] == 6) break; // 벽만나면 그만 이동

            // 빈칸인 경우 감시 카운트 증가 (cctv는 통과)
            if(map[row][col] == 0) {
                watch[row][col] += flag;
            }
        }
    }

    private static int calArea() {
        int count = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0 && watch[i][j] == 0) count++;
            }
        }
        return count;
    }

    private static class Cctv {
        public int row, col;
        public int type;

        public Cctv(int row, int col, int type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }
}
