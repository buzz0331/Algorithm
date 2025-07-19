import java.util.*;
import java.io.*;

class Solution {
    
    private Map<Integer, Integer> map = new HashMap<>();
    
    public int solution(int[] players, int m, int k) {
        int currentServerCount = 0; // 현재 증설된 서버 수
        int answer = 0; // 누적 증설 횟수
        
        for(int i = 0; i < players.length; i++) {
            int needServers = players[i] / m; // 필요한 서버 수 (내림)
            
            if(needServers > currentServerCount) {
                // 서버 추가 증설 필요
                int increaseServers = needServers - currentServerCount;
                currentServerCount += increaseServers;
                map.put(i - 1 + k, increaseServers);
                
                answer += increaseServers;
            }
            
            
            currentServerCount -= map.getOrDefault(i, 0);
        }
        
        return answer;
    }
}