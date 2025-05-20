import java.util.*;
import java.io.*;

public class Main {
    private static Deque<Integer> deque;
    private static boolean isReverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < T; i++){
            deque = new LinkedList<>();
            isReverse = false;
            String[] operation = br.readLine().split("");

            int n = Integer.parseInt(br.readLine());

            String temp = br.readLine().replace("[", "").replace("]", "");
            StringTokenizer st = new StringTokenizer(temp, ",");
            for(int j = 0; j < n; j++){
                deque.offerLast(Integer.parseInt(st.nextToken()));
            }

            boolean isError = false;
            for (String op : operation) {
                if(op.equals("R")) {
                    reverse();
                    continue;
                }
                if(op.equals("D")) {
                    if(dump()){
                        isError = true;
                    };
                }
            }

            if(isError){
                sb.append("error").append("\n");
            } else{
                int size = deque.size();
                sb.append("[");
                for(int j = 0; j < size; j++){
                    if(isReverse) sb.append(deque.pollLast());
                    else sb.append(deque.pollFirst());

                    if(j != size - 1) sb.append(",");
                }
                sb.append("]").append("\n");
                
            }

        }

        bw.write(sb.toString());
        bw.flush(); bw.close();
    }

    private static void reverse(){
        isReverse = !isReverse;
    }

    private static boolean dump(){
        if(deque.isEmpty()){
            return true;
        }

        if(isReverse){
            deque.pollLast();
        }else{
            deque.pollFirst();
        }
        return false;
    }
}
