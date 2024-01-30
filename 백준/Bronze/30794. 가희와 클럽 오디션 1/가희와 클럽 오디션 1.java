import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int lv = Integer.parseInt(st.nextToken());
        String data = st.nextToken();

        Map<String, Integer> map = new HashMap<>();
        map.put("miss", 0);
        map.put("bad", 200);
        map.put("cool", 400);
        map.put("great", 600);
        map.put("perfect", 1000);

        System.out.println(lv * map.get(data));
    }
}