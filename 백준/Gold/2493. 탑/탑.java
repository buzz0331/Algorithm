import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] towers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(simulate(towers));
    }

    private static String simulate(int[] towers) {
        Stack<Tower> towerStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        sb.append("0").append(" ");
        towerStack.push(new Tower(0, towers[0]));

        for(int i = 1; i < towers.length; i++) {
            int currentHeight = towers[i];

            while(true) {
                if(towerStack.isEmpty()) {
                    towerStack.push(new Tower(i, towers[i]));
                    sb.append("0" + " ");
                    break;
                }

                Tower tower = towerStack.pop();
                if(tower.height <= currentHeight) {
                    continue; // 뒤에 나온 탑이 더 높으면 더이상 앞에 있는 탑을 스택에 넣을 필요 X
                }

                sb.append(tower.index + 1).append(" ");
                towerStack.push(tower);
                towerStack.push(new Tower(i, towers[i]));
                break;
            }

        }
        return sb.toString();
    }

    private static class Tower{
        public int index;
        public int height;

        Tower(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

}
