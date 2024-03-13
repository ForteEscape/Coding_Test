import java.util.*;
import java.io.*;

public class Main {
	
	static class Tree {
		int y;
		int x;
		int age;
		
		Tree(int y, int x, int age) {
			this.y = y;
			this.x = x;
			this.age = age;
		}
	}
	
	static class Segment {
		int compliment;
		int currentFood;
		PriorityQueue<Tree> trees;
		
		Segment(int compliment, int currentFood) {
			this.compliment = compliment;
			this.currentFood = currentFood;
			this.trees = new PriorityQueue<>(Comparator.comparingInt(t -> t.age));
		}
	}
	
	static Segment[][] board;
	static int N, M, K;
	static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new Segment[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int compliment = Integer.parseInt(st.nextToken());		
				board[i][j] = new Segment(compliment, 5);
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			board[y][x].trees.add(new Tree(y, x, age));
		}
		
		for(int i = 0; i < K; i++) {
			eatingFood();
			breeding();
		}
		
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(board[i][j].trees.size() >= 0) {
					ans += board[i][j].trees.size();
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void eatingFood() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {			
				int deadTreeCompliment = 0;
				List<Tree> tmpList = new ArrayList<>();
				
				while(!board[i][j].trees.isEmpty()) {
					Tree tree = board[i][j].trees.poll();
					
					if(board[i][j].currentFood >= tree.age) {
						board[i][j].currentFood -= tree.age;
						tree.age++;
						
						tmpList.add(tree);
					} else {
						deadTreeCompliment += (tree.age / 2);
					}
				}
				
				board[i][j].currentFood += deadTreeCompliment;
				for(int k = 0; k < tmpList.size(); k++) {
					board[i][j].trees.offer(tmpList.get(k));
				}
				
				board[i][j].currentFood += board[i][j].compliment;
			}
		}
	}
	
	static void breeding() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(board[i][j].trees.size() >= 1) {
					for(Tree tree : board[i][j].trees) {
						if(tree.age % 5 == 0) {
							for(int k = 0; k < 8; k++) {
								int ny = i + dy[k];
								int nx = j + dx[k];
								
								if(isUnreachable(ny, nx)) {
									continue;
								}
								
								board[ny][nx].trees.offer(new Tree(ny, nx, 1));
							}
						}
					}
				}
			}
		}
	}
	
	static boolean isUnreachable(int y, int x) {
		return y < 1 || y > N || x < 1 || x > N;
	}
}