import java.util.*;
import java.io.*;

public class Main{
	
	private static int N, M;
	
	private static final int INF = 100_000_000;
	
	public static void main(String[] args) throws IOException {
		//------여기에 솔루션 코드를 작성하세요.------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		List<Edge>[] graph = new ArrayList[N + 1];
		List<Edge>[] reverseGraph = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			reverseGraph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Edge(b, cost));
			reverseGraph[b].add(new Edge(a, cost));
		}
		
		int[] dist = dijkstra(X, graph);
		int[] reverseDist = dijkstra(X, reverseGraph);
		
		int max = -1;
		for(int i = 1; i <= N; i++) {
			int to = dist[i];
			int from = reverseDist[i];
			
			if(to == INF || from == INF) continue; // 갔다가 되돌아오는 경로가 없는 경우
			max = Math.max(max, to + from);
		}
		
		System.out.print(max);
		
	}
	
	private static int[] dijkstra(int start, List<Edge>[] graph) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(start, 0));
		dist[start] = 0;

		while(!pq.isEmpty()) {
			Edge current = pq.poll();
			
			if(dist[current.to] < current.cost) continue;
			
			for(Edge next : graph[current.to]) {
				int newDist = dist[current.to] +  next.cost;
				if(dist[next.to] > newDist) {
					dist[next.to] = newDist;
					pq.offer(new Edge(next.to, newDist));
				}
			}
		}
		
		return dist;
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