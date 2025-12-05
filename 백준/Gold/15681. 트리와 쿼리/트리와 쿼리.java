import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Integer>[] connect;

    private static ArrayList<Integer>[] childrens;
    private static int[] parents;

    private static int[] subTreeNodes;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점의 수
        int R = Integer.parseInt(st.nextToken()); // 루트의 번호
        int Q = Integer.parseInt(st.nextToken()); // 쿼리의 수

        connect = new ArrayList[N + 1];
        childrens = new ArrayList[N + 1];
        parents = new int[N + 1];
        subTreeNodes = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            connect[i] = new ArrayList<>();
            childrens[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N - 1; i++) { // 간선의 수 = 정점의 수 - 1
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            connect[U].add(V);
            connect[V].add(U);
        }

        makeTree(R, -1);
        countSubTreeNodes(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            sb.append(subTreeNodes[q]).append("\n");
        }

        System.out.print(sb);
    }

    private static void makeTree(int currentNode, int parent) {
        for (int node : connect[currentNode]) {
            if (node != parent) {
                childrens[currentNode].add(node);
                parents[node] = currentNode;
                makeTree(node, currentNode);
            }
        }
    }

    private static void countSubTreeNodes(int currentNode) {
        subTreeNodes[currentNode] = 1;

        for(int child : childrens[currentNode]) {
            countSubTreeNodes(child);
            subTreeNodes[currentNode] += subTreeNodes[child];
        }
    }
}
