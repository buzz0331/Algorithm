import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] length = new int[6];
    private static int size;

    private static int maxWidthIdx = 0;
    private static int maxHeightIdx = 0;

    private static int maxWidth = 0;
    private static int maxHeight = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if ((direction == 1 || direction == 2) && maxWidth < distance){
                maxWidthIdx = i;
                maxWidth = distance;
            } else if ((direction == 3 || direction == 4) && maxHeight < distance) {
                maxHeightIdx = i;
                maxHeight = distance;
            }

            length[i] = distance;
        }

        int rightIdx, leftIdx;
        if (maxWidthIdx + 1 == 6) {
            rightIdx = 0;
        } else {
            rightIdx = maxWidthIdx + 1;
        }
        if (maxWidthIdx - 1 == -1) {
            leftIdx = 5;
        } else {
            leftIdx = maxWidthIdx - 1;
        }
        int width = Math.abs(length[rightIdx] - length[leftIdx]);

        if (maxHeightIdx + 1 == 6) {
            rightIdx = 0;
        } else {
            rightIdx = maxHeightIdx + 1;
        }
        if (maxHeightIdx - 1 == -1) {
            leftIdx = 5;
        } else {
            leftIdx = maxHeightIdx - 1;
        }
        int height = Math.abs(length[rightIdx] - length[leftIdx]);

        size = maxWidth * maxHeight - (width * height);

        System.out.println(size * K);
    }


}
