import java.util.*;
import java.io.*;

public class Main {

    private static int[] value;
    private static ArrayList<Integer>[] adj;
    private static int min = Integer.MAX_VALUE;
    private static int total = 0, N;
    private static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        value = new int[N + 1];
        selected = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            value[i] = Integer.parseInt(st.nextToken());
            total += value[i];
        }

        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());

            for(int j = 0; j < x; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        subSet(1, 0);

        if(min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }

    }

    private static void subSet(int depth, int sum) {
        boolean result = false;
        if(depth == N + 1) { // 부분집합 완성

            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();

            for(int i = 1; i <= N; i++) {
                if(selected[i]) groupA.add(i);
                else groupB.add(i);
            }

            // 한 그룹에 몰리는 경우 제외
            if(groupA.isEmpty() || groupB.isEmpty()) return;

            // 두 그룹 모두 연결되어 있는지 확인
            if(isConnected(groupA) && isConnected(groupB)) {
                int sumA = 0;
                for(int idx : groupA) sumA += value[idx];
                int sumB = total - sumA;
                min = Math.min(min, Math.abs(sumA - sumB));
            }
            return;
        }

//        System.out.println("========================" + result);
//        for(int i = 1; i <= N; i++) {
//            if(selected[i]) System.out.print(i + " ");
//        }
//        System.out.println();
//
//        for(int i = 1; i <= N; i++) {
//            if(!selected[i]) System.out.print(i + " ");
//        }
//        System.out.println();
//        System.out.println("========================");

        selected[depth] = true;
        subSet(depth + 1, sum + value[depth]);
        selected[depth] = false;
        subSet(depth + 1, sum);

    }

    private static boolean isConnected(List<Integer> group) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int count = 0;
        int start = group.get(0);

        queue.offer(start);
        visited[start] = true;
        while(!queue.isEmpty()) {
            int current = queue.poll();

            count++;

            for(int next : adj[current]) {
                if(!visited[next] && group.contains(next)) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }

        return count == group.size(); // 선택되지 않은 그룹이 연결되어 있음
    }
}
