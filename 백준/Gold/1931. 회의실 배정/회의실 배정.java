import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        List<Meeting> meetings = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            Meeting meeting = new Meeting(start, end);
            meetings.add(meeting);
        }

        Collections.sort(meetings);   //끝나는 시간으로 정렬

        int count = 0;
        Meeting meeting = meetings.get(0);
        int end = meeting.end;
        count++;

        for (int i = 1; i < N; i++) {
            Meeting nextMeeting = meetings.get(i);
            if (end <= nextMeeting.start) {     //가능한 회의 바로 채택
                end = nextMeeting.end;
                count++;
            }
        }

        bw.write(count + "");
        bw.flush();
    }

    private static class Meeting implements Comparable<Meeting> {
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting m) {
//            if (this.end < m.end) {
//                return -1;
//            } else if (this.end > m.end) {
//                return 1;
//            } else {
//                return 0;
//            }
            if (this.end == m.end) {
                return this.start - m.start;    
            }

            return this.end - m.end;
            
        }
    }
}
