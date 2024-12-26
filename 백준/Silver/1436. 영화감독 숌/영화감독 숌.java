import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int num = 666;
        int count = 1;
        String s = Integer.toString(num);
        while (N != count) {

            num ++;
//            System.out.println(num);
            s = Integer.toString(num);
            if (s.contains("666")) {
                count++;
            }

        }
        bw.write(s);
        bw.flush();

    }
}
