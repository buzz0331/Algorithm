import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 1. 시작 시간 기준으로 정렬
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (String[] time : book_time) {
            int start = toMinutes(time[0]);
            int end = toMinutes(time[1]) + 10; // 청소 10분 추가
            
            // 2. 가장 빨리 끝나는 방 확인
            if (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll(); // 기존 방 재사용
            }
            
            pq.add(end); // 현재 예약 종료 시간 삽입
        }
        
        return pq.size();
    }

    private int toMinutes(String t) {
        String[] parts = t.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}