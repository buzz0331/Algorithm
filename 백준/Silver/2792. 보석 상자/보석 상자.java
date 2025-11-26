import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int max = Integer.MIN_VALUE;
        int[] colors = new int[M];

        for(int i = 0; i < M; i++) {
            int c = Integer.parseInt(br.readLine());
            max = Math.max(max, c);
            colors[i] = c;
        }
        binarySearch(colors, 1, max);
        System.out.println(answer);
    }

    private static void binarySearch(int[] colors, int low, int high) {
        while(low <= high) {
            int mid = low + (high - low) / 2;

            boolean isPossible = simulate(colors, mid); // true : 해당 경우 가능 / false : 해당 경우 불가능
            if(isPossible) { // 가능하면 mid 감소
                answer = Math.min(answer, mid);
                high = mid - 1;
            } else { // 불가능하면 mid 증가
                low = mid + 1;
            }
        }
    }

    private static boolean simulate(int[] colors, int key) {
        List<Integer> minCase = new ArrayList<>(M);

        for(int c : colors) {
            int count = c / key;

            minCase.add(c % key == 0 ? count : count + 1);
        }

        int min = minCase.stream().mapToInt(i -> i).sum(); // 나올 수 있는 최소 분배 수

        return min <= N;
    }
}
