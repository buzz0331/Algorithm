import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, TreeSet<Integer>> setMap = new HashMap<>(); // 각 집합에서 최솟값, 집합 매핑
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for(int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(operation == 0) { // a와 b 합집합
                union(a, b);
            } else { // a와 b가 같은 집합인지 확인
                checkSameSet(a, b);
            }
        }
    }

    private static int find(int a) {
        if(parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static void checkSameSet(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if(aParent == bParent) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
