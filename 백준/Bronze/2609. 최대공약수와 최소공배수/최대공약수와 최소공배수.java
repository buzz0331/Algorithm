import java.util.*;
import java.io.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
     
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        int gcd = gcd(a, b);
        int lcm = lcm(a, b);
        
        System.out.println(gcd);
        System.out.println(lcm);
    }
    
    public static int gcd(int a, int b) {
    	if(b == 0) return a;
    	return gcd(b, a % b);
    }
    
    
    public static int lcm(int a, int b) {
    	return a * b / gcd(a, b);
    }
    
}