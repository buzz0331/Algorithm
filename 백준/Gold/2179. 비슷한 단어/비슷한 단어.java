import java.util.*;
import java.io.*;


public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		List<String> stringList = new ArrayList<>();
		Map<String, Integer> indexMap = new HashMap<>();
		Map<String, Set<String>> prefixMap = new HashMap<>(); // prefix와 문자열 매핑.
		
		for (int i = 0 ; i < N; i++) {
			String s = br.readLine();
			if(indexMap.containsKey(s)) { // 이미 같은 문자열이 존재하는 경우
				continue;
			}
			stringList.add(s);
			indexMap.put(s, i);
		}
		
		Collections.sort(stringList);
		
		int max = Integer.MIN_VALUE;
				
		for(int i = 0; i < stringList.size() - 1; i++) {
			String s1 = stringList.get(i);
			String s2 = stringList.get(i + 1);
		
			String prefix = calPrefixLength(s1, s2);
			int prefixLength = prefix.length();
			
			if(max <= prefixLength) {
				Set<String> stringSet = prefixMap.getOrDefault(prefix, new HashSet<>());
				stringSet.add(s1);
				stringSet.add(s2);
				prefixMap.put(prefix, stringSet);
				max = prefixLength;
			}
			
		}
		
		String S = null, T = null; 
		int S_idx = Integer.MAX_VALUE, T_idx = Integer.MAX_VALUE;
		
		for(String p : prefixMap.keySet()) {
			if(p.length() < max) continue;
			
			int minSIdx = Integer.MAX_VALUE, minTIdx = Integer.MAX_VALUE;
			String minS = null, minT = null;
			
			Set<String> stringSet = prefixMap.get(p);
			
			for(String s : stringSet) {
				int index = indexMap.get(s);
				if(index < minSIdx) {
					minTIdx = minSIdx;
					minT = minS;
					
					minSIdx = index;
					minS = s;
				} else if(index < minTIdx) {
					minTIdx = index;
					minT = s;
				}
			}
			
			if(minTIdx < T_idx && minSIdx < S_idx) {
				S = minS;
				S_idx = minSIdx;
				
				T = minT;
				T_idx = minTIdx;
			}
			
		}
		
		System.out.println(S);
		System.out.println(T);
		
	}
	
	private static String calPrefixLength(String s1, String s2) {
		int prefixLength = 0;
		int length = s1.length() < s2.length() ? s1.length() : s2.length();
		
		for(int i = 1; i < length + 1; i++) {
			if(s1.substring(0, i).equals(s2.substring(0, i))) {
				prefixLength++;
			} else {
				break;
			}
		}
		return s1.substring(0, prefixLength);
	}

}
