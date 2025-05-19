import java.util.*;
import java.io.*;

public class Main {
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            String[] op = br.readLine().split(" ");
            switch(op[0]){
                case "push":
                    stack.push(Integer.parseInt(op[1]));
                    break;
                case "top":
                    if(stack.isEmpty()){
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(stack.peek()).append("\n");
                    }
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    if(stack.isEmpty()){
                        sb.append(1).append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                    break;
                case "pop":
                    if(stack.isEmpty()){
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(stack.pop()).append("\n");
                    }
                    break;
            }
        }

        bw.write(sb.toString());
        bw.flush(); bw.close();
    }
}
