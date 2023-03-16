import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        house = new int[N];

        for(int i = 0; i < N; i++){
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        int front = 1;
        int end = house[N - 1] - house[0] + 1;

        while(front < end){
            int mid = (front + end) / 2;

            if(canInstall(mid) < M){
                end = mid;
            } else{
                front = mid + 1;
            }
        }

        System.out.println(front - 1);
    }

    static int canInstall(int distance){
        int count = 1;
        int lastLocation = house[0];

        for(int i = 1; i < house.length; i++){
            int location = house[i];

            if (location - lastLocation >= distance){
                count++;
                lastLocation = location;
            }
        }

        return count;
    }
}