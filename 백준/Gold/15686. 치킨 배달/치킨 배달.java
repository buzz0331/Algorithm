import java.util.*;
import java.io.*;

public class Main {

    private static List<int[]> chickens = new ArrayList<>(), houses = new ArrayList<>();
    private static int N, M;
    private static int[] selectedChickens;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       StringTokenizer st = new StringTokenizer(br.readLine());
       N = Integer.parseInt(st.nextToken());
       M = Integer.parseInt(st.nextToken());

       selectedChickens = new int[M];

       for(int i = 0; i < N; i++) {
           st = new StringTokenizer(br.readLine());
           for(int j = 0; j < N; j++) {
               int temp = Integer.parseInt(st.nextToken());
               if(temp == 2) chickens.add(new int[]{i, j});
               else if(temp == 1) houses.add(new int[]{i, j});
           }
       }

        selectChickens(0, 0);
       System.out.print(min);

    }

    private static void selectChickens(int depth, int start) {
        if(depth == M) {
            calChickenDistance();
//            System.out.println(Arrays.toString(selectedChickens));
            return;
        }

        for(int i = start; i < chickens.size(); i++) {
            selectedChickens[depth] = i;
            selectChickens(depth + 1, i + 1);
        }
    }

    private static void calChickenDistance() {
        int[] distances = new int[houses.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        for(int s : selectedChickens) {
            int[] chickenLocation = chickens.get(s);
            for(int i = 0; i < houses.size(); i++) {
                int[] houseLocation = houses.get(i);

                int distance = Math.abs(chickenLocation[0] - houseLocation[0]) + Math.abs(chickenLocation[1] - houseLocation[1]);
                distances[i] = Math.min(distances[i], distance);
//                System.out.println(Arrays.toString(distances));
            }
        }



        int sum = 0;
        for(int d : distances) {
            sum += d;
        }

        min = Math.min(min, sum);
    }

}
