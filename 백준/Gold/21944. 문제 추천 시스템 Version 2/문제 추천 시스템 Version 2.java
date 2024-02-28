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
			if(this.difficulty == o.difficulty) {
				return this.idx - o.idx;
			} else {
				return this.difficulty - o.difficulty;
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + difficulty;
			result = prime * result + idx;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Problem other = (Problem) obj;
			if (difficulty != other.difficulty)
				return false;
			if (idx != other.idx)
				return false;
			return true;
		}
	}
	
	static int[] problemType;
	static Problem[] problems;
	static Map<Integer, TreeSet<Problem>> recommendMap; // classified type
	static TreeSet<Problem> recommendSet; // unClassified type

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		recommendMap = new HashMap<>();
		recommendSet = new TreeSet<>();
		problemType = new int[100_001];
		problems = new Problem[100_001];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int problemIdx = Integer.parseInt(st.nextToken());
			int difficulty = Integer.parseInt(st.nextToken());
			int type = Integer.parseInt(st.nextToken());
			
			addProblem(problemIdx, difficulty, type);
		}
		
		int Q = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			String query = st.nextToken();
			
			if(query.equals("add")) {
				int problemIdx = Integer.parseInt(st.nextToken());
				int difficulty = Integer.parseInt(st.nextToken());
				int type = Integer.parseInt(st.nextToken());
				
				addProblem(problemIdx, difficulty, type);
			} else if(query.equals("recommend")) {
				int type = Integer.parseInt(st.nextToken());
				int option = Integer.parseInt(st.nextToken());
				
				System.out.println(recommend(type, option));
			} else if(query.equals("recommend2")) {
				int option = Integer.parseInt(st.nextToken());
				
				System.out.println(recommend2(option));
			} else if(query.equals("recommend3")) {
				int option = Integer.parseInt(st.nextToken());
				int difficulty = Integer.parseInt(st.nextToken());
				
				System.out.println(recommend3(option, difficulty));
			} else if(query.equals("solved")) {
				int problemIdx = Integer.parseInt(st.nextToken());
				
				remove(problemIdx);
			}
		}
		
	}
	
	public static void addProblem(int problemIdx, int difficulty, int type) {
		problemType[problemIdx] = type;
		problems[problemIdx] = new Problem(problemIdx, difficulty);
		
		if(!recommendMap.containsKey(type)) {
			recommendMap.put(type, new TreeSet<>());
		}
		
		recommendMap.get(type).add(new Problem(problems[problemIdx].idx, problems[problemIdx].difficulty));
		recommendSet.add(new Problem(problems[problemIdx].idx, problems[problemIdx].difficulty));
	}
	
	public static int recommend(int type, int option) {
		TreeSet<Problem> typeProblemSet = recommendMap.get(type);
		
		if(option == 1) {
			return typeProblemSet.last().idx;
		}
		
		return typeProblemSet.first().idx;
	}
	
	public static int recommend2(int option) {
		if(option == 1) {
			return recommendSet.last().idx;
		}
		
		return recommendSet.first().idx;
	}
	
	public static int recommend3(int option, int difficulty) {
		Problem result = null;
		
		if(option == 1) {
			result = recommendSet.ceiling(new Problem(0, difficulty));
			return result == null ? -1 : result.idx;
		}
		
		result = recommendSet.floor(new Problem(0, difficulty));
		return result == null ? -1 : result.idx;
	}
	
	public static void remove(int problemIdx) {
		Problem deleteProblem = problems[problemIdx];
		int type = problemType[problemIdx];
		
		recommendMap.get(type).remove(deleteProblem);
		recommendSet.remove(deleteProblem);
		
		problems[problemIdx] = null;
		problemType[problemIdx] = 0;
	}
}