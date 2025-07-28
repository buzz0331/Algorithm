import java.util.*;

class Solution {
    
    private Map<Integer, List<Time[]>> roomSchedule = new TreeMap<>();
    private int maxRoomNum = 0;
    
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));
        
        for(int i = 0; i < book_time.length; i++) {
            Time[] time = new Time[2];
            time[0] = new Time(book_time[i][0]);
            time[1] = new Time(book_time[i][1]);
            
            Integer findRoomNum = findRoom(time);
            if(findRoomNum == null) {
                List<Time[]> list = new ArrayList<>();
                list.add(time);
                roomSchedule.put(maxRoomNum, list);
                maxRoomNum++;
                // System.out.println("i 시작" + time[0].hour + ":" + time[0].minute);
                // System.out.println("i 종료" + time[1].hour + ":" + time[1].minute);
            } else {
                List<Time[]> list = roomSchedule.get(findRoomNum);
                list.add(time);
                roomSchedule.put(findRoomNum, list);
            }
        }
        return roomSchedule.size();
    }
    
    // 예약 가능한 방을 찾음
    private Integer findRoom(Time[] time) {
        for(Integer roomNum : roomSchedule.keySet()) {
            List<Time[]> bookList = roomSchedule.get(roomNum);
            if(canBook(time, bookList)) {
                return roomNum;
            }
        }
        return null; // 예약할 방이 없는 경우
    }
    
    // 현재 방에 예약 가능한지 확인
    private boolean canBook(Time[] time, List<Time[]> bookList) {
        for(Time[] schedule : bookList) {
            if(checkOverlapped(schedule, time)) {
                return false;
            }
        }
        return true;
    }
    
    // 겹치는 스케줄이 있는지 확인
    private boolean checkOverlapped(Time[] schedule, Time[] tryBookTime) {
        // 기존 예약의 종료 시간 + 10분
        int prevEnd = schedule[1].toMinutes() + 10;
        // 새로운 예약 시작 시간
        int newStart = tryBookTime[0].toMinutes();
        // 새로운 예약 종료 시간
        int newEnd = tryBookTime[1].toMinutes();
        // 기존 예약 시작 시간
        int prevStart = schedule[0].toMinutes();

        // 새 예약이 기존 예약보다 끝난 후 시작하면 겹치지 않음
        if (newStart >= prevEnd) return false;

        // 새 예약이 기존 예약 시작 전 끝나면 겹치지 않음
        if (newEnd <= prevStart - 10) return false;

        // 이외의 경우 겹침
        return true;
    }

    
    private class Time {
        public int hour;
        public int minute;
        
        public Time(String string) {
            StringTokenizer st = new StringTokenizer(string, ":");
            this.hour = Integer.parseInt(st.nextToken());
            this.minute = Integer.parseInt(st.nextToken());
        }
        
        public int toMinutes() {
            return hour * 60 + minute;
        }
    }
    
    
}