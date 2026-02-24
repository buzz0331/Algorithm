import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, ItemInfo> itemMap = new HashMap<>();
        int visitCnt = 0;

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            ItemInfo aItem = itemMap.getOrDefault(a, new ItemInfo());
            ItemInfo bItem = itemMap.getOrDefault(b, new ItemInfo());

            aItem.adjacent.add(b);
            itemMap.put(a, aItem);
            itemMap.put(b, bItem);
            bItem.indegree++;
        }

        PriorityQueue<String> pq = new PriorityQueue<>();
        for(String key : itemMap.keySet()) {
            ItemInfo info = itemMap.get(key);
            if(info.indegree == 0) pq.offer(key);
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            int size = pq.size();
            List<String> list = new ArrayList<>();

            for(int i = 0; i < size; i++) {
                String current = pq.poll();
                visitCnt++;
                ItemInfo currentItem = itemMap.get(current);
                sb.append(current).append("\n");

                for(String adj : currentItem.adjacent) {
                    ItemInfo nextItem = itemMap.get(adj);

                    if(--nextItem.indegree == 0) {
                        list.add(adj);
                    } else {
                        itemMap.put(adj, nextItem);
                    }
                }
            }

            for(String s : list) {
                pq.offer(s);
            }
        }

        if(visitCnt == itemMap.size()) {
            System.out.print(sb);
        } else {
            System.out.print(-1);
        }
    }

    private static class ItemInfo {
        public List<String> adjacent;
        public int indegree;

        ItemInfo() {
            adjacent = new ArrayList<>();
            indegree = 0;
        }
    }
}
