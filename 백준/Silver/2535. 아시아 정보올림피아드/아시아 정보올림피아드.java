import java.util.*;
import java.io.*;

public class Main {

    static class Data {
        int nation;
        int number;
        int score;

        Data(int nation, int number, int score) {
            this.nation = nation;
            this.number = number;
            this.score = score;
        }
    }

    static Data[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        result = new Data[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int nation = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            result[i] = new Data(nation, number, score);
        }

        Arrays.sort(result, Comparator.comparingInt(e -> -e.score));
        HashMap<Integer, Integer> nationMap = new HashMap<>();
        List<Data> ans = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            if (!nationMap.containsKey(result[i].nation)) {
                nationMap.put(result[i].nation, 1);
                ans.add(result[i]);
            } else {
                if(nationMap.get(result[i].nation) < 2) {
                    nationMap.put(result[i].nation, nationMap.get(result[i].nation) + 1);
                    ans.add(result[i]);
                }
            }

            if (ans.size() == 3) break;
        }

        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i).nation + " " + ans.get(i).number);
        }
    }
}