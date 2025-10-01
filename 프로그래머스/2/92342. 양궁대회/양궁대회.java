class Solution {
    
    private static int[] result = new int[11];
    private static int[] answer = {-1};

    private static int max = Integer.MIN_VALUE;
    
    public int[] solution(int n, int[] info) {
        
        dfs(0, n, info);
        
        if(max == -1) {
            answer = new int[1];
            answer[0] = -1;
        }
        
        return answer;
    }
    
    private static void dfs(int depth, int n, int[] info) { 
        if(depth == n) { //화살 수만큼 재귀
            int gap = getScoreGap(info);
            if(max <= gap) {
                max = gap;
                answer = result.clone();
            }
            return;
        }
        
        for(int i = 0; i < info.length; i++) {
            if(result[i] > info[i]) break;
            
            result[i] += 1;
            dfs(depth + 1, n , info);
            result[i] -= 1;
        }
    }
    
    private static int getScoreGap(int[] info) {
        int apeach = 0, lion = 0;
        
        for(int i = 0; i < result.length; i++) {
            if(info[i] == 0 && result[i] == 0) continue;
            if(info[i] >= result[i]) apeach += (10 - i);
            else lion += (10 - i);
        }
        
        int gap = lion - apeach;
        if(gap <= 0) return -1;
        return gap;
    }
}