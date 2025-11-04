class Solution {
    public String solution(String new_id) {
        
        String one = new_id.toLowerCase();
        
        String two = one.replaceAll("[^a-z0-9._-]", "");
        
        String three = two.replaceAll("[.]{2,}", ".");
        
        String four = three.replaceAll("^[.]|[.]$", "");
        
        String five = four.isEmpty() ? "a" : four;
        
        String six = five;
        if(six.length() >= 16) {
            six = six.substring(0, 15).replaceAll("[.]$", "");
        }
        
        String answer = six;
    
        char lastLetter = answer.charAt(answer.length() - 1);
        while(answer.length() < 3) answer += lastLetter;
        
        return answer;
    }
}