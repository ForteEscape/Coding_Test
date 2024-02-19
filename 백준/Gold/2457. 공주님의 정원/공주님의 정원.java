import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Date implements Comparable<Date>{
        int startDate;
        int endDate;

        Date(int startDate, int endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public int compareTo(Date o) {
            if(this.startDate == o.startDate) {
                return o.endDate - this.endDate;
            } else {
                return this.startDate - o.startDate;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Date[] flowerDate = new Date[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

            flowerDate[i] = new Date(startMonth * 100 + startDay, endMonth * 100 + endDay);
        }

        Arrays.sort(flowerDate);

        int startDate = 301;
        int endDate = 1201;

        int idx = 0;
        int ans = 0;
        int max = 0;

        while(startDate < endDate) {
            boolean flag = false;

            for (int i = idx; i < N; i++) {
                if (startDate < flowerDate[i].startDate) {
                    break;
                }

                if (max < flowerDate[i].endDate) {
                    flag = true;
                    max = flowerDate[i].endDate;
                    idx = i + 1;
                }
            }

            if (flag) {
                startDate = max;
                ans++;
            } else {
                break;
            }
        }

        if (max < endDate) {
            System.out.println(0);
        } else {
            System.out.println(ans);
        }
    }
}