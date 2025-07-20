import java.util.*;
import java.util.stream.Collectors;

class Solution {
    int answer = 0;
    int N;
    
    List<Integer> code = new ArrayList<>();
    int[][] Q;
    int[] ANS;
    
    public int solution(int n, int[][] q, int[] ans) {
        N = n;
        ANS = ans;
        Q = q;
        
        backtracking(0, 0);
        
        return answer;
    }
    
    private void backtracking(int prev, int depth) {
        if(depth == 5) {
            // System.out.println(code[0] + " " + code[1] + " " + code[2] + " "+ code[3] + " "+ code[4] + " ");
            // 조합 완성
            if(isCorrectSecretCode()) {
                answer++;
            }
            return;
        }
        
        for(int i = prev + 1; i <= N - (4 - depth); i++) {
            code.add(depth, i);
            backtracking(i, depth + 1);
            code.remove(depth);
        }
    }
    
    private boolean isCorrectSecretCode() {
        // List<Integer> codeList = Arrays.stream(code).boxed().collect(Collectors.toList());
        
        for(int i = 0; i < Q.length; i++) {
            int[] inputCode = Q[i];
            int answerCount = 0;
            for(int j = 0; j < 5; j++) {
                if(code.contains(inputCode[j])) {
                    answerCount++;
                }
            }
            
            if (answerCount != ANS[i]) return false;
        }
        return true;
    }
}