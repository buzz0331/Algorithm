import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());       //Test case 수
        for (int i = 0; i < T; i++) {       //Test case 시작
            int N = Integer.parseInt(br.readLine());
            List<Applicant> applicants = new ArrayList<>(N);
            int min_interview = N;

            int count = 1;

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int documentRank = Integer.parseInt(st.nextToken());
                int interviewRank = Integer.parseInt(st.nextToken());
                Applicant applicant = new Applicant(documentRank, interviewRank);
                applicants.add(applicant);
            }

            Collections.sort(applicants);
            min_interview = applicants.get(0).interviewRank;

            for (int j = 1; j < N; j++) {
                Applicant applicant = applicants.get(j);
                if (applicant.interviewRank < min_interview) {
                    min_interview = applicant.interviewRank;
                    count++;
                }
            }

            sb.append(count + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static class Applicant implements Comparable<Applicant> {
        public int documentRank;
        public int interviewRank;

        public Applicant(int documentRank, int interviewRank) {
            this.documentRank = documentRank;
            this.interviewRank = interviewRank;
        }

        @Override
        public int compareTo(Applicant o) {
            return this.documentRank - o.documentRank;
        }
    }
}
