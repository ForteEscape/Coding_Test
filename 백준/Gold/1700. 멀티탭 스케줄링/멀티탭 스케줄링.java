import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> plugList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            plugList.add(Integer.parseInt(st.nextToken()));
        }

        Set<Integer> set = new HashSet<>();
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            int plugNum = plugList.get(i);

            if (set.contains(plugNum)) {
                continue;
            }

            if (set.size() < N) {
                set.add(plugNum);
                continue;
            }

            int max = -1;
            int idx = -1;

            for (int element : set) {
                int tmp = 0;
                List<Integer> subList = plugList.subList(i + 1, K);

                if (subList.contains(element)) {
                    tmp = subList.indexOf(element) + 1;
                } else {
                    tmp = K - i - 1;
                }

                if (tmp > max) {
                    max = tmp;
                    idx = element;
                }
            }

            set.remove(idx);
            set.add(plugNum);
            cnt++;
        }

        System.out.println(cnt);
    }
}