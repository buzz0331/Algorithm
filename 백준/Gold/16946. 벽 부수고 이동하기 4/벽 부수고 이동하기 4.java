import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map, answer;
    private static boolean[][] visited;
    private static int N, M;

    private static final int[][] dRowCol = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static final Map<Integer, Integer> groupMap = new HashMap<>(); // 키 : 그룹 번호, 값 : 그룹 내의 좌표 갯수

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        List<int[]> wallList = new ArrayList<>(); // 벽의 좌표를 저장

        for(int i = 0; i < N; i++) {
            String[] s = br.readLine().split("");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(s[j]);
                if(map[i][j] == 1) wallList.add(new int[]{i ,j});
            }
        }

        int groupNum = 2; // 1은 벽이니까 겹치지 않게 2부터 시작
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!visited[i][j] && map[i][j] == 0) {
                    dfs(i, j, groupNum);
                    groupNum++;
                }
            }
        }

        answer = new int[N][M];
        for(int[] wall : wallList) {
            int row = wall[0];
            int col = wall[1];
            int count = 1;
            Set<Integer> visitedGroup = new HashSet<>();

            for(int[] rowcol : dRowCol) {
                int nRow = row + rowcol[0];
                int nCol = col + rowcol[1];

                if(nRow < 0 || nCol < 0 || nRow >= N || nCol >= M) continue; //맵 밖
                if(map[nRow][nCol] == 1) continue; //벽

                int groupIdx = map[nRow][nCol];
                if(visitedGroup.contains(groupIdx)) continue; // 이미 방문한 그룹

                visitedGroup.add(groupIdx);
                count += groupMap.get(groupIdx);
            }

            answer[row][col] = count % 10;
        }

        StringBuilder sb = new StringBuilder();

        for (int[] a : answer) {
            Arrays.stream(a).forEach(sb::append);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    // 좌표 그룹화
    private static void dfs(int row, int col, int groupNum) {
        visited[row][col] = true;
        groupMap.put(groupNum, groupMap.getOrDefault(groupNum, 0) + 1);
        map[row][col] = groupNum;

        for(int[] rowcol : dRowCol) {
            int nRow = row + rowcol[0];
            int nCol = col + rowcol[1];

            if(nRow < 0 || nCol < 0 || nRow >= N || nCol >= M) continue; //맵 밖
            if(visited[nRow][nCol]) continue; //이미 방문
            if(map[nRow][nCol] == 1) continue; //벽

            dfs(nRow, nCol, groupNum);
        }
    }
}
