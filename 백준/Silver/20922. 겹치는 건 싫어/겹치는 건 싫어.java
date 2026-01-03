import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] num = new int[N];
        int[] count = new int[100001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int left = 0, right = 0;
        while(right < N) {
            count[num[right]]++;

            if (count[num[right]] > K) { // 오른쪽으로 갈수 있는 최대
                while(left < right && count[num[right]] > K) {
                    count[num[left]]--;
                    left++;
                }
            }
            right++;
            answer = Math.max(answer, right - left);
        }

        System.out.print(answer);
    }
}
