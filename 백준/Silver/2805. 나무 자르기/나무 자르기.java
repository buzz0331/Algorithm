import java.util.*;
import java.io.*;

public class Main {

    private static int[] trees;
    private static int N, answer = -1;
    private static long M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = -1;
        for(int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        binarySearch(0, max);
        System.out.print(answer);
    }

    private static void binarySearch(int start, int end) {
        while(start <= end) {
            int mid = start + (end - start) / 2;
            
            long result = cutTrees(mid);
            if(result >= M) { // 너무 많이 잘렸으니 mid 증가 (요구사항 충족)
                answer = Math.max(answer, mid);
                start = mid + 1;
            } else { // 요구사항 충족 x -> mid 감소
                end = mid - 1;
            }
        }
    }

    private static long cutTrees(int height) {
        long sum = 0;
        for(int i = 0; i < N; i++) {
            if(trees[i] > height) {
                sum += trees[i] - height;
            }
        }
        return sum;
    }
}
