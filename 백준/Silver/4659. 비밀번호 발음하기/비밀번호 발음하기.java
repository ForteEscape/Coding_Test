import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Set<Character> set = new HashSet<>(List.of('a', 'e', 'i', 'o', 'u'));

    while(true){
      String testData = br.readLine();

      if (testData.equals("end")) break;

      boolean flag = false;
      boolean isContainsVowels = false;

      char prevPrevCharacter = '0';
      char prevCharacter = '0';

      for (int i = 0; i < testData.length(); i++){
        char currentCharacter = testData.charAt(i);

        if (set.contains(currentCharacter)){
          isContainsVowels = true;
        }

        if (currentCharacter == prevCharacter){
          if (!List.of('e', 'o').contains(currentCharacter)){
            flag = true;
            break;
          }
        }

        if (prevPrevCharacter == '0' || prevCharacter == '0'){
          prevPrevCharacter = prevCharacter;
          prevCharacter = currentCharacter;
          continue;
        }

        if (set.contains(prevPrevCharacter) && set.contains(prevCharacter) && set.contains(currentCharacter)){
          flag = true;
          break;
        } else if (!set.contains(prevPrevCharacter) && !set.contains(prevCharacter) && !set.contains(currentCharacter)){
          flag = true;
          break;
        }

        prevPrevCharacter = prevCharacter;
        prevCharacter = currentCharacter;
      }

      if (flag || !isContainsVowels){
        System.out.println("<" + testData + "> is not acceptable.");
      } else{
        System.out.println("<" + testData + "> is acceptable.");
      }
    }
  }
}
