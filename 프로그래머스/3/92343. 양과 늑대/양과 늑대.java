class Solution {
    
    private static int[] INFO;
    private static int[][] EDGES;
    private static int answer;
    
    public int solution(int[] info, int[][] edges) {
        INFO = info;
        EDGES = edges;
        boolean[] visited = new boolean[info.length];
        dfs(0, visited, 0, 0);
        return answer;
    }
    
    private void dfs(int index, boolean[] visited, 
                     int sheepCount, int wolfCount) {
        visited[index] = true;
        if(INFO[index] == 0) {
            sheepCount++;
            if(sheepCount > answer) {
                answer = sheepCount;
            }
        } else {
            wolfCount++;
        }
        
        if (sheepCount <= wolfCount) return;
        
        for (int[] edge : EDGES) {
            if(visited[edge[0]] && !visited[edge[1]]) {
                boolean[] nextVisited = visited.clone();
                dfs(edge[1], nextVisited, sheepCount, wolfCount);
            }
        }
    }
}