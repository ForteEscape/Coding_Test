import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Map<Character, Integer> map = new HashMap<>();
    map.put('A', 0);
    map.put('G', 1);
    map.put('C', 2);
    map.put('T', 3);

    char[][] decodeData = {
        {'A', 'C', 'A', 'G'},
        {'C', 'G', 'T', 'A'},
        {'A', 'T', 'C', 'G'},
        {'G', 'A', 'G', 'T'},
    };

    int N = Integer.parseInt(br.readLine());
    String code = br.readLine();

    if (N == 1){
      System.out.println(code);
      return;
    }

    char result = decodeData[map.get(code.charAt(N - 2))][map.get(code.charAt(N - 1))];
    N--;
    while(N > 1){
      result = decodeData[map.get(code.charAt(N - 2))][map.get(result)];
      N--;
    }

    System.out.println(result);
  }
}
