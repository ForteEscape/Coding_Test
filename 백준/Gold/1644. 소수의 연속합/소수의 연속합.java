import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> primeList = generatePrime(N);

        int start = 0;
        int end = 0;
        int result = 2;
        int size = primeList.size();

        long ans = 0;

        while(start < size && end < size){
            if (result >= N){
                if (result == N) {
                    ans++;
                }

                result -= primeList.get(start++);
            } else {
                if (end == size - 1){
                    break;
                }

                end++;
                result += primeList.get(end);
            }
        }

        System.out.println(ans);
    }

    public static List<Integer> generatePrime(int N){
        int[] temp = new int[N + 1];

        for (int i = 2; i <= N; i++){
            temp[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(N); i++){
            if(temp[i] != 0){
                for (int j = i * 2; j <= N; j += i){
                    temp[j] = 0;
                }
            }
        }

        List<Integer> primeList = new ArrayList<>();
        for (int element: temp){
            if (element != 0){
                primeList.add(element);
            }
        }

        return primeList;
    }
}
