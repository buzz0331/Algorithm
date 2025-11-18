import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		StringBuilder sb= new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{	
			StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 정점 갯수
            int E = Integer.parseInt(st.nextToken()); // 간선 갯수
            List<Node>[] nodes = new ArrayList[V + 1];
            for(int i = 1; i < V + 1; i++) {
                nodes[i] = new ArrayList<>();
            }
            
            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
				nodes[x].add(new Node(y, cost));
                nodes[y].add(new Node(x, cost));
            }
            
            long total = prim(nodes, V, 1);
            sb.append("#" + test_case).append(" " + total).append("\n");
		}
        System.out.println(sb.toString());
	}
    
    private static long prim(List<Node>[] nodes, int V, int start) {
		Queue<Node> pq = new PriorityQueue<>();
        long total = 0;
		boolean[] visited = new boolean[V + 1];
		pq.offer(new Node(start, 0));
        
		while(!pq.isEmpty()) {
            Node currentNode = pq.poll();
            
            if(visited[currentNode.to]) continue;
            
            visited[currentNode.to] = true;
            total += currentNode.cost;
            
            for(Node next : nodes[currentNode.to]) {
                if(!visited[next.to]) pq.offer(next);
            }
        }

        return total;
    }
    
    private static class Node implements Comparable<Node>  {
        public int to;
        public int cost;
        
        public Node(int to, int cost) {
			this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
        
    }
}