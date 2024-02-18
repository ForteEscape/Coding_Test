import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] number = { 0b1110111, 0b0010010, 0b1011101, 0b1011011, 0b0111010, 0b1101011, 0b1101111, 0b1010010,
			0b1111111, 0b1111011 };

	static int K, P;
	static String N, X;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = st.nextToken();
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = st.nextToken();

		ans = 0;
		int initLength = X.length();
		for (int i = 0; i < K - initLength; i++) {
			X = '0' + X;
		}

		solve(0, 0, new char[K]);

		System.out.println(ans);
	}

	public static void solve(int start, int flipedCount, char[] selected) {
		if (start == K) {
			if (flipedCount == 0) {
				return;
			}

			int limit = Integer.parseInt(N);
			int result = Integer.parseInt(String.valueOf(selected));

			if (result > 0 && result <= limit) {
				ans++;
			}

			return;
		}

		for (int i = 0; i < 10; i++) {
			// flipedCount가 아직 P 이하이면
			int result = number[X.charAt(start) - '0'] ^ number[i];
			
			int cnt = 0;
			for(int j = 0; j <= 6; j++) {
				if((result & (1 << j)) == (1 << j)) {
					cnt++;
				}
			}
			
			if (flipedCount + cnt <= P) {
				selected[start] = (char) (i + '0');
				solve(start + 1, flipedCount + cnt, selected);
			}
		}
	}

}