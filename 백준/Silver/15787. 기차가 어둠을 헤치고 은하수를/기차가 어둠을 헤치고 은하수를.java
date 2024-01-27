import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] trains = new int[N + 1];
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

            if (cmd == 1){
                int seat = Integer.parseInt(st.nextToken());
                trains[idx] |= (1 << (seat - 1));
            } else if (cmd == 2){
                int seat = Integer.parseInt(st.nextToken());
                trains[idx] &= ~(1 << (seat - 1));
            } else if (cmd == 3){
                trains[idx] = (trains[idx] & ~(1 << 19)) << 1;
            } else if (cmd == 4){
                trains[idx] = (trains[idx] & ~(1)) >> 1;
            }
        }

        Set<Integer> hashSet = new HashSet<>();
        for (int i = 1; i <= N; i++){
            hashSet.add(trains[i]);
        }

        System.out.println(hashSet.size());
    }
}