import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer s_A = new StringTokenizer(br.readLine());
        StringTokenizer s_B = new StringTokenizer(br.readLine());
        ArrayList<Integer> A = new ArrayList<>(N);
        ArrayList<Integer> B = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(s_A.nextToken()));
            B.add(Integer.parseInt(s_B.nextToken()));
        }

        Collections.sort(A);//정렬
        Collections.sort(B, Collections.reverseOrder());//역정렬

        int sum = 0;
        for (int i = 0; i < N; i++) {
            Integer a = A.get(i);
            Integer b = B.get(i);
            sum += a*b;
        }

        System.out.println(sum);
    }
}
