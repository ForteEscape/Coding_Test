import java.io.*;
import java.util.*;

public class Main {
	
	static class Problem implements Comparable<Problem>{
		int idx;
		int difficulty;
		
		Problem(int idx, int difficulty) {
			this.idx = idx;
			this.difficulty = difficulty;
		}

		@Override
		public int compareTo(Problem o) {
			if(o.difficulty == this.difficulty) {
				return o.idx - this.idx;
			} else {
				return o.difficulty - this.difficulty;
			}
		}
	}
	
	static TreeSet<Problem> problemSet;
	static Problem[] problemArr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		problemSet = new TreeSet<>();
		problemArr = new Problem[100_001];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int idx = Integer.parseInt(st.nextToken());
			int difficulty = Integer.parseInt(st.nextToken());
			
			Problem problem = new Problem(idx, difficulty);
			problemSet.add(problem);
			problemArr[idx] = problem;
		}
		
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			String query = st.nextToken();
			
			if(query.equals("add")) {
				int idx = Integer.parseInt(st.nextToken());
				int difficulty = Integer.parseInt(st.nextToken());
				
				Problem newProblem = new Problem(idx, difficulty);
				problemArr[idx] = newProblem;
				problemSet.add(newProblem);
			} else if(query.equals("recommend")) {
				int type = Integer.parseInt(st.nextToken());
				
				if(type == 1) {
					System.out.println(problemSet.first().idx);
				} else {
					System.out.println(problemSet.last().idx);
				}
			} else if(query.equals("solved")) {
				int idx = Integer.parseInt(st.nextToken());
				
				problemSet.remove(problemArr[idx]);
				problemArr[idx] = null;
			}
		}
	}
}