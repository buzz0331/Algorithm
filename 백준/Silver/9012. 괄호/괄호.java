import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while(T --> 0) {
            if (isVPS(br.readLine().toCharArray())) {
                sb.append("YES").append("\n");
                continue;
            }
            sb.append("NO").append("\n");
        }

        bw.write(sb.toString());
        bw.flush(); bw.close();
    }

    private static boolean isVPS(char[] parenthesis) {
        //1.'('는 그냥 스택에 넣는다.
        //2. ')'는 스택에서 '('가 나오는지 확인
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < parenthesis.length; i++) {
            if(parenthesis[i] == '(') {
                stack.push('(');
            } else {
                if(stack.isEmpty()) return false;

                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
