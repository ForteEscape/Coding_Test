import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 1; i <= N; i++){
            deque.addLast(i);
        }

        st = new StringTokenizer(br.readLine());
        int ans = 0;

        while(st.hasMoreTokens()){
            int key = Integer.parseInt(st.nextToken());
            int index = 0;

            for (Integer element: deque) {
                if (element == key){
                    break;
                }

                index++;
            }

            while(deque.peekFirst() != key){
                if (deque.size() - index < index){
                    deque.addFirst(deque.pollLast());
                    ans++;
                } else{
                    deque.addLast(deque.pollFirst());
                    ans++;
                }
            }
            deque.pollFirst();
        }

        System.out.println(ans);
    }
}