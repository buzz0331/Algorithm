import java.util.*;
import java.io.*;

public class Main {

    private static int[] parents;
    private static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            pq.offer(new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            });
        }

        System.out.print(kruskal());
    }

    private static int kruskal() {
        int sum = 0;
        int count = 0;
        while(!pq.isEmpty()) {
            int[] current = pq.poll();

            if (union(current[0], current[1])) {
                count++;
                sum += current[2];
            }

            if(count == N - 1) {
                break;
            }
        }
        return sum;
    }

    private static int find(int x) {
        if(parents[x] == x) return x;

        return parents[x] = find(parents[x]);
    }

    private static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return false;

        if(rootX < rootY) {
            parents[rootY] = rootX;
        } else {
            parents[rootX] = rootY;
        }

        return true;
    }
}
