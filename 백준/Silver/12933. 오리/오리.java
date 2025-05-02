import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean isSuccess = true;
        int[] stage = new int [5]; // 'q', 'u', 'a', 'c', 'k'
        int currentDucks = 0;   //현재 울고 있는 오리
        int maxDucks = 0; //동시에 울 수 있는 최대 오리 수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] alphabets = br.readLine().toCharArray();

        for (char alphabet : alphabets) {
            int idx = "quack".indexOf(alphabet);
            if (idx == -1) {
                isSuccess = false;
                break;
            }

            if (idx == 0) {
                stage[0]++;
                currentDucks++;
                maxDucks = Math.max(maxDucks, currentDucks);
            } else {
                if (stage[idx - 1] == 0) {
                    isSuccess = false;
                    break;
                }
                stage[idx - 1]--;
                stage[idx]++;
                if (idx == 4) {
                    currentDucks--;
                    stage[idx]--;
                }
            }
        }

        for (int s : stage) {
            if (s != 0) {
                isSuccess = false;
                break;
            }
        }

        System.out.println(isSuccess? maxDucks : -1);


    }


}
