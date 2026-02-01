import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, R;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int layerCount = Math.min(N, M) / 2;

//        int row = N;
//        int col = M;

        for(int layer = 0; layer < layerCount; layer++) {
            ArrayList<Integer> list = new ArrayList<>();

            // 왼 -> 오
            for(int j = layer; j < M - layer; j++) {
                list.add(arr[layer][j]);
            }

            // 위 -> 아래
            for(int j = layer + 1; j < N - 1 - layer; j++) {
                list.add(arr[j][M - 1 - layer]);
            }

            // 오 -> 왼
            for(int j = M - 1 - layer; j >= layer; j--) {
                list.add(arr[N - 1 - layer][j]);
            }

            // 아래 -> 위
            for(int j = N - 2 - layer; j > layer; j--) {
                list.add(arr[j][layer]);
            }

            reassign(arr, layer, list);
        }

        print(arr);

    }

    private static void print(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void reassign(int[][] arr, int layer, ArrayList<Integer> list) {
        int size = list.size();
        int index = R % size;
        // 왼 -> 오
        for(int j = layer; j < M - layer; j++) {
            arr[layer][j] = list.get(index++ % size);
        }

        // 위 -> 아래
        for(int j = layer + 1; j < N - 1 - layer; j++) {
            arr[j][M - 1 - layer] = list.get(index++ % size);
        }

        // 오 -> 왼
        for(int j = M - 1 - layer; j >= layer; j--) {
            arr[N - 1 - layer][j] = list.get(index++ % size);
        }

        // 아래 -> 위
        for(int j = N - 2 - layer; j > layer; j--) {
            arr[j][layer] = list.get(index++ % size);
        }

    }
}
