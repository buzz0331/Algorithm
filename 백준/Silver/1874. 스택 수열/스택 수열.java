import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int prevPush = 0; //가장 마지막에 Stack에 넣은 숫자
    private static int prevPop;  //가장 마지막에 Stack에서 꺼낸 숫자

    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        while(n --> 0) {
            int x = Integer.parseInt(br.readLine());
            if(!makeSequence(x, stack)) {
                sb = new StringBuilder();
                sb.append("NO");
                break;
            }
        }

        bw.write(sb.toString());
        bw.flush(); bw.close();
    }

    private static boolean makeSequence(int x, Stack<Integer> stack) {
        for(int i = prevPush; i < x; i++) {
            stack.push(i + 1);
            prevPush++;
            sb.append("+").append("\n");
        }

        prevPop = stack.pop();
        if(prevPop != x) return false;
        sb.append("-").append("\n");

        return true;
    }
}
