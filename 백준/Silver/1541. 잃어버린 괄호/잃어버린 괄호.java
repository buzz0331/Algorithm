import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int sum = 0;

        //아예 처음부터 -가 없는 경우
        if (!line.contains("-")) {
            int dummy = getDummy(line);
            sum += dummy;
            System.out.println(sum);
            return;
        }

        StringTokenizer parentheses = new StringTokenizer(line, "-", true); //괄호단위
        boolean negative_flag = false;
        
        while (parentheses.hasMoreTokens()) {
            String s = parentheses.nextToken();

            if (s.equals("-")) {
                negative_flag = true;
                continue;
            }

            if (s.contains("+")) { //괄호 계산

                int dummy = getDummy(s); //+ 포함하는 괄호 안 구하는 메서드

                if (negative_flag) { //음수 뒤에 오는 괄호만 빼기
                    sum -= dummy;
                } else { //음수 뒤에 오지 않는 수는 더하기
                    sum += dummy;
                }

            } else { //괄호 없을때
                int abs = Integer.parseInt(s);
                if (negative_flag) { //음수일 경우
                    abs *= -1;
                }

                sum += abs;
                negative_flag = false;
            }


        }
        System.out.println(sum);
    }

    private static int getDummy(String s) {
        StringTokenizer st = new StringTokenizer(s, "+");
        int dummy = 0;
        while (st.hasMoreTokens()) {
            int i = Integer.parseInt(st.nextToken());
            dummy += i;
        }
        return dummy;
    }
}
