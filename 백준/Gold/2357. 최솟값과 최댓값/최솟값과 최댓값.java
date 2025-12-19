import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] minTree, maxTree;
    private static int[] num;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        num = new int[N + 1];
        minTree = new int[N * 4];
        maxTree = new int[N * 4];

        for(int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        initMinTree(1, N, 1);
        initMaxTree(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int min = min(1, N, 1, a, b);
            int max = max(1, N, 1, a, b);

            sb.append(min).append(" ").append(max).append("\n");
        }

        System.out.print(sb);
    }

    private static int initMinTree(int start, int end, int node) {
        if(start == end) return minTree[node] = num[start];

        int mid = (start + end) / 2;
        return minTree[node] = Math.min(initMinTree(start, mid, node * 2), initMinTree(mid + 1, end, node * 2 + 1));
    }

    private static int initMaxTree(int start, int end, int node) {
        if(start == end) return maxTree[node] = num[start];

        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(initMaxTree(start, mid, node * 2), initMaxTree(mid + 1, end, node * 2 + 1));
    }

    private static int min(int start, int end, int node, int left, int right) {
        if(left > end || right < start) return Integer.MAX_VALUE;

        if(left <= start && end <= right) return minTree[node];

        int mid = (start + end) / 2;
        return Math.min(min(start, mid, node * 2, left, right), min(mid + 1, end, node * 2 + 1, left, right));
    }

    private static int max(int start, int end, int node, int left, int right) {
        if(left > end || right < start) return Integer.MIN_VALUE;

        if(left <= start && end <= right) return maxTree[node];

        int mid = (start + end) / 2;
        return Math.max(max(start, mid, node * 2, left, right), max(mid + 1, end, node * 2 + 1, left, right));
    }
}
