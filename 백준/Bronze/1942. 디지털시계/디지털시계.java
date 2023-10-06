import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    for (int i = 0; i < 3; i++){
      StringTokenizer st = new StringTokenizer(br.readLine());

      int[] startTime = new int[3];
      int[] endTime = new int[3];

      inputData(startTime, st.nextToken());
      inputData(endTime, st.nextToken());

      int ans = 0;

      while(true){
        if (chk(startTime, endTime)){
          if ((startTime[0] + startTime[1] + startTime[2]) % 3 == 0){
            ans++;
          }
          break;
        }

        if ((startTime[0] + startTime[1] + startTime[2]) % 3 == 0){
          ans++;
        }

        startTime[2]++;
        if (startTime[2] >= 60){
          startTime[2] = 0;
          startTime[1]++;
        }

        if (startTime[1] >= 60){
          startTime[1] = 0;
          startTime[0]++;
        }

        if (startTime[0] >= 24){
          startTime[0] = 0;
        }
      }

      System.out.println(ans);
    }
  }

  public static void inputData(int[] dateTime, String data){
    StringTokenizer st = new StringTokenizer(data, ":");
    dateTime[0] = Integer.parseInt(st.nextToken());
    dateTime[1] = Integer.parseInt(st.nextToken());
    dateTime[2] = Integer.parseInt(st.nextToken());
  }

  public static boolean chk(int[] date1, int[] date2){
    for (int i = 0; i < 3; i++){
      if (date1[i] != date2[i]){
        return false;
      }
    }

    return true;
  }
}
