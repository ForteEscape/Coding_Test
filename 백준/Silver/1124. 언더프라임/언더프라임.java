import java.util.*;
import java.io.*;

public class Main {
	
	static int[] prime;
	static Set<Integer> primeSet;
	
	static {
		prime = new int[100_001];
		
		for(int i = 2; i <= 100_000; i++) {
			prime[i] = 1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		getPrime();
		
		int ans = 0;
		for(int i = A; i <= B; i++) {
			if(primeSet.contains(i)) {
				continue;
			}
			
			int tmp = i;
			int cnt = 0;
			
			while(tmp > 1) {
				for(int prime : primeSet) {
					if(tmp % prime == 0) {
						cnt++;
						tmp /= prime;
						break;
					}
				}
			}
			
			if(primeSet.contains(cnt)) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
	
	static void getPrime() {
		primeSet = new HashSet<>();
		
		for(int i = 2; i < (Math.sqrt(100_000) + 1); i++) {
			if(prime[i] == 1) {
				for(int j = i + i; j < 100_001; j += i) {
					prime[j] = 0;
				}
			}
		}
		
		for(int i = 2; i <= 100_000; i++) {
			if(prime[i] == 1) {
				primeSet.add(i);
			}
		}
	}
 }