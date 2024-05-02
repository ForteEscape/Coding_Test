import java.util.*;
import java.io.*;

public class Main {
	
	static class Pair {
		int y;
		int x;
		
		Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int M, N, L;
	static int[] shootPlace;
	static List<Pair> animals;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		shootPlace = new int[M];
		animals = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			shootPlace[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			animals.add(new Pair(y, x));
		}
		
		Arrays.sort(shootPlace);
		int ans = 0;
		
		for(int i = 0; i < N; i++) {
			Pair animal = animals.get(i);
			
			int start = 0;
			int end = M - 1;
			
			while(start <= end) {
				int mid = (start + end) >> 1;
				int length = getLength(animal, shootPlace[mid]);
				
				if(length <= L) {
					ans++;
					break;
				} else {
					if(animal.x > shootPlace[mid]) {
						start = mid + 1;
					} else {
						end = mid - 1;
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static int getLength(Pair animal, int place) {
		return Math.abs(animal.x - place) + animal.y;
	}
}