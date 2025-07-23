import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int right = Arrays.stream(diffs).max().getAsInt();
        int left = 1;
        int answer = 0;
    
        while(left <= right) {
            long playingTime = 0; //해당 level에서 총 퍼즐 풀이 시간
            int time_prev = 0;
            int mid = (left + right) / 2;
            
            for(int i = 0; i < diffs.length; i++) {
                int time_cur = times[i];
                
                if (diffs[i] <= mid) {
                    playingTime += time_cur;
                } else {
                    int wrongTime = diffs[i] - mid;
                    playingTime += wrongTime * (time_cur + time_prev) + time_cur;
                }
                
                if(playingTime > limit) break;
                
                time_prev = time_cur;
            }
            
            if(playingTime > limit) {
                //풀이 시간을 초과했으므로 level을 올려야됨
                left = mid + 1;
            }
            
            if(playingTime <= limit) {
                //풀이 시간을 통과했지만 최소 level을 찾기 위해 낮춰야됨
                answer = mid;
                right = mid - 1;
            }
            
        }
        
        return answer;
    }
}