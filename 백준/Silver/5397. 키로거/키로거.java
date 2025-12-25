import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while(T --> 0) {
            Stack<Character> s1 = new Stack<>();
            Stack<Character> s2 = new Stack<>();

            char[] op = br.readLine().toCharArray();
            for(int i = 0; i < op.length; i++) {

                switch(op[i]) {

                    case '<':
                        if(!s1.isEmpty()) s2.push(s1.pop());
                        break;
                    case '>':
                        if(!s2.isEmpty()) s1.push(s2.pop());
                        break;
                    case '-':
                        if(!s1.isEmpty()) s1.pop();
                        break;
                    default:
                        s1.push(op[i]);
                }

            }

            StringBuilder sb = new StringBuilder();
            while(!s1.isEmpty()) {
                sb.append(s1.pop());
            }
            sb.reverse();
            while(!s2.isEmpty()) {
                sb.append(s2.pop());
            }

            System.out.println(sb);
        }
    }
}
