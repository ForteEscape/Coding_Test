import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		long pi = N;
		for(int i = 2; i <= Math.sqrt(N); i++) {
			if(N % i == 0) {
				pi = (pi / i) * (i - 1);
			}
			
			while(N % i == 0) {;
				N /= i;
			}
		}
		
		if(N > 1) {
			pi = (pi / N) * (N - 1);
		}
		
		System.out.println(pi);
	}
}