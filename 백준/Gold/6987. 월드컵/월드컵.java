import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] result;
	static int[][] match;
	static int[][] candidate;
	static int matchNum;
	static boolean isValidate;
	
	static {
		match = new int[2][15];
		match[0] = new int[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
		match[1] = new int[] {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
	}
	
	// 각 국가가 이기는 경우, 지는 경우, 무승부인 경우를 나누어 가지뻗기
	// 만약 각 카운트가 해당 나라의 카운트와 다른 경우에는 해당 가지 탐색 x

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 4; i++) {
			result = new int[6][3];
			candidate = new int[6][3];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 6; j++) {
				for(int k = 0; k < 3; k++) {
					result[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			isValidate = false;
			matchNum = 0;
			
			solve(0);
			
			if(isValidate) {
				sb.append(1).append(" ");
			} else {
				sb.append(0).append(" ");
			}
		}
		
		System.out.println(sb);
	}

	public static void solve(int currentRound) {
		if(isValidate) return;
		
		if(currentRound == 15) {
			if(matchNum != 30) {
				return;
			} else {
				for(int i = 0; i < 6; i++) {
					for(int j = 0; j < 3; j++) {
						if(result[i][j] != candidate[i][j]) {
							return;
						}
					}
				}
			}
			
			isValidate = true;
			return;
		}
		
		// team[0]이 이기고 team[1]이 지는 경우
		candidate[match[0][currentRound]][0]++;
		candidate[match[1][currentRound]][2]++;
		matchNum += 2;
		solve(currentRound + 1);
		candidate[match[0][currentRound]][0]--;
		candidate[match[1][currentRound]][2]--;
		matchNum -= 2;
		
		// 비기는 경우
		candidate[match[0][currentRound]][1]++;
		candidate[match[1][currentRound]][1]++;
		matchNum += 2;
		solve(currentRound + 1);
		candidate[match[0][currentRound]][1]--;
		candidate[match[1][currentRound]][1]--;
		matchNum -= 2;
		
		// 지는 경우
		candidate[match[0][currentRound]][2]++;
		candidate[match[1][currentRound]][0]++;
		matchNum += 2;
		solve(currentRound + 1);
		candidate[match[0][currentRound]][2]--;
		candidate[match[1][currentRound]][0]--;
		matchNum -= 2;
	}
}