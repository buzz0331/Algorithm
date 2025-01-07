import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int count = 1;

        while (B >= 1) {
            if (B == A) {
                System.out.println(count);
                return;
            }

            if (B % 2 == 0) { //짝수인 경우
                B /= 2;
                count++;
            } else { //홀수 인경우
                B--; //1을 뺌

                if (B % 10 != 0) { //0으로 안 나누어 떨어지면 (ex. 원래 B가 83 이런 거라면)
                    System.out.println(-1);
                    return;
                }

                B /= 10;
                count ++;
            }

        }
        System.out.println(-1);

    }


}

