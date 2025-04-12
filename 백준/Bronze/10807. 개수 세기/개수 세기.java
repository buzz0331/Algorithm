import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // 정수의 개수
        String[] nums = br.readLine().split(" "); // 정수들
        int v = Integer.parseInt(br.readLine()); // 찾으려는 정수
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (Integer.parseInt(nums[i]) == v) {
                count++;
            }
        }

        System.out.println(count);
    }
}
