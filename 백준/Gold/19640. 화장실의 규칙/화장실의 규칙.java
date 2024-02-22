import java.io.*;
import java.util.*;

public class Main {
	
	static class Employee implements Comparable<Employee>{
		int row;
		int workDay;
		int emergency;
		
		Employee(int row, int workDay, int emergency) {
			this.row = row;
			this.workDay = workDay;
			this.emergency = emergency;
		}

		@Override
		public int compareTo(Employee o) {
			if(o.workDay == this.workDay) {
				if(o.emergency == this.emergency) {
					return this.row - o.row;
				} else {
					return o.emergency - this.emergency;
				}
			} else {
				return o.workDay - this.workDay;
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + emergency;
			result = prime * result + row;
			result = prime * result + workDay;
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
			Employee other = (Employee) obj;
			if (emergency != other.emergency)
				return false;
			if (row != other.row)
				return false;
			if (workDay != other.workDay)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Employee [row=" + row + ", workDay=" + workDay + ", emergency=" + emergency + "]";
		}
		
		
	}
	
	static int N, M, K;
	static Deque<Employee>[] queueArr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		queueArr = new Deque[M];
		for(int i = 0; i < M; i++) {
			queueArr[i] = new ArrayDeque<>();
		}
		
		Employee deka = null;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int row = i % M;
			int day = Integer.parseInt(st.nextToken());
			int emergency = Integer.parseInt(st.nextToken());
			
			Employee employee = new Employee(row, day, emergency);
			queueArr[row].addLast(employee);
			
			if(i == K) {
				deka = employee;
			}
		}
		
		PriorityQueue<Employee> pq = new PriorityQueue<>();
		for(int i = 0; i < M; i++) {
			if(!queueArr[i].isEmpty()) {
				pq.offer(queueArr[i].pollFirst());
			}
		}
		
		int ans = 0;
		while(true) {
			Employee usingEmployee = pq.poll();
			int row = usingEmployee.row;
			
			if(deka == usingEmployee) {
				break;
			}
			
			ans++;
			
			if(!queueArr[row].isEmpty()) {
				pq.offer(queueArr[row].pollFirst());
			}
		}
		
		System.out.println(ans);
	}
	
}