import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            pq.offer(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < M; i++){
            long a = pq.poll();
            long b = pq.poll();

            long result = a + b;
            pq.offer(result);
            pq.offer(result);
        }

        long ans = 0;
        for (long element: pq){
            ans += element;
        }

        System.out.println(ans);
    }
}

