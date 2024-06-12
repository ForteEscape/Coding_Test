import java.io.*;
import java.util.*;

public class Main {

    static int[] data;
    static char[] oper = {'+', '-', ' '};
    static List<List<String>> ansList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        ansList = new ArrayList<>();

        for(int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());

            data = new int[N + 1];
            for(int i = 0; i < N; i++) {
                data[i] = i + 1;
            }

            List<String> ans = new ArrayList<>();
            solve(N - 1, 0, new char[N - 1], ans);

            ansList.add(ans);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ansList.size(); i++){
            Collections.sort(ansList.get(i));
            
            for(int j = 0; j < ansList.get(i).size(); j++){
                sb.append(ansList.get(i).get(j)).append("\n");
            }

            if(i != ansList.size() - 1){
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void solve(int limit, int index, char[] selectOper, List<String> ans){
        if(index == limit) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();

            for(int i = 0; i < limit; i++) {
                sb.append(data[i]);
                sb2.append(data[i]);

                if(selectOper[i] != ' ') {
                    sb.append(selectOper[i]);
                }
                sb2.append(selectOper[i]);
            }

            sb.append(data[limit]);
            sb2.append(data[limit]);

            int res = calc(sb.toString());

            if(res == 0) {
                ans.add(sb2.toString());
            }
            return;
        }

        for(int i = 0; i < oper.length; i++) {
            selectOper[index] = oper[i];
            solve(limit, index + 1, selectOper, ans);
        }
    }

    private static int calc(String str){
        int idx = 0;

        Deque<Integer> number = new ArrayDeque<>();
        Deque<Character> operator = new ArrayDeque<>();
        while(idx < str.length()) {
            StringBuilder sb = new StringBuilder();
            while(idx < str.length() && (str.charAt(idx) >= '0' && str.charAt(idx) <= '9')) {
                sb.append(str.charAt(idx));
                idx++;
            }

            number.addLast(Integer.parseInt(sb.toString()));

            if(idx == str.length()) {
                break;
            }

            operator.addLast(str.charAt(idx));

            idx++;
        }

        while(number.size() > 1 && !operator.isEmpty()) {
            char opt = operator.pollFirst();

            if(opt == '+') {
                int oper1 = number.pollFirst();
                int oper2 = number.pollFirst();

                number.addFirst(oper1 + oper2);
            } else if(opt == '-') {
                int oper1 = number.pollFirst();
                int oper2 = number.pollFirst();

                number.addFirst(oper1 - oper2);
            }
        }

        return number.pollFirst();
    }
}