import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int query = Integer.parseInt(st.nextToken());

            if (query == 1) {
                int num = Integer.parseInt(st.nextToken());
                deque.addFirst(num);
            } else if (query == 2) {
                int num = Integer.parseInt(st.nextToken());
                deque.addLast(num);
            } else if (query == 3) {
                sb.append(deque.isEmpty() ? -1 : deque.pollFirst()).append("\n");
            } else if (query == 4) {
                sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append("\n");
            } else if (query == 5) {
                sb.append(deque.size()).append("\n");
            } else if (query == 6) {
                sb.append(deque.isEmpty() ? 1 : 0).append("\n");
            } else if (query == 7) {
                sb.append(deque.isEmpty() ? -1 : deque.peekFirst()).append("\n");
            } else if (query == 8) {
                sb.append(deque.isEmpty() ? -1 : deque.peekLast()).append("\n");
            }
        }
        System.out.print(sb);
    }
}