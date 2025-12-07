import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static long[] a;
    private static long[] tree;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        a = new long[N + 1];
        tree = new long[4 * N];

        for(int i = 1; i <= N; i++) {
            a[i] = Long.parseLong(br.readLine());
        }

        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if(op == 1) { // x번째 숫자를 y로 바꿈
                long y = Long.parseLong(st.nextToken());

                long current = sum(1, N, 1, x, x);
                update(1, N, 1, x, y - current);
            } else { // x번째부터 y번째까지 부분 합 출력
                int y = Integer.parseInt(st.nextToken());
                sb.append(sum(1, N, 1, x, y)).append("\n");
            }
        }

        System.out.print(sb);
    }

    // 세그먼트 트리 초기화
    private static long init(int start, int end, int node) {
        if(start == end) return tree[node] = a[start];

        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // 특정 구간의 합을 구하는 함수
    private static long sum(int start, int end, int node, int left, int right) {
        if(left > end || right < start) return 0;

        if(left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    // 특정 인덱스 값을 업데이트하는 함수
    private static void update(int start, int end, int node, int index, long diff) {
        if(index < start || index > end) return;

        tree[node] += diff;
        if(start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }
}
