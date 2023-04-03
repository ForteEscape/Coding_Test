import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Gem{
        int weight;
        int value;

        Gem(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Gem[] gems = new Gem[N];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            gems[i] = new Gem(weight, value);
        }

        Arrays.sort(gems, (x, y) -> (x.weight - y.weight) == 0 ? y.value - x.value : x.weight - y.weight);

        int[] knapsack = new int[K];
        for (int i = 0; i < K; i++){
            knapsack[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(knapsack);

        PriorityQueue<Gem> pq = new PriorityQueue<>((x, y) -> (y.value - x.value));
        long ans = 0;
        int idx = 0;

        for (int i = 0; i < K; i++){
            while(idx < N && gems[idx].weight <= knapsack[i]){
                pq.offer(gems[idx++]);
            }

            if (!pq.isEmpty()){
                ans += pq.poll().value;
            }
        }

        System.out.println(ans);
    }
}