import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 1; i <= N; i++){
            list.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        while(!list.isEmpty()){
            for(int i = 0; i < K - 1; i++){
                list.addLast(list.pollFirst());
            }

            sb.append(list.pollFirst());
            if (!list.isEmpty()){
                sb.append(", ");
            } else{
                sb.append(">");
            }
        }

        System.out.println(sb.toString());
    }
}
