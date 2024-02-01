import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static String[][] commandSet = new String[100_001][2];
	static final int MAX_VALUE = 1_000_000_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Deque<Integer> stack = new ArrayDeque<>();
		main : while(true) {
			int idx = 0;
			StringBuilder sb = new StringBuilder();
			
			// cmd input
			while(true) {
				String status = br.readLine();
				if(status.equals("")) {
					//sb.append("\n");
					continue;
				}
				
				st = new StringTokenizer(status);
				String command = st.nextToken();
				if(command.equals("END")) {
					break;
				}
				
				if(command.equals("QUIT")) {
					break main;
				}
				
				commandSet[idx][0] = command;
				if(command.equals("NUM")) {
					commandSet[idx][1] = st.nextToken();
				}
				idx++;
			}
			
			int N = Integer.parseInt(br.readLine());
			
			stack.clear();
			for(int i = 0; i < N; i++) {
				stack.clear();
				int element = Integer.parseInt(br.readLine());
				stack.addLast(element);
				
				boolean err = false;
				for(int j = 0; j < idx; j++) {
					if(commandSet[j][0].equals("DUP")) {
						if(stack.isEmpty()) {
							err = true;
							break;
						}
						
						stack.addLast(stack.peekLast());
						
					} else if(commandSet[j][0].equals("POP")) {
						if(stack.isEmpty()) {
							err = true;
							break;
						}
						
						stack.pollLast();
						
					} else if(commandSet[j][0].equals("INV")) {
						if(stack.isEmpty()) {
							err = true;
							break;
						}
						
						stack.addLast(stack.pollLast() * (-1));
						
					} else if(commandSet[j][0].equals("NUM")) {
						stack.addLast(Integer.parseInt(commandSet[j][1]));
						
					} else if(commandSet[j][0].equals("SWP")) {
						int[] data = preProcess(stack);
						if(data == null) {
							err = true;
							break;
						}
						
						stack.addLast(data[0]);
						stack.addLast(data[1]);
						
					} else if(commandSet[j][0].equals("ADD")) {
						int[] data = preProcess(stack);
						if(data == null) {
							err = true;
							break;
						}
						
						long result = (long)data[0] + data[1];
						if(Math.abs(result) > MAX_VALUE) {
							err = true;
							break;
						}
						
						//System.out.println("ADD");
						stack.addLast(data[0] + data[1]);
						
					} else if(commandSet[j][0].equals("SUB")) {
						int[] data = preProcess(stack);
						if(data == null) {
							err = true;
							break;
						}
						
						long result = (long)data[1] - data[0];
						if(Math.abs(result) > MAX_VALUE) {
							err = true;
							break;
						}
						
						stack.addLast(data[1] - data[0]);
						
					} else if(commandSet[j][0].equals("MUL")) {
						int[] data = preProcess(stack);
						if(data == null) {
							err = true;
							break;
						}
						
						//System.out.println("MUL");
						long result = (long)data[1] * data[0];
						if(Math.abs(result) > MAX_VALUE) {
							err = true;
							break;
						}
						
						stack.addLast(data[1] * data[0]);
						
					} else if(commandSet[j][0].equals("DIV")) {
						int[] data = preProcess(stack);
						if(data == null || data[0] == 0) {
							err = true;
							break;
						}
						
						long result = (long)data[1] / data[0];
						if(Math.abs(result) > MAX_VALUE) {
							err = true;
							break;
						}
						
						stack.addLast(data[1] / data[0]);
						
					} else if(commandSet[j][0].equals("MOD")) {
						int[] data = preProcess(stack);
						if(data == null || data[0] == 0) {
							err = true;
							break;
						}
						
						long result = (long)data[1] % data[0];
						if(Math.abs(result) > MAX_VALUE) {
							err = true;
							break;
						}
						
						stack.addLast(data[1] % data[0]);
					}
				}
				
				if(err || stack.size() != 1) {
					sb.append("ERROR").append("\n");
				} else {
					sb.append(stack.pollLast()).append("\n");
				}
				
			}
			System.out.println(sb);
		}
		
	}
	
	public static int[] preProcess(Deque<Integer> stack) {
		if(stack.size() <= 1) {
			return null;
		}
		
		int e1 = stack.pollLast();
		int e2 = stack.pollLast();
		
		return new int[] {e1, e2};
	}
}