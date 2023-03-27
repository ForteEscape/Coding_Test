import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> numberQueue = new ArrayDeque<>();
        Deque<String> operQueue = new ArrayDeque<>();

        while(true){
            String data = br.readLine();

            if (data.equals("+") || data.equals("-") || data.equals("*") || data.equals("/")){
                operQueue.addLast(data);
            } else if (data.equals("=")){
                break;
            } else{
                numberQueue.addLast(Integer.parseInt(data));
            }
        }

        int data = numberQueue.pollFirst();
        while(!operQueue.isEmpty() && !numberQueue.isEmpty()){
            String oper = operQueue.pollFirst();
            int number = numberQueue.pollFirst();

            if (oper.equals("+")){
                data += number;
            } else if (oper.equals("-")){
                data -= number;
            } else if (oper.equals("*")){
                data *= number;
            } else if (oper.equals("/")){
                data /= number;
            }
        }

        System.out.println(data);
    }
}