import java.util.*;
import java.io.*;

public class Main {
	
	private static List<Edge>[] graph;
	private static int n, m;
	
	private static final int INF = 100000000;
	private static int[] dist;
	private static int[] preCity;
	private static int count = 1;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[n + 1];
		preCity = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Edge(b, c));
		}
		
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int start = Integer.parseInt(st.nextToken());
		int end	= Integer.parseInt(st.nextToken());
		sb.append(dijkstra(start, end)).append("\n");
		
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		stack.push(end);
		while(preCity[end] != 0) {
			count++;
			stack.push(preCity[end]);
			end = preCity[end];
		}

		sb.append(count).append("\n");
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}

		
		System.out.print(sb);
	}
	
	private static int dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist = new int[n + 1];
		
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge current = pq.poll();
			
			if(dist[current.to] < current.cost) continue; 
			
			for(Edge next : graph[current.to]) {
				int newDist = dist[current.to] + next.cost;
				
				if(dist[next.to] > newDist) {
					dist[next.to] = newDist;
					preCity[next.to] = current.to;
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
