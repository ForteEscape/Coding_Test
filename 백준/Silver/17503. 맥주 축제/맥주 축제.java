import java.util.*;
import java.io.*;

public class Main {
	
	static class Beer implements Comparable<Beer> {
		int value;
		int cost;
		
		Beer(int value, int cost) {
			this.value = value;
			this.cost = cost;
		}

		@Override
		public int compareTo(Beer o) {
			// TODO Auto-generated method stub
			if(Integer.compare(this.cost, o.cost) == 0) {
				return -Integer.compare(this.value, o.value);
			}
			
			return Integer.compare(this.cost, o.cost);
		}

		@Override
		public String toString() {
			return "Beer [value=" + value + ", cost=" + cost + "]";
		}
		
		
	}
	
	static Beer[] beers;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		beers = new Beer[M];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int value = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			beers[i] = new Beer(value, cost);
		}
		
		Arrays.sort(beers);
		
		long sum = 0L;
		boolean flag = true;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i = 0; i < M; i++) {
			pq.offer(beers[i].value);
			sum += beers[i].value;
			
			if(pq.size() > N) {
				sum -= pq.poll();
			}
			
			if(pq.size() == N && sum >= S) {
				flag = false;
				System.out.println(beers[i].cost);
				break;
			}
		}
		
		if(flag) {
			System.out.println(-1);
		}
	}
 }