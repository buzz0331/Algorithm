import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] students;
    private static boolean[] visited;
    private static int count;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T --> 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            students = new int[n + 1];
            visited = new boolean[n + 1];
            count = 0;

            for(int i = 1; i <= n; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 1; i <= n; i++) {
                if(!visited[i]) dfs(i, new HashSet<>());
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }

    public static int dfs(int next, Set<Integer> visitedSet) {
        if(visitedSet.contains(next)) { // dfs 경로에서
            return next;
        }

        if(visited[next]) { // 이미 팀 여부가 결정된 학생이므로 현재 dfs 경로의 모든 학생은 팀 불가
            return -1;
        }

        visited[next] = true;
        visitedSet.add(next);

        int dfs = dfs(students[next], visitedSet);
        if(dfs == next) { // 팀 완성
            return -1; // 완성 된 이후 dfs 경로에 존재하는 다른 학생은 모두 팀에 속하지 못함
        } else if(dfs == -1) {
            count++;
            return -1;
        } else {
            return dfs;
        }
    }


}
