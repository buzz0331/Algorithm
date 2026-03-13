import java.util.*;
import java.io.*;

public class Main {
	
	private static List<Edge>[] adj;
	private static int N, E;
	private static final int INF = 200_000_000;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Edge(b, c));
			adj[b].add(new Edge(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
				
		int sum1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
		int sum2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
		
		int ans = (sum1 >= INF && sum2 >= INF) ? -1 : Math.min(sum1, sum2);
		System.out.println(ans);
		
	}
	
	private static int dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge current = pq.poll();
			
			if(dist[current.to] >= INF) continue;
			
			for(Edge next : adj[current.to]) {
				int newDist = dist[current.to] + next.cost;
				if(dist[next.to] > newDist) {
					dist[next.to] = newDist;
					pq.offer(new Edge(next.to, newDist));
				}
			}
		}
		
		return dist[end];
	}
		
	private static class Edge implements Comparable<Edge> {
		public int to, cost;
		
		Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

}
