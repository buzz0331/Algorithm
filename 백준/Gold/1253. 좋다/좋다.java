//import java.util.*;
//import java.io.*;
//
//public class Main {
//
//    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//    public static void main(String[] args) throws IOException {
//        int N = Integer.parseInt(br.readLine());
//
//        int[] numbers = new int[N]; // 지금까지 나온 수를 보관
//        Map<Integer, Integer> goodMap = new HashMap<>(); // 나온 수 갯수 보관
//        Set<Integer> goodNum = new HashSet<>(); // 좋은 수 집합
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        for(int i = 0; i < N; i++) {
//            int num = Integer.parseInt(st.nextToken());
//            numbers[i] = num;
//            goodMap.put(num, goodMap.getOrDefault(num, 0) + 1);
//        }
//
//        for(int i = 0; i < N; i++) {
//            for(int j = 0; j < i; j++) {
//                int sum = numbers[i] + numbers[j];
//
//                if (numbers[i] == 0 && numbers[j] == 0) {
//                    if (goodMap.getOrDefault(sum, 0) >= 3) goodNum.add(sum); //둘다 0인 경우는 3개 인상인 경우에만 가능
//                } else if (numbers[i] == 0 || numbers[j] == 0) {
//                    if (goodMap.getOrDefault(sum, 0) >= 2) goodNum.add(sum); //둘 중 하나만 0인경우에는 0이 2개 이상인 경우에만 가능
//                } else {
//                    goodNum.add(sum);
//                }
//
//
//            }
//        }
//
//        long answer = Arrays.stream(numbers).filter(goodNum::contains).count();
//
//        System.out.print(answer);
//    }
//}

import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] numbers = new int[N]; // 지금까지 나온 수를 보관

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        int answer = 0;

        for(int i = 0; i < N; i++) {
            if(binarySearch(i, numbers)) answer++;
        }

        System.out.print(answer);
    }

    private static boolean binarySearch(int idx, int[] numbers) {
        int start = 0;
        int end = numbers.length - 1;
        while(start < end) {
            if(start == idx || end == idx) { // 자기 자신인 경우
                if(start == idx) start++;
                else end--;
            } else {
                int now = numbers[start] + numbers[end];
                if(now == numbers[idx]) {
                    return true; // 찾은 경우
                }

                if(now < numbers[idx]) { // 더 큰 숫자가 필요한 경우
                    start++;
                } else { // 더 작은 숫자가 필요한 경우
                    end--;
                }

            }
        }

        return false;
    }
}
