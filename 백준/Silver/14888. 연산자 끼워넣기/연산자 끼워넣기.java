import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int number;
    static int[] numberData;
    static int[] operatorData;
    static String[] operator = {"+", "-", "*", "/"};
    static int maxAns = Integer.MIN_VALUE;
    static int minAns = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        number = Integer.parseInt(br.readLine());
        numberData = new int[number];
        operatorData = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < number; i++){
            numberData[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++){
            operatorData[i] = Integer.parseInt(st.nextToken());
        }

        LinkedList<String> formula = new LinkedList<>();

        solve(0, formula);

        System.out.println(maxAns);
        System.out.println(minAns);
    }

    public static void solve(int length, LinkedList<String> formula){
        if (length == number - 1){
            // calculate expression data
            int result = numberData[0];
            int idx = 1;

            for (int i = 0; i < formula.size(); i++){
                String operator = formula.get(i);

                if (operator.equals("+")){
                    result += numberData[idx++];
                } else if (operator.equals("-")){
                    result -= numberData[idx++];
                } else if (operator.equals("*")){
                    result *= numberData[idx++];
                } else if (operator.equals("/")){
                    if (result < 0){
                        result *= -1;
                        result = result / numberData[idx++];
                        result *= -1;
                    } else{
                        result = result / numberData[idx++];
                    }
                }
            }

            maxAns = Math.max(maxAns, result);
            minAns = Math.min(minAns, result);

            return;
        }

        for (int i = 0; i < 4; i++){
            if(operatorData[i] != 0){
                formula.addLast(operator[i]);
                operatorData[i]--;
                solve(length + 1, formula);
                operatorData[i]++;
                formula.pollLast();
            }
        }
    }
}