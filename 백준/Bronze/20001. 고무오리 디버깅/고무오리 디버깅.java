import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String start = br.readLine();

    Deque<String> stack = new ArrayDeque<>();

    while(true){
      String command = br.readLine();

      if (command.equals("고무오리 디버깅 끝")) break;
      if (command.equals("문제")){
        stack.add("문제");
      }

      if (command.equals("고무오리")){
        if (stack.isEmpty()){
          stack.addLast("문제");
          stack.addLast("문제");
        } else{
          stack.pollLast();
        }
      }
    }

    if (stack.isEmpty()){
      System.out.println("고무오리야 사랑해");
    } else{
      System.out.println("힝구");
    }
  }
}
