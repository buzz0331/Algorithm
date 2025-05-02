import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final Map<Character, Integer> map = Map.of(
            'q', 1,
            'u', 2,
            'a', 3,
            'c', 4,
            'k', 5);
    private static final ArrayList<Integer> ducks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        boolean isSuccess = true;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] alphabets = br.readLine().toCharArray();

        for (char alphabet : alphabets) {
            switch(alphabet) {
                case 'q':
                    if (checkDucks('k', 'q')) {
                        continue;
                    } else {
                        ducks.add(map.get('q'));
                        break;
                    }
                case 'u':
                    if (checkDucks('q', 'u')) {
                        continue;
                    }
                    isSuccess = false;
                    break;

                case 'a':
                    if (checkDucks('u', 'a')) {
                        continue;
                    }
                    isSuccess = false;
                    break;
                case 'c':
                    if (checkDucks('a', 'c')) {
                        continue;
                    }
                    isSuccess = false;
                    break;
                case 'k':
                    if (checkDucks('c', 'k')) {
                        continue;
                    }
                    isSuccess = false;
                    break;
            }
        }

        for (Integer duck : ducks) {
            if (duck != map.get('k')) {
                isSuccess = false;
            }
        }

        if (isSuccess) {
            System.out.println(ducks.size());
        } else {
            System.out.println(-1);
        }


    }

    private static boolean checkDucks(char prev, char curr) {
        for (int i = 0; i < ducks.size(); i++) {
            if (ducks.get(i) == map.get(prev)) {
                ducks.set(i, map.get(curr));
                return true;
            }
        }
        return false;
    }
}
