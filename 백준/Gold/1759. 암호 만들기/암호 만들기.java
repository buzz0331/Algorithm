import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final char[] vowel = {'a', 'e', 'i', 'o', 'u'}; //모음
    private static final int MIN_VOWEL = 1;
    private static final int MIN_CONSONANT = 2;
    private static char[] alphabets;
    private static int L, C;
    private static StringBuilder sb;
    private static int vowel_count, consonant_count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alphabets = br.readLine().replace(" ", "").toCharArray();
        Arrays.sort(alphabets);

        //L개 중에 C개를 고름
        for (int i = 0; i < C; i++) {
            sb = new StringBuilder();
            vowel_count = 0;
            consonant_count = 0;
            char c = alphabets[i];

            sb.append(c);
            checkVowel(c);

            backtracking(i, 1);

            if (i > C - L) {
                break;
            }
        }


    }

    private static void backtracking(int prevIdx, int count) {
        if (count == L) {
            if (vowel_count >= MIN_VOWEL && consonant_count >= MIN_CONSONANT) {

                System.out.println(sb);
            }
            return;
        }

        for (int i = prevIdx + 1; i < C; i++) {
            char c = alphabets[i];

            sb.append(c);
            checkVowel(c);

            backtracking(i, count + 1);

            sb.deleteCharAt(count);
            deleteVowel(c);
        }
    }

    private static void checkVowel(char c) {
        for (char v : vowel) {
            if (c == v) {
                vowel_count++;
                return;
            }
        }
        consonant_count++;
    }

    private static void deleteVowel(char c) {
        for (char v : vowel) {
            if (c == v) {
                vowel_count--;
                return;
            }
        }
        consonant_count--;
    }
}
