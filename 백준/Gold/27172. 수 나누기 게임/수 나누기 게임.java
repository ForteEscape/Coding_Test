import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        int[] data = new int[N];
        int limit = Integer.MIN_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
            map.put(data[i], 0);

            limit = Math.max(limit, data[i]);
        }

        for (int i = 0; i < N; i++) {
            for (int j = data[i] + data[i]; j <= limit; j += data[i]) {
                if (map.containsKey(j)) {
                    map.put(j, map.get(j) - 1);
                    map.put(data[i], map.get(data[i]) + 1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int element: data) {
            sb.append(map.get(element)).append(" ");
        }

        System.out.println(sb);
    }
}
