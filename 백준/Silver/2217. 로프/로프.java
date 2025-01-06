import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> ropes = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            ropes.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(ropes);
        ArrayList<Integer> W = new ArrayList<>(N); //최대 중량 저장 리스트

        for (Integer rope : ropes) {
            W.add(rope * N);
            N--;
        }

        Integer max = Collections.max(W);

        bw.write(String.valueOf(max));
        bw.flush();
    }
}
