import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        String bomb = br.readLine();
        int bombLength = bomb.length();

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
            if (stack.size() >= bombLength) {   //Stack이 폭발 문자열의 길이와 같아지면 폭발 탐색
                boolean isBomb = true;

                for(int j = 0; j < bombLength; j++) {
                    if(stack.get(stack.size() - bombLength + j) != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }

                if(isBomb) {
                    for (int j = 0; j < bombLength; j++) {
                        stack.pop();
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            bw.write("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Character c : stack) sb.append(c);
            bw.write(sb.toString());
        }

        bw.flush(); bw.close();
    }
}
