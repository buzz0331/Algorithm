import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int test_case = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(test_case --> 0) {
            int T = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();

            for(int i = 0; i < T; i++) {
                st = new StringTokenizer(br.readLine());

                String op = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if (Objects.equals(op, "I")) {
                    treeMap.put(n, treeMap.getOrDefault(n, 0) + 1);
                } else {
                    if(treeMap.isEmpty()) continue;

                    if(n == 1) { // 최댓값 삭제
                        Integer maxKey = treeMap.lastKey();
                        delete(treeMap, maxKey);

                    } else { // 최솟값 삭제
                        Integer minKey = treeMap.firstKey();
                        delete(treeMap, minKey);
                    }
                }
            }

            if(treeMap.isEmpty()) sb.append("EMPTY").append("\n");
            else sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
        }

        System.out.print(sb);
    }

    private static void delete(TreeMap<Integer, Integer> treeMap, Integer key) {
        Integer count = treeMap.get(key);
        if(count == 1) {
            treeMap.remove(key);
        } else {
            treeMap.put(key, --count);
        }
    }
}
