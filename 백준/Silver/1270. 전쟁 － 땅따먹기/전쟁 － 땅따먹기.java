import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Map<Long, Integer>> data = new ArrayList<>();
        for (int i = 0; i < N; i++){
            data.add(new HashMap<>());
        }

        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int soldierNum = Integer.parseInt(st.nextToken());

            Map<Long, Integer> map = data.get(i);
            boolean flag = false;
            for (int j = 0; j < soldierNum; j++){
                long element = Long.parseLong(st.nextToken());
                map.put(element, map.getOrDefault(element, 0) + 1);

                if (map.get(element) > (soldierNum / 2)) {
                    System.out.println(element);
                    flag = true;
                    break;
                }
            }

            if (!flag){
                System.out.println("SYJKGW");
            }
        }
    }
}