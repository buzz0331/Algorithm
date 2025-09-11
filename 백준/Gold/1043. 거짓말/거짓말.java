import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static boolean[] knowTrue;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사람 수
        int M = Integer.parseInt(st.nextToken()); // 파티 수

        knowTrue = new boolean[N + 1]; // 진실을 알고있는지 여부

        st = new StringTokenizer(br.readLine());
        int truePeopleNum = Integer.parseInt(st.nextToken());

        for(int i = 0; i < truePeopleNum; i++) {
            knowTrue[Integer.parseInt(st.nextToken())] = true;
        }

        Map<Integer, List<Integer>> party = new HashMap<>();

        for(int i = 1; i < M + 1; i++) {
            boolean hasKnowTruePeople = false;
            ArrayList<Integer> partyParticipants = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int partyParticipantNum = Integer.parseInt(st.nextToken()); // 파티 참여자 수

            while(partyParticipantNum -- > 0) {
                int partyParticipant = Integer.parseInt(st.nextToken());
                if(!hasKnowTruePeople && knowTrue[partyParticipant]) {
                    hasKnowTruePeople = true;
                }
                partyParticipants.add(partyParticipant);
            }

            if(hasKnowTruePeople) {
                for(Integer p : partyParticipants) {
                    knowTrue[p] = true;
                }
            } else { // 진실을 알고있는 사람이 존재하는 파티는 Map에서 제외
                party.put(i, partyParticipants);
            }
        }

        updateKnowTrue(party);
        System.out.println(party.size());
    }

    private static void updateKnowTrue(Map<Integer, List<Integer>> party) {
        int updateCount = 1;
        while(updateCount > 0) { // 갱신이 존재할 때까지 반복
            updateCount = 0;
            Map<Integer, List<Integer>> copyParty = Map.copyOf(party);
            
            for(Integer partyNum : copyParty.keySet()) {
                boolean hasKnowTruePeople = false;
                List<Integer> partyParticipants = copyParty.get(partyNum);

                for(Integer p : partyParticipants) {
                    if (knowTrue[p]) {
                        hasKnowTruePeople = true;
                        break;
                    }
                }

                if(hasKnowTruePeople) {
                    for(Integer p : partyParticipants) {
                        knowTrue[p] = true;
                    }
                    updateCount++;
                    party.remove(partyNum); // 파티를 Map에서 제거
                }
            }
        }
    }
}
