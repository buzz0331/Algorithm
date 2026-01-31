import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int length = 2 * (R + C); // 직사각형을 일자로 풀었을때 길이

        int K = Integer.parseInt(br.readLine());
        List<Integer> stores = new ArrayList<>();

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int location = Integer.parseInt(st.nextToken());

            switch(direction) {
                case 1:
                    stores.add(R - location);
                    break;
                case 2:
                    stores.add(R + C + location);
                    break;
                case 3:
                    stores.add(R + location);
                    break;
                case 4:
                    stores.add(length - location);
                    break;
            }
        }

        st = new StringTokenizer(br.readLine());
        int pDirection = Integer.parseInt(st.nextToken());
        int pLocation = Integer.parseInt(st.nextToken());
        int person = 0;

        switch(pDirection) {
            case 1:
                person = R - pLocation;
                break;
            case 2:
                person = R + C + pLocation;
                break;
            case 3:
                person = R + pLocation;
                break;
            case 4:
                person = length - pLocation;
                break;
        }

        int sum = 0;
        for(int store : stores) {
            int gap = Math.abs(person - store);
            sum += Math.min(gap, length - gap);
        }

        System.out.print(sum);
    }
}
