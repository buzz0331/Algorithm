import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Parent[] parents;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(t --> 0) {
            int F = Integer.parseInt(br.readLine());
            Map<String, Integer> indexMap = new HashMap<>();
            int indexCount = 0;
            parents = new Parent[F * 2];

            for(int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                indexCount = init(indexMap, f1, indexCount);
                indexCount = init(indexMap, f2, indexCount);

                union(indexMap.get(f1), indexMap.get(f2));
                Parent parent = find(indexMap.get(f1));

                sb.append(parent.count).append("\n");
            }
        }
        System.out.print(sb);
    }

    // indexMap 및 parents 배열 초기화
    private static int init(Map<String, Integer> indexMap, String friend, int indexCount) {
        if(!indexMap.containsKey(friend)) {
            parents[indexCount] = new Parent(indexCount, 1);
            indexMap.put(friend, indexCount);
            indexCount++;
        }
        return indexCount;
    }

    private static Parent find(int x) {
        if(parents[x].root == x) return parents[x];

        Parent parent = find(parents[x].root);
        parents[x].updateRoot(parent);
        return parent;
    }

    private static void union(int x, int y) {
        Parent parentX = find(x);
        Parent parentY = find(y);

        if(parentX.root < parentY.root) {
            parents[parentY.root].updateRoot(parentX); // y의 부모를 x의 부모로 변경
            parents[parentX.root].mergeNetwork(parentY); // y의 네트워크 -> x의 네트워크로 병합
        } else if(parentX.root > parentY.root) {
            parents[parentX.root].updateRoot(parentY); // x의 부모를 y의 부모로 변경
            parents[parentY.root].mergeNetwork(parentX); // x의 네트워크 -> y의 네트워크로 병합
        }
    }

    private static class Parent {
        public int root;
        public int count; // 친구 네트워크 수

        Parent(int root, int count) {
            this.root = root;
            this.count = count;
        }

        public void updateRoot(Parent o) {
            this.root = o.root;
        }

        public void mergeNetwork(Parent o) {
            this.count += o.count;
        }
    }
}
