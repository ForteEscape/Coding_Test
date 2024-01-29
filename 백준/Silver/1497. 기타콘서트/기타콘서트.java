import java.util.*;
import java.io.*;


public class Main {

    public static long[] bit;
    public static int ans;
    public static int N;
    public static int M;
    public static int playableCnt;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bit = new long[N];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            String status = st.nextToken();

            for (int j = 0; j < status.length(); j++){
                if (status.charAt(j) == 'Y'){
                    bit[i] |= (1L << (M - 1 - j));
                }
            }
        }

        playableCnt = 0;
        ans = Integer.MAX_VALUE;
        visited = new boolean[N];

        for(int i = 0; i < N; i++){
            solve(i, 0L);
        }

        if (playableCnt == 0){
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    public static void solve(int idx,long result){
        if (result != 0){
            int cnt = 0;
            for (int i = 0; i < M; i++){
                if ((result & (1L << i)) == (1L << i)){
                    cnt++;
                }
            }

            if (cnt > playableCnt){
                playableCnt = cnt;
                ans = idx;
            } else if (cnt == playableCnt){
                ans = Math.min(ans, idx);
            }
        }

        for(int i = idx; i < N; i++){
            solve(idx + 1, result | bit[i]);
        }
    }
}