import java.util.*;
import java.io.*;

public class Main {
	
	static final int MAX = 5_000_000;
	static Set<Integer> primeSet;
	static boolean[] visited;
	static TreeSet<Integer> team1, team2;
	static long scoreTeam1, scoreTeam2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		primeSet = new HashSet<>();
		visited = new boolean[MAX + 1];
		init();
		
		team1 = new TreeSet<>(Comparator.comparingInt(e -> -e));
		team2 = new TreeSet<>(Comparator.comparingInt(e -> -e));
		
		scoreTeam1 = 0L;
		scoreTeam2 = 0L;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int team1Prime = Integer.parseInt(st.nextToken());
			validate(team1, team2, team1Prime, 1);
			
			int team2Prime = Integer.parseInt(st.nextToken());
			validate(team2, team1, team2Prime, 2);
		}
		
		//System.out.println(primeSet.toString());
		System.out.println(scoreTeam1 == scoreTeam2 ? "우열을 가릴 수 없음" : (scoreTeam1 > scoreTeam2 ? "소수의 신 갓대웅" : "소수 마스터 갓규성"));
	}
	
	static void validate(TreeSet<Integer> team, TreeSet<Integer> otherTeam, int teamPrime, int currentTeam) {
		if(primeSet.contains(teamPrime)) {
			if(team.contains(teamPrime) || otherTeam.contains(teamPrime)) {
				if(currentTeam == 1) {
					scoreTeam1 -= 1000;
				} else {
					scoreTeam2 -= 1000;
				}
			} else {
				team.add(teamPrime);
			}
		} else {
			if(otherTeam.size() < 3) {
				if(currentTeam == 1) {
					scoreTeam2 += 1000;
				} else {
					scoreTeam1 += 1000;
				}
			} else {
				int idx = 1;
				for(int element : otherTeam) {
					if(idx == 3) {
						if(currentTeam == 1) {
							scoreTeam2 += element;
						} else {
							scoreTeam1 += element;
						}
						break;
					} else {
						idx++;
					}
				}
			}
		}
	}
	
	static void init() {
		for(int i = 2; i * i <= MAX; i++) {
			if(!visited[i]) {
				for(int j = i * i; j <= MAX; j += i) {
					visited[j] = true;
				}
			}
		}
		
		for(int i = 2; i <= MAX; i++) {
			if(!visited[i]) {
				primeSet.add(i);
			}
		}
	}
}