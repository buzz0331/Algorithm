import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] currentDirection = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 보드의 크기
        int K = Integer.parseInt(br.readLine()); // 사과의 개수

        int[][] board = new int[N + 1][N + 1];

        while(K --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            board[row][col] = -1;
        }

        int L = Integer.parseInt(br.readLine());

        while(L --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();

            map.put(time, direction);
        }

        System.out.print(dummy(board));
    }

    private static int dummy(int[][] board){
        int hRow = 1, hCol = 1; //뱀 머리의 위치
        board[hRow][hCol] = 1;
        int second = 0; //게임 소요시간
        int index = 0; // 현재 방향 배열의 인덱스
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(hRow, hCol));

        while(true) {
            second++;

            hRow += currentDirection[index][0];
            hCol+= currentDirection[index][1];

            if(hRow <= 0 || hRow >= board.length || hCol <= 0 || hCol >= board.length) {
                break;  // 보드밖으로 나가서 게임 종료
            }

            if(board[hRow][hCol] == 1) {
                break; //자기 몸에 닿아서 게임 종료
            }

            if(board[hRow][hCol] == 0)  {
                //사과가 아닌 경우 꼬리 하나 제거
                Position pos = queue.poll();
                board[pos.row][pos.col] = 0;
            }

            //머리 추가
            board[hRow][hCol] = 1;
            queue.offer(new Position(hRow, hCol));

            if(map.containsKey(second)) {   //방향 변환 여부 확인
                index = changeDirection(index, second);
            }
        }

        return second;
    }

    private static int changeDirection(int index, int second) {
        String direction = map.get(second);
        if(direction.equals("D")) {
            index++;
            index %= currentDirection.length;
        } else {
            index--;
            if(index < 0) index = 3;
        }
        return index;
    }

    private static class Position {
        public int row;
        public int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
