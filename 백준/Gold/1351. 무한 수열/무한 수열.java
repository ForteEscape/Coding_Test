import java.util.*;
import java.io.*;

public class Main {
    
    public static long N;
    public static int P, Q;
    public static Map<Long, Long> map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        
        if(N == 0) {
            System.out.println(1);
            return;
        }
        
        map = new HashMap<>();
        map.put(0L, 1L);
        map.put(1L, 2L);
        
        long ans = solve(N, P, Q);
        
        System.out.println(ans);
    }
    
    public static long solve(long N, int P, int Q) {
        if(map.containsKey(N)) {
            return map.get(N);
        }
        
        map.put(N / P, solve(N / P, P, Q));
        map.put(N / Q, solve(N / Q, P, Q));
        
        return map.get(N / P) + map.get(N / Q);
    }
}
