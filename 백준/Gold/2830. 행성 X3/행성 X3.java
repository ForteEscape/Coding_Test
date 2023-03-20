import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] bitArr = new int[20];

        for (int i = 0; i < N; i++){
            int data = Integer.parseInt(br.readLine());

            for (int j = 0; j < 20; j++){
                if ((data & (1 << j)) == (1 << j)){
                    bitArr[j]++;
                }
            }
        }

        long answer = 0;

        for (int i = 0; i < 20; i++){
            answer += (1L << i) * (bitArr[i]) * (N - bitArr[i]);
        }
        System.out.println(answer);
    }
}