import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    private static final int MAX = 1001;
    private static final boolean[] covered = new boolean[MAX * 2];       //테이프를 붙인 곳

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   //물이 새는 곳의 개수
        int L = Integer.parseInt(st.nextToken());   //테이프의 길이

        int count = 0;

        ArrayList<Integer> positions = new ArrayList<>(N);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            positions.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(positions);

        for (Integer position : positions) {
            if (!covered[position]) {
                for (int i = 0; i < L; i++) {
                    covered[position + i] = true;
                }
                count ++;
            }
        }
        System.out.println(count);
    }
}
