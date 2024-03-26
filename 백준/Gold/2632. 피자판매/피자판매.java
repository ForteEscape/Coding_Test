import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static Map<Integer, Integer> mapA;
	static Map<Integer, Integer> mapB;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] pizA = new int[A];
		int[] pizB = new int[B];
		
		int prefixA = 0;
		for(int i = 0; i < A; i++) {
			pizA[i] = Integer.parseInt(br.readLine());
			prefixA += pizA[i];
		}
		
		int prefixB = 0;
		for(int i = 0; i < B; i++) {
			pizB[i] = Integer.parseInt(br.readLine());
			prefixB += pizB[i];
		}
		
		mapA = new TreeMap<Integer, Integer>();
		mapB = new TreeMap<Integer, Integer>();
		
		if(prefixA <= N) {
			mapA.put(prefixA, 1);
		}
		
		if(prefixB <= N) {
			mapB.put(prefixB, 1);
		}
		
		// A의 모든 누적 합 집합을 구한다. 이때 모든 수의 합은 제외한다.
		for(int i = 0; i < A; i++) {
			int tmp = 0;
			for(int j = i; j < (i + A - 1); j++) {
				tmp += pizA[j % A];
				
				if(tmp > N) { // 누적 합 집합의 원소 중 원하는 사이즈보다 크다면 그 이상은 볼 필요가 없다.
					break;
				}
				
				mapA.put(tmp, mapA.getOrDefault(tmp, 0) + 1);
			}
		}
		
		for(int i = 0; i < B; i++) {
			int tmp = 0;
			for(int j = i; j < (i + B - 1); j++) {
				tmp += pizB[j % B];
				
				if(tmp > N) { // 누적 합 집합의 원소 중 원하는 사이즈보다 크다면 그 이상은 볼 필요가 없다.
					break;
				}
				
				mapB.put(tmp, mapB.getOrDefault(tmp, 0) + 1);
			}
		}
		
		int ans = 0;
		
		if(mapA.containsKey(N)) {
			ans += mapA.get(N);
		}
		
		if(mapB.containsKey(N)) {
			ans += mapB.get(N);
		}
		
		for(int key : mapA.keySet()) {
			if(mapB.containsKey(N - key)) {
				ans += mapA.get(key) * mapB.get(N - key);
			}
		}
		
		System.out.println(ans);
	}
}