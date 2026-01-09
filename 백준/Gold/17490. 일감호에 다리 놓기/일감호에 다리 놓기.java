import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parents, cost;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        parents = new int[N + 1];
        cost = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if ((x == N && y == 1) || (x == 1 && y == N)) {
                set.add(1);
                continue;
            }
            set.add(Math.max(x, y));
        }

        // 집합 맺기
        for(int i = 1; i <= N; i++) {
            if(i == N) {
                if(set.contains(1)) continue; // 공사장 때문에 연결 x
                union(i, 1);
            } else {
                if(set.contains(i + 1)) continue; // 공사장 때문에 연결 x
                union(i, i + 1);
            }
        }

        Map<Integer, Integer> group = new HashMap<>(); // 부모 인덱스, 최소 비용
        for(int i = 1; i <= N; i++) {
            int parent = find(i);
            group.put(parent, Math.min(group.getOrDefault(parent, Integer.MAX_VALUE), cost[i]));
        }

        if(group.size() == 1) {
            System.out.print("YES");
            return;
        }

        long count = 0;
        for(int key : group.keySet()) {
            count += group.get(key);

            if(count > K) {
                System.out.print("NO");
                return;
            }
        }

        System.out.print("YES");
    }

    private static int find(int x) {
        if(parents[x] == x) return x;

        return parents[x] = find(parents[x]);
    }

    private static void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX < parentY) {
            parents[parentY] = parentX;
        } else {
            parents[parentX] = parentY;
        }
    }
}
