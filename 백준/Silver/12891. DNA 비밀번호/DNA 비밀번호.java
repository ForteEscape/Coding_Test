import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int[] useCount;
	static char[] code = { 'A', 'C', 'G', 'T' };
	static int ans;
	static Map<Character, Integer> map = new HashMap<>();
	
	static {
		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);
	}
	
	static Deque<Character> deque = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		String code = br.readLine();

		useCount = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			useCount[i] = Integer.parseInt(st.nextToken());
		}
		
		String result = code.substring(0, P);
		
		int[] resultArr = new int[4];
		for(int i = 0; i < P; i++) {
			deque.addLast(code.charAt(i));
			
			resultArr[map.get(code.charAt(i))]++;
		}
		
		
		ans = 0;
		
		if(chk(resultArr)) {
			ans++;
		}
		
		for(int i = P; i < S; i++) {
			resultArr[map.get(deque.pollFirst())]--;
			deque.addLast(code.charAt(i));
			resultArr[map.get(code.charAt(i))]++;
			
			if(chk(resultArr)) {
				ans++;
			}
		}

		

		System.out.println(ans);
	}
	
	public static boolean chk(int[] resultArr) {
		for(int j = 0; j < 4; j++) {
			if(resultArr[j] < useCount[j]) {
				return false;
			}
		}
		
		return true;
	}
}