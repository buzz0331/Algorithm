import java.util.*;
import java.io.*;
 
class Solution
{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
    public static void main(String args[]) throws Exception
    {
     
        int T;
        T=Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
             
            TreeSet<Integer> treeSet = new TreeSet<>();
            st = new StringTokenizer(br.readLine());
             
            for(int i = 0; i < N; i++) {
                TreeSet<Integer> temp = new TreeSet<>();
                int x= Integer.parseInt(st.nextToken());
                 
                temp.add(x);
                for(int num : treeSet) {
                    temp.add(x + num);
                }
                 
                treeSet.addAll(temp);
            }
             
            int find = treeSet.ceiling(B);
            sb.append("#").append(test_case).append(" ").append(find - B).append("\n");
           
        }
         
        System.out.print(sb.toString());
    }
}