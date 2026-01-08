import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Number[] A;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        A = new Number[N];
        
        for(int i = 0; i < N; i++) {
            A[i] = new Number(Integer.parseInt(br.readLine()), i);
        }

        Arrays.sort(A);
        int sub = -1;
        for(int i = 0; i < N; i++) {
            int x = A[i].index - i;
            sub = Math.max(sub, x);
        }

        System.out.print(sub + 1);
    }

    private static class Number implements Comparable<Number> {
        public int num;
        public int index;

        public Number(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public int compareTo(Number o) {
            return Integer.compare(this.num, o.num);
        }
    }


}
