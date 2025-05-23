import java.io.*;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int min = sigma(K);	//최소 경우 공의 개수 구하기
        if(min > N)		//최소 경우도 바구니에 담지 못할 때
            bw.write("-1");		//조건에 맞게 바구니에 담지 못함!
        else{
            //최소 경우 바구니에 담은 후
            int answer = K-1;
            N -= min;		//남은 공 개수
            if(N%K != 0)	//남은 공을 1개씩 나눠서 담았을 때 모두 같게 나누지 못할 때
                answer++;		//최소와 최대 차이가 1이 더 벌어집니다.
            bw.write(answer + "");	//최대, 최소 공의 차이 BufferedWriter 저장
        }
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //∑를 구현한 함수
    static int sigma(int n){
        int result = 0;
        for(int i=1;i<=n;i++)
            result += i;
        return result;
    }
}