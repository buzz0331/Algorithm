import java.util.*;

/*
[백준] 1707번 - 이분 그래프 (Java)
*/

public class Main {
    static int K, V, E;
    static ArrayList<Integer> list[];
    static int visited[];
    static String result = "";

    static boolean DFS(int node, int color) {
        visited[node] = color;

        for (int v : list[node]) {
            //System.out.println("list["+node+"] -> "+v+", color= "+color);
            if (visited[v] == 0) {
                DFS(v, color*-1);
            } else if (visited[v] == visited[node]) {
                result = "NO";
                return false;
            }
            if (result.equals("NO")) {
                return false;
            }
        }
        result = "YES";
        return true;
    }

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        K = in.nextInt();
        for (int i=0; i<K; i++) {
            result = "";// 결과값 초기화
            V = in.nextInt();
            E = in.nextInt();
            list = new ArrayList[V+1];
            for (int j=1; j<=V; j++) {
                list[j] = new ArrayList<>();
            }
            visited = new int[V+1];
            for (int k=1; k<=E; k++) {
                int s = in.nextInt();
                int e = in.nextInt();
                list[s].add(e);
                list[e].add(s);
            }
            boolean ans = true;
            for (int t=1; t<=V; t++) {
                if (visited[t] == 0) {
                    ans = DFS(t, 1);
                }
                if (ans == false) break;//그래프가 여러개일 때 하나라도 이분그래프가 아니면 중지
            }
            System.out.println(result);
        }
	}
}