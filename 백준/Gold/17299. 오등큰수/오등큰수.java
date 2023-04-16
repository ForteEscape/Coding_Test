import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] cntArr = new int[1000001];

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
            cntArr[nums[i]]++;
        }

        int[] answer = new int[N];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = N - 1; i >= 0; i--){
            while(!stack.isEmpty() && cntArr[nums[stack.peekLast()]] <= cntArr[nums[i]]){
                stack.pollLast();
            }

            if(stack.isEmpty()){
                answer[i] = -1;
            } else{
                answer[i] = nums[stack.peekLast()];
            }

            stack.addLast(i);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int element: answer){
            sb.append(element).append(" ");
        }

        System.out.println(sb.toString());
    }
}