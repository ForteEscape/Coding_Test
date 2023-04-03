import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] data = new int[N][M];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++){
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++){
            int sum = 0;
            st = new StringTokenizer(br.readLine());

            int startY = Integer.parseInt(st.nextToken()) - 1;
            int startX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;
            int endX = Integer.parseInt(st.nextToken()) - 1;

            int areaY = endY - startY;
            int areaX = endX - startX;

            if (areaY == 0 && areaX == 0){
                System.out.println(data[endY][endX]);
            } else if (areaY == 0){
                for (int j = startX; j <= endX; j++){
                    sum += data[startY][j];
                }
                System.out.println(sum);
            } else if (areaX == 0){
                for (int j = startY; j <= endY; j++){
                    sum += data[j][startX];
                }
                System.out.println(sum);
            } else{
                for (int j = startY; j <= endY; j++){
                    for (int k = startX; k <= endX; k++){
                        sum += data[j][k];
                    }
                }
                System.out.println(sum);
            }
        }
    }
}