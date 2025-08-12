import java.util.*;

public class Solution {
    public int solution(int[][] info, int n, int m) {
        int itemCount = info.length;
        
        // dp[i][a] = i번째 물건까지 처리했을 때 A의 흔적이 a개일 때 B의 최소 흔적 개수
        // -1이면 불가능한 상태
        int[][] dp = new int[itemCount + 1][n];
        
        // 초기화: 모든 상태를 불가능으로 설정
        for (int i = 0; i <= itemCount; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // 시작 상태: 0번째 물건까지(아무것도 안 훔침), A 흔적 0개, B 흔적 0개
        dp[0][0] = 0;
        
        // 각 물건에 대해 처리
        for (int i = 0; i < itemCount; i++) {
            int aTrace = info[i][0]; // A가 훔칠 때 남기는 흔적
            int bTrace = info[i][1]; // B가 훔칠 때 남기는 흔적
            
            for (int a = 0; a < n; a++) {
                if (dp[i][a] == -1) continue; // 불가능한 상태
                
                int currentB = dp[i][a];
                
                // 선택 1: A가 물건 i를 훔치는 경우
                int nextA = a + aTrace;
                if (nextA < n) { // A가 붙잡히지 않는 경우
                    if (dp[i + 1][nextA] == -1 || dp[i + 1][nextA] > currentB) {
                        dp[i + 1][nextA] = currentB;
                    }
                }
                
                // 선택 2: B가 물건 i를 훔치는 경우
                int nextB = currentB + bTrace;
                if (nextB < m) { // B가 붙잡히지 않는 경우
                    if (dp[i + 1][a] == -1 || dp[i + 1][a] > nextB) {
                        dp[i + 1][a] = nextB;
                    }
                }
            }
        }
        
        // 결과 찾기: A의 흔적이 최소인 경우
        for (int a = 0; a < n; a++) {
            if (dp[itemCount][a] != -1) {
                return a;
            }
        }
        
        return -1; // 불가능한 경우
    }
}