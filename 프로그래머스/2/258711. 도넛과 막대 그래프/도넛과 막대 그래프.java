import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        // 노드의 갯수 구하기
        int nodeNum = 0;
        for (int i = 0; i < edges.length; i++) {
            nodeNum = Math.max(nodeNum, Math.max(edges[i][0], edges[i][1]));
        }
        
        int[] in = new int[nodeNum + 1];
        int[] out = new int[nodeNum + 1];
        
        for (int i = 0; i < edges.length; i++) {
            out[edges[i][0]]++;
            in[edges[i][1]]++;
        }
        
        for (int i = 1; i < nodeNum + 1; i++) {
            if(out[i] - in[i] > 1) answer[0] = i;
        }
        
        for (int i = 1; i < nodeNum + 1; i++) {
            if(i == answer[0]) continue;
            
            if(out[i] == 2) answer[3]++;
            if(in[i] > 0 && out[i] == 0) answer[2]++;
        }
        
        answer[1] = out[answer[0]] - answer[2] - answer[3];
        
        return answer;
    }
}