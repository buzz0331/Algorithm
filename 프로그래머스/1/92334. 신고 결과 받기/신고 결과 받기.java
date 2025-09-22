import java.util.*;

class Solution {
    
    private static int K;
    
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        K = k;
        
        Map<String, ReportInfo> reportMap = new HashMap<>();
        
        for(String r : report) {
            StringTokenizer st = new StringTokenizer(r);
            String reporter = st.nextToken(); // 신고자
            String reportee = st.nextToken(); // 신고당한 사람
            
            reportMap.put(reportee, reportMap
                          .getOrDefault(reportee, new ReportInfo()).addReporter(reporter));
        }
        
        // 이용자, 정지시킨 사람 명수
        Map<String, Integer> answerMap = new HashMap<>();
            
        // 이용자별로 정지시킨 사람 명수 계산
        for(String reportee : reportMap.keySet()) {
            ReportInfo reportInfo = reportMap.get(reportee);
            
            //정지된 사람이 아니면 넘어감
            if(!reportInfo.isStopped) continue;
            
            Set<String> reporters = reportInfo.reporters;
            for(String reporter : reporters) {
                answerMap.put(reporter, answerMap.getOrDefault(reporter, 0) + 1);
            }
        }
        
        for(int i = 0; i < answer.length; i++) {
            answer[i] = answerMap.getOrDefault(id_list[i], 0);
        }
        return answer;
    }
    
    class ReportInfo {
        public Set<String> reporters; // 신고한 사람
        public int reportCount; // 신고당한 횟수
        public boolean isStopped; // 정지 여부
            
        public ReportInfo() {
            reporters = new HashSet<>();
            reportCount = 0;
            isStopped = false;
        }
        
        public ReportInfo addReporter(String reporter) {
            if(reporters.contains(reporter)) {
                return this;
            }
            reporters.add(reporter);
            reportCount++;
            checkStopped();
            return this;
        }
        
        public void checkStopped() {
            if(reportCount >= K) {
                isStopped = true;
            }
        }
            
    }
}