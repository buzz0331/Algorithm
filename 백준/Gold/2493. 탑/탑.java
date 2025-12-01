import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Stack<Tower> stack = new Stack<>();
        int[] towers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
        }

        stack.push(new Tower(0, towers[0]));
        sb.append("0 ");

        for(int i = 1; i < N; i++) {
            int currentHeight = towers[i];

            while (true) {
                Tower tower = stack.pop();
                if(tower.height >= currentHeight) { // 탑 레이저 만나는 경우
                    sb.append(tower.index + 1).append(" ");
                    stack.push(tower);
                    stack.push(new Tower(i, currentHeight));
                    break;
                }

                if(stack.isEmpty()) {
                    sb.append("0 ");
                    stack.push(new Tower(i, currentHeight));
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    private static class Tower {
        public int index;
        public int height;

        Tower(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}
