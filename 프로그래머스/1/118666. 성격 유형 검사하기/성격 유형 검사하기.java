import java.util.*;

class Solution {
    
    private static final String[] characters = {
        "R", "T", "C", "F", "J", "M", "A", "N"
    };
    
    private static final Map<String, Integer> scoreMap = new HashMap<>();
    
    public String solution(String[] survey, int[] choices) {
        
        for(String character : characters) {
            scoreMap.put(character, 0);
        }
        
        for(int i = 0; i < choices.length; i++) {
            String[] s = survey[i].split("");
            
            calScore(choices[i], s);
        }
        
        return getResult();
    }
    
    private void calScore(int choice, String[] split) {
        if(choice <= 3) {
            scoreMap.put(split[0], scoreMap.get(split[0]) + 4 - choice);
        } else if (choice == 4) {
            return;
        }
        else {
            scoreMap.put(split[1], scoreMap.get(split[1]) + choice - 4);
        }
    }
    
    private String getResult() {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < characters.length; i += 2) {
            if(scoreMap.get(characters[i]) < scoreMap.get(characters[i + 1])) {
                sb.append(characters[i + 1]);
            } else {
                sb.append(characters[i]);
            }
        }
        return sb.toString();
    }
}