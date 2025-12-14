import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] students;
    private static int[] state; // 0: 미방문, 1: 현재 DFS 경로(진행중), 2: 처리완료
    private static int count;   // 팀에 속하지 못한 학생 수

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            students = new int[n + 1];
            state = new int[n + 1];
            count = 0;

            for (int i = 1; i <= n; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (state[i] == 0) dfs(i);
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }

    // 반환값 의미
    // -1 : 현재 경로에서 "사이클 처리(팀 확정)"가 끝났거나, 이미 완료된 경로라 더 이상 카운트할 게 없음
    //  k : 사이클의 시작 노드(또는 사이클 처리 중임을 나타내는 노드 번호). 위로 전달되면서 팀/비팀 판정을 진행
    public static int dfs(int next) {

        // 이미 처리 완료된 노드를 만난 경우: 현재 경로의 남은 노드들은 전부 팀이 될 수 없음(사이클로 합류 불가)
        if (state[next] == 2) {
            return -1;
        }

        // 현재 DFS 경로에서 다시 만난 경우: next가 사이클의 시작점
        if (state[next] == 1) {
            return next;
        }

        // 방문 처리(경로 진입)
        state[next] = 1;

        int res = dfs(students[next]);

        if (res == -1) {
            // 이미 사이클 처리 끝났거나, 완료 경로로 빠졌으므로 next는 팀에 속하지 못함
            count++;
            state[next] = 2;
            return -1;
        }

        if (res == next) {
            // next가 사이클의 시작점으로 돌아왔다는 뜻
            // next는 사이클에 포함(팀), 여기서 사이클 처리를 종료시키기 위해 -1 반환
            state[next] = 2;
            return -1;
        }
        
        state[next] = 2;
        return res;
    }

}
