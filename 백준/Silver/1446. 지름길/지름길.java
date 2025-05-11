import java.util.*;
import java.io.*;

public class Main {
    private static Road[] shortRoads;
    private static int N, D;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        //dfs로 풀면 안되나?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        shortRoads = new Road[N];
        dist = new int[D + 1];
        for(int i = 0; i < dist.length; i++){
            dist[i] = i;
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            shortRoads[i] = new Road(start, end, length);
        }

        int[] dist = dijkstra(0);
        System.out.println(dist[D]);
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(start, 0, 0));

        dist[start] = 0;

        while (!pq.isEmpty()) {
            Road current = pq.poll();
            int currentPos = current.end;

            for(Road r : shortRoads){
                if (currentPos <= r.start) {
                    if(r.end > D) //지름길의 끝이 고속도로 벗어나면 패스
                        continue;
                    if(dist[r.end] > dist[currentPos] + r.length + (r.start - currentPos)){
                        dist[r.end] = dist[currentPos] + r.length + (r.start - currentPos);
                        pq.offer(new Road(currentPos, r.end, dist[r.end]));
                    }
                }
            }

            dist[D] = Math.min(dist[D], D - currentPos + dist[currentPos]);

        }

        return dist;

    }

    private static class Road implements Comparable<Road> {
        public int start;
        public int end;
        public int length;

        Road(int start, int end, int length){
            this.start = start;
            this.end = end;
            this.length = length;
        }

        @Override
        public int compareTo(Road o) {
            return this.length - o.length;
        }
    }
}
