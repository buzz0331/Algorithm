import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer l = new StringTokenizer(br.readLine()); //각 나라 사이 거리
        StringTokenizer p = new StringTokenizer(br.readLine()); //각 나라의 리터당 가격
        int price = Integer.parseInt(p.nextToken());

        int min_price = 0;  // 왼쪽 도시에서 제일 오른쪽 도시로 가는 최소 비용
        int length_sum = 0; // 지금까지 왔던 거리 저장하는 변수

        while (p.hasMoreTokens()) { //왼쪽에서 오른쪽으로 나라 이동
            length_sum += Integer.parseInt(l.nextToken());
            int nextPrice = Integer.parseInt(p.nextToken());

            if (nextPrice <= price) { //다음 나라의 리터당 가격이 더 작을 경우 -> (지금까지 왔던 거리 * 현재 price)
                min_price += length_sum * price;

                length_sum = 0;
                price = nextPrice;
            }

        }

        bw.write(String.valueOf(min_price));
        bw.flush();
    }
}
