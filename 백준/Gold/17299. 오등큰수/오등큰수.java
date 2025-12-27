import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] a;
    private static int[] answer;
    private static Map<Integer, Integer> map = new HashMap<>();
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        a = new int[N];
        answer = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }

        Arrays.fill(answer, -1);
        stack.push(a[N - 1]);
        for(int i = N - 2; i >= 0; i--) {
            while (!stack.isEmpty()) {
                Integer peek = stack.peek();
                if(map.get(peek) > map.get(a[i])) { // 오등큰수 찾은 경우
                    answer[i] = peek;
                    break;
                } else { // 오등큰수가 아닌 경우
                    stack.pop();
                }
            }
            stack.push(a[i]);
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(answer).forEach(a -> sb.append(a).append(" "));
        System.out.print(sb);
    }
}
