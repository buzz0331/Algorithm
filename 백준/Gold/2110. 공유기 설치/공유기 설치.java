import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[] houses;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        houses = new int[N];

        for(int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);


        int lo = 1;
        int hi = houses[N - 1] - houses[0] + 1;

        while(lo < hi) {
            int mid = (hi + lo) / 2;

            if(canInstall(mid) < C) {
                hi = mid;
            }

            else {
                lo = mid + 1;
            }
        }

        System.out.println(lo - 1);
    }

    private static int canInstall(int distance) {
        int count = 1;
        int last = houses[0];
        
        for (int i = 1; i< houses.length; i++) {
            int locate = houses[i];
            
            if(locate - last >= distance) {
                count++;
                last = locate;
            }
        }
        
        return count;
    }
}
