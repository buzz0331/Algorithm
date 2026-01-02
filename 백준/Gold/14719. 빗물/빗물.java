import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int answer = 0;

        int[] blocks = new int[W];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < W - 1; i++) { // 인덱스 별 모이는 빗물
            int left = 0;
            int right = 0;

            for(int j = 0; j < i; j++) {
                left = Math.max(blocks[j], left);
            }

            for(int j = i + 1; j < W; j++) {
                right = Math.max(blocks[j], right);
            }

            if(blocks[i] < left && blocks[i] < right) answer += Math.min(left, right) - blocks[i];

        }

        System.out.print(answer);

    }
}
