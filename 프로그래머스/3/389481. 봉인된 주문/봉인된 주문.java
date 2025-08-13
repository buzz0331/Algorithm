import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        String result = "";
    
        long currentValue;
        long[] bannedValues = new long[bans.length];
        for (int idx = 0; idx < bans.length; idx++) {
            bannedValues[idx] = convertStrToNum(bans[idx]);
        }
        
        Arrays.sort(bannedValues);
        for (long bannedNum : bannedValues) {
            if (bannedNum <= n) {
                n++;
            }
        }
        
        result = convertNumToStr(n);
        
        return result;
    }

    private long convertStrToNum(String input) {
        int strLength = input.length();
        long num = 0;

        for (int pos = 0; pos < strLength; pos++) {
            num += (input.charAt(pos) - 96) * Math.pow(26, (strLength - 1 - pos));
        }

        return num;    
    }

    private String convertNumToStr(long value) {
        String output = "";

        while (value > 0) {
            output = String.valueOf((char) ((value - 1) % 26 + 1 + 96)) + output;
            value = (value - 1) / 26;
        }

        return output;
    }
}
