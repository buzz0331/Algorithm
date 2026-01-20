import java.util.*;
import java.io.*;

public class Main {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map = new int[19][19];
    
    public static void main(String[] args) throws IOException {     
    	
    	for (int i = 0; i < 19; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}    		
		}
    	
    	int winner = 0;
    	int row = 0, col = 0;
    	
    	Label:for (int i = 0; i < 19; i++) {
    		for(int j = 0; j < 19; j++) {
    			
    			if(map[i][j] != 0) {
    				int color = map[i][j];
    				
    				int check = checkWin(i, j, color);
    				if(check == 1) {
    					row = i; col = j;
    					winner = color;
    					break Label;
    				} else if(check == 2) {
    					row = i + 4; col = j - 4;
    					winner = color;
    					break Label;
    				}
    			}
    			
    		}
    	}
    	
    	
    	System.out.println(winner);
    	if(winner != 0) System.out.print(++row + " " + ++col); 
                     
    }
    
    private static int checkWin(int row, int col, int color) {
    	//좌우 확인
    	int count = 1;
    	for(int d = 1; d < col; d++) {
    		if(count == 6) break;
    		if(map[row][col - d] == color) count ++;
    		else break;
    	}
    	
    	for (int d = 1; d < 19 - col; d++) {
    		if(count == 6) break;
    		if(map[row][col + d] == color) {
    			count ++;
    		}
    		else break;
    	}
    	
    	if(count == 5) return 1;
    	
    	
    	//상하 확인
    	count = 1;
    	for(int d = 1; d < row; d++) {
    		if(count == 6) break;
    		if(map[row - d][col] == color)  count ++;
    		else break;
    	}
    	
    	for (int d = 1; d < 19 - row; d++) {
    		if(count == 6) break;
    		if(map[row + d][col] == color)  count ++;
    		else break;
    	}
    	
    	if(count == 5) return 1;
    	
    	
    	//대각선 확인
    	count = 1;
    	int nRow = row;
    	int nCol = col;
    	while(true) {
    		if(count == 6) break;
    		nRow--;
    		nCol--;
    		if(nRow < 0 || nCol < 0) break;
    		if(map[nRow][nCol] == color) count ++;
    		else break;
    	}
    	
    	nRow = row;
    	nCol = col;
    	while(true) {
    		if(count == 6) break;
    		nRow++;
    		nCol++;
    		if(nRow >= map.length || nCol >= map.length) break;
    		if(map[nRow][nCol] == color) count ++;
    		else break;
    	
    	}
    	
    	if(count == 5) return 1;
    	
    	
    	//대각선 확인
    	count = 1;
    	nRow = row;
    	nCol = col;
    	while(true) {
    		if(count == 6) break;
    		nRow--;
    		nCol++;
    		if(nRow < 0 || nCol >= map.length) break;
    		if(map[nRow][nCol] == color) count ++;
    		else break;
    	}
    	
    	nRow = row;
    	nCol = col;
    	while(true) {
    		if(count == 6) break;
    		nRow++;
    		nCol--;
    		if(nRow >= map.length || nCol < 0) break;
    		if(map[nRow][nCol] == color) count ++;
    		else break;
    	
    	}
    	
    	if(count == 5) return 2;
    	
    	
    	return 0;
    	
    	
    }
    
   
}