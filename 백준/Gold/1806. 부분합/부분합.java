import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] >= S) {
                System.out.print(1);
                return;
            }
        }

        int s = 0, e = 1;
        int min = Integer.MAX_VALUE;
        int sum = arr[0] + arr[1];
        while(s < e) {

            if(sum >= S) { // 조건 만족
                min = Math.min(min, e - s + 1);
                sum -= arr[s];
                s++;
            } else {
                e++;
                if(e == N) break;
                sum += arr[e];
            }
        }

        if(min == Integer.MAX_VALUE) System.out.print(0);
        else System.out.print(min);
    }
}
