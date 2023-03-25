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

            // 설치한 공유기 개수가 있는 공유기 개수보다 적다면 더 적은 반경으로 해도 된다.
            if (getInstallCnt(mid) < M){
                end = mid;
            } else{
                front = mid + 1;
            }
        }

        System.out.println(front - 1);
    }

    // 해당 와이파이 범위만큼 조정한 상태에서 설치를 한다면 몇 개를 설치할 수 있는가를 구하는 메서드
    static int getInstallCnt(int dist){
        int cnt = 1;
        int prevInstallLocation = house[0];

        for (int i = 1; i < house.length; i++){
            int location = house[i];

            if (location - prevInstallLocation >= dist){
                cnt++;
                prevInstallLocation = house[i];
            }
        }

        return cnt;
    }
}