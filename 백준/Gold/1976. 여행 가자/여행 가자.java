import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 총 도시의 수
        int M = Integer.parseInt(br.readLine()); // 여행 계획의 도시 수

        parent = new int[N + 1];
        StringTokenizer st;

        for(int i = 1; i <= N; i++) {
            parent[i] = i;
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= N; j++) {
                int op = Integer.parseInt(st.nextToken());
                if(op == 0) continue;

                union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine()); // 여행 계획
        int currentTravel = Integer.parseInt(st.nextToken());
        boolean canTravelAll = true;
        for(int i = 0; i < M - 1; i++) {
            int nextTravel = Integer.parseInt(st.nextToken());

            if(!isSameSet(currentTravel, nextTravel)) {
                canTravelAll = false;
                break;
            }

            currentTravel = nextTravel;
        }

        System.out.print(canTravelAll ? "YES" : "NO");
    }

    private static int find(int x) {
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX < parentY) {
            parent[y] = x;
        } else if(parentX > parentY) {
            parent[x] = y;
        }
    }

    private static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }
}
