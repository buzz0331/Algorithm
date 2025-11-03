import java.util.*;

class Solution {
    
    int primeCount = 0;
    
    public int solution(int n, int k) {
        String num = Integer.toString(n, k);
        char[] charArray = num.toCharArray();
        // System.out.println(num);
        
        int startIdx = -1;
        int endIdx = charArray.length;
        for(int i = 0; i < charArray.length; i++) {
            if(startIdx == -1) {
                if(charArray[i] == '0') continue;
                startIdx = i;
            }
            if(charArray[i] == '0') {
                endIdx = i;
                // System.out.println("startIdx: " + startIdx);
                // System.out.println("endIdx: " + endIdx);
                long number = Long.parseLong(num.substring(startIdx, endIdx));
                checkAndAddPrimeNumber(number);
                startIdx = -1;
            } else if(i == charArray.length - 1) {
                endIdx = i + 1;
                long number = Long.parseLong(num.substring(startIdx, endIdx));
                checkAndAddPrimeNumber(number);
                startIdx = -1;
            }
        }
        
        return primeCount;
    }
    
    private void checkAndAddPrimeNumber(long number) {
        if(number == 1) return;
        if(number == 2) { primeCount++; return; }
        if(number % 2 == 0) return;
    
        for(long i = 3; i * i <= number; i += 2) {
            if(number % i == 0) return; // 나누어떨어지면 소수 x
        }
        
        
        primeCount++;
    }
}