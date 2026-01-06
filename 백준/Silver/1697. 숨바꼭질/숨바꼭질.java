import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[100001];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {N, 0});
        visited[N] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentPos = current[0];
            int currentSec = current[1];
            if(currentPos == K) {
                System.out.print(currentSec);
                return;
            }

            int move1 = currentPos + 1;
            int move2 = currentPos - 1;
            int move3 = currentPos * 2;

            if(move1 >= 0 && move1 <= MAX && !visited[move1]) {
                queue.offer(new int[]{move1, currentSec + 1});
                visited[move1] = true;
            }

            if(move2 >= 0 && move2 <= MAX && !visited[move2]) {
                queue.offer(new int[]{move2, currentSec + 1});
                visited[move2] = true;
            }

            if(move3 >= 0 && move3 <= MAX && !visited[move3]) {
                queue.offer(new int[]{move3, currentSec + 1});
                visited[move3] = true;
            }

        }
    }
}
