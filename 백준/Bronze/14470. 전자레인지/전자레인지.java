import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int current = Integer.parseInt(br.readLine());
    int goal = Integer.parseInt(br.readLine());
    int frozenHeat = Integer.parseInt(br.readLine());
    int thaw = Integer.parseInt(br.readLine());
    int notFrozenHeat = Integer.parseInt(br.readLine());

    int ans = 0;

    if (current < 0){
      ans += (-current) * frozenHeat;
      ans += thaw;
      ans += goal * notFrozenHeat;
    } else{
      ans += (goal - current) * notFrozenHeat;
    }

    System.out.println(ans);
  }
}
