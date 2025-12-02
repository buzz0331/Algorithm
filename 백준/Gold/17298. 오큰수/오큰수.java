import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        int[] answer = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = N - 1; i >= 0; i--) {
            int currentNum = num[i];

            while (!stack.isEmpty() && stack.peek() <= currentNum) {
                stack.pop();
            }

            if(stack.isEmpty()) {
                answer[i] = -1;
                stack.push(currentNum);
                continue;
            }

            Integer nge = stack.pop();
            answer[i] = nge;

            stack.push(nge);
            stack.push(currentNum);
        }

        Arrays.stream(answer).forEach(a -> sb.append(a).append(" "));
        bw.write(sb.toString());
        bw.flush();
    }
}
