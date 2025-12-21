import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];
        for(int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int count = 0;
        for(int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());
//            print();
            // 부모 인덱스 찾기
            int root = find(g);

            union(root, g);

            int next = root - 1;
            if(next < 0) break;
            parent[root] = next;

            count++;
        }
//        print();

        System.out.print(count);
    }

    private static void print() {
        for(int x : parent) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    private static int find(int x) {
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX < parentY) {
            parent[parentY] = parentX;
        } else if(parentX > parentY) {
            parent[parentX] = parentY;
        }
    }
}
