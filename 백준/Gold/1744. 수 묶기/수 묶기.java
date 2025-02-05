import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int sum = 0;

        ArrayList<Integer> negative = new ArrayList<>();
        ArrayList<Integer> positive = new ArrayList<>();    //1을 제외한 자연수
        ArrayList<Integer> one = new ArrayList<>();
        boolean hasZero = false;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                positive.add(num);
            } else if (num < 0) {
                negative.add(num);
            } else if (num == 0) {
                hasZero = true;
            } else {
                one.add(num);
            }
        }

        Collections.sort(negative);
        for (int i = 0; i < negative.size()-1; i += 2) {
            sum += negative.get(i) * negative.get(i + 1);
        }
        if (negative.size() % 2 != 0) {
            if (!hasZero) {     //음수의 개수가 홀수이고 현재 수열에 0이 없다면 음수 중 가장 큰 수를 더한다.
                sum += negative.get(negative.size() - 1);
            }
        }

        Collections.sort(positive, Comparator.reverseOrder());
        for (int i = 0; i < positive.size()-1; i += 2) {
            sum += positive.get(i) * positive.get(i + 1);
        }
        if (positive.size() % 2 != 0) {
            sum += positive.get(positive.size() - 1);
        }

        for (Integer integer : one) {
            sum += integer;
        }

        System.out.println(sum);
    }
}
