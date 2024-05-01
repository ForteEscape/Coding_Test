import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int max = -1;
        int maxY = -1;
        int maxX = -1;

        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int element = Integer.parseInt(st.nextToken());

                if (max < element) {
                    max = element;
                    maxY = i + 1;
                    maxX = j + 1;
                }
            }
        }

        System.out.println(max);
        System.out.println(maxY + " " + maxX);
    }
}