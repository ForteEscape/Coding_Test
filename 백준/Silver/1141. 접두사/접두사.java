import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = new HashSet<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++){
            set.add(br.readLine());
        }

        List<String> words = new ArrayList<>(set);
        Collections.sort(words, (o1, o2) -> o1.length() - o2.length());

        List<String> ans = new ArrayList<>();

        for(int i = 0; i < words.size() - 1; i++){
            String original = words.get(i);

            boolean flag = true;
            for(int j = i + 1; j < words.size(); j++){
                String other = words.get(j);

                if (chk(original, other)){
                    flag = false;
                    break;
                }
            }

            if(flag){
                ans.add(original);
            }
        }

        System.out.println(ans.size() + 1);
    }

    public static boolean chk(String original, String other){
        for(int i = 0; i < original.length(); i++){
            if(original.charAt(i) != other.charAt(i)){
                return false;
            }
        }

        return true;
    }
}