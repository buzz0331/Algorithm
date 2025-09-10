import java.util.*;

class Solution {
    
    private int maxWinCount;
    private int[] answer;
    private int diceCount;
    
    private int[][] D;
    
    public int[] solution(int[][] dice) {
        D = dice;
        maxWinCount = -1; // A의 최대 승리 횟수
        
        diceCount = dice.length; // 주사위 갯수
        answer = new int[diceCount / 2];
        
        int[] A = new int[diceCount / 2];
        
        diceCombination(0, A, 0);
        
        for(int i = 0; i < answer.length; i++) {
            answer[i]++;
        }
        
        return answer;
    }
    
    private void diceCombination(int depth, int[] A, int nextIdx) {
        if(depth == diceCount / 2) {
            // 주사위 조합 완성
            simulate(A);
            return;
        }
        
        for (int i = nextIdx; i < diceCount; i++) {
            A[depth] = i;
            diceCombination(depth + 1, A, i + 1);
        }   
    }
    
    private void simulate(int[] A) { 
        // B 주사위 조합 구하기
        int[] B = new int[diceCount / 2];
        boolean[] isASelected = new boolean[diceCount];
        
        for (int A_idx : A) {
            isASelected[A_idx] = true;
        }
        
        int B_idx = 0;
        for (int i = 0; i < diceCount; i++) {
            if(!isASelected[i]) {
                B[B_idx++] = i;
            }
        }
        
         // 각 조합에서 나올 수 있는 눈금의 합과 횟수를 Map으로 구함
        Map<Integer, Integer> A_sums = new HashMap<>();
        getDiceSumsWithMap(A, 0, 0, A_sums);

        Map<Integer, Integer> B_sums = new HashMap<>();
        getDiceSumsWithMap(B, 0, 0, B_sums);
        
        int currentWinCount = 0;
        
        // A의 합을 순회하며 B의 합과 비교하여 승리 횟수 계산
        for (Map.Entry<Integer, Integer> aEntry : A_sums.entrySet()) {
            int aSum = aEntry.getKey();
            int aCount = aEntry.getValue();

            // B의 합을 순회하며 승리 조건 체크
            for (Map.Entry<Integer, Integer> bEntry : B_sums.entrySet()) {
                int bSum = bEntry.getKey();
                int bCount = bEntry.getValue();

                if (aSum > bSum) {
                    // A가 이길 경우, A의 횟수 * B의 횟수를 더함
                    currentWinCount += (aCount * bCount);
                }
            }
        }
        
        // 승리 횟수 갱신 - 기존과 동일
        if (currentWinCount > maxWinCount) {
            maxWinCount = currentWinCount;
            answer = Arrays.copyOf(A, A.length);
        }
    }
    
    private void getDiceSumsWithMap(int[] combinations, int depth, int sum, Map<Integer,Integer> sums) {
        if(depth == combinations.length) {
            sums.put(sum, sums.getOrDefault(sum, 0) + 1);
            return;
        }
        
        for(int diceValue : D[combinations[depth]]) {
            getDiceSumsWithMap(combinations, depth + 1, sum + diceValue, sums);
        }
    }
}