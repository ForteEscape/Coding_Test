import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        String[] data = new String[N];
        for (int i = 0; i < N; i++){
            data[i] = br.readLine();
        }
        
        for(int i = 0; i < N; i++){
            board[i] = data[i].toCharArray();
        }

        int left = 0;
        int right = N;
        int count = 0;

        while(left <= right){
            int mid = (left + right) / 2;

            if (chk(board, mid)){
                right = mid - 1;
            } else {
                count = mid;
                left = mid + 1;
            }
        }

        System.out.println(count);
    }

    public static boolean chk(char[][] board, int mid){
        Set<String> set = new HashSet<>();

        for(int i = 0; i < M; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = mid; j < N; j++) {
                sb.append(board[j][i]);
            }

            //System.out.println(sb);

            if (set.contains(sb.toString())){
                return true;
            } else {
                set.add(sb.toString());
            }
        }

        return false;
    }
}