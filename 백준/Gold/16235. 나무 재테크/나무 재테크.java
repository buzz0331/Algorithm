import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Land[][] map;

    private static final int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 맵 크기
        int M = Integer.parseInt(st.nextToken()); // 나무 개수
        int K = Integer.parseInt(st.nextToken()); // 년수

        map = new Land[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = new Land(Integer.parseInt(st.nextToken()));
            }
        }

        int count = 0; // 총 나무 갯수

        // 나무 입력
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());

            map[row][col].trees.offer(age);
            count++;
        }

        Queue<int[]> breed = new ArrayDeque<>();
        // 계절 순환 시작
        while(K --> 0) {
            // 봄 & 여름
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    PriorityQueue<Integer> temp = new PriorityQueue<>();
                    int feed = 0; // 죽은 나무로 인해 증가되는 양분

                    PriorityQueue<Integer> trees = map[i][j].trees;

                    while(!trees.isEmpty()) {
                        Integer age = trees.poll();

                        if(age <= map[i][j].feedSum) { // 나무에게 양분 줄 수 있는 상태
                            map[i][j].feedSum -= age;
                            age++;
                            if(age % 5 == 0) { // 5의 배수이면 번식 준비
                                breed.offer(new int[]{i, j});
                            }
                            temp.offer(age);
                        } else {
                            feed += age / 2;
                            count--;
                            while(!trees.isEmpty()) {
                                feed += trees.poll() / 2; // 나이 많은 나무 모두 죽임
                                count--;
                            }
                        }
                    }

                    map[i][j].trees = temp;
                    map[i][j].feedSum += feed;
                }
            }

            // 가을
            while(!breed.isEmpty()) {
                int[] location = breed.poll();
                for(int[] direction : directions) {
                    int nRow = location[0] + direction[0];
                    int nCol = location[1] + direction[1];

                    if(nRow >= 0 && nRow < N && nCol >= 0 && nCol < N) {
                        map[nRow][nCol].trees.offer(1);
                        count++;
                    }
                }
            }


            // 겨울
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    map[i][j].increaseFeed();
                }
            }
        }

        System.out.print(count);

    }

    private static class Land {
        public int feedInc; // 매달 증가하는 양분
        public int feedSum; // 현재 땅의 총 양분
        public PriorityQueue<Integer> trees; // 현재 칸에 존재하는 나무 나이

        Land(int feedInc) {
            this.feedInc = feedInc;
            this.feedSum = 5;
            this.trees = new PriorityQueue<>();
        }

        public void increaseFeed() {
            this.feedSum += this.feedInc;
        }
    }
}
