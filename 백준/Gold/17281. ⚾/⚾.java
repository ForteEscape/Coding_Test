import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] data;
	static int N;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		data = new int[N + 1][10];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= 9; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[10];
		ans = -1;
		solve(0, new ArrayList<>());
		
		System.out.println(ans);
	}
	
	// 각 이닝당 8명의 선수 순서를 배열한다. -> 순열
	// 
	public static void solve(int depth, List<Integer> order) {
		if(depth == 8) {
			int score = 0;
			int idx = 0;
			//System.out.println(order.toString());
			order.add(3, 1);
			
			//System.out.println(order.toString());
			
			for(int inning = 1; inning <= N; inning++) {
				int outCnt = 0;
				int[] playerBase = new int[10];
				
				while(true) {
					if(idx >= 9) idx = 0;
					//System.out.println("in : " + inning + " " + idx);
					
					if(outCnt >= 3) {
						break;
					}
					
					int player = order.get(idx);
					if(data[inning][player] == 0) {
						outCnt++;
					} else {
						playerBase[player] = data[inning][player];
						//System.out.println("player : " + player + " score " + data[inning][player]);
						
						for(int i = 1; i <= 9; i++) {
							if(playerBase[i] > 0) {
								if(i != player) {
									playerBase[i] += data[inning][player];
								}
								
								if(playerBase[i] >= 4) {
									score++;
									playerBase[i] = 0;
								}
							}
						}
					}
					idx++;
				}
			}
			ans = Math.max(ans, score);
			order.remove(3);
			return;
		}
		
		for(int i = 2; i <= 9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				order.add(i);
				
				solve(depth + 1, order);
				
				order.remove(order.size() - 1);
				visited[i] = false;
			}
		}
	}
}