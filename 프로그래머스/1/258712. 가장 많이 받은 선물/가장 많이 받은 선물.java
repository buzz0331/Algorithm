import java.util.*;

class Solution {
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        int N = friends.length;
        int[][] gift = new int[N][N];
        int[] present = new int[N];
        
        Map<String, Integer> pIdx = new HashMap<>(); // 이름, idx
        for(int i = 0; i < N; i++) {
            pIdx.put(friends[i], i);
        }
        
        for(int i = 0; i < gifts.length; i++) {
            StringTokenizer st = new StringTokenizer(gifts[i]);
            String sender = st.nextToken();
            String receiver = st.nextToken();
            
            gift[pIdx.get(sender)][pIdx.get(receiver)]++;
        }
        
        for (int i = 0; i < N; i++) {
            present[i] = calculateJisoo(i, gift);
        }
        
        // 다음달 선물 받는 횟수
        int[] receiverCount = new int[N];
        
        for(int i = 0; i < N - 1; i++) {
            for(int j = i; j < N; j++) {
                int giftGap = gift[i][j] - gift[j][i];
                
                if(giftGap > 0) {
                    receiverCount[i]++;
                } else if (giftGap == 0) {
                    
                    if(present[i] > present[j]) {
                        receiverCount[i]++;
                    }
                    
                    if(present[i] < present[j]) {
                        receiverCount[j]++;
                    }
                    
                } else {
                    receiverCount[j]++;
                }
            }
        }
        
        return Arrays.stream(receiverCount).max().getAsInt();
    }
    
    // 선물 지수 계산
    private int calculateJisoo(int index, int[][] gift) {
        int sendCount = 0, receiverCount = 0;
        
        for(int i = 0; i < gift.length; i++) {
            if(i == index) continue;
            
            sendCount += gift[index][i];
            receiverCount += gift[i][index];
        }
        
        return sendCount - receiverCount;
    }
}