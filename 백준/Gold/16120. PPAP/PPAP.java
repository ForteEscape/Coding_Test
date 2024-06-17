import java.util.*;
import java.io.*;

public class Main {
    
    public static final String PPAP = "PPAP";
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String data = br.readLine();
        
        if(data.length() < 4) {
            if(data.equals("P")) {
                System.out.println(PPAP);
            } else {
                System.out.println("NP");   
            }
            return;
        }
        
        if(data.length() == 4) {
            if(PPAP.equals(data)) {
                System.out.println(PPAP);
            } else {
                System.out.println("NP");
            }
            
            return;
        }
        
        Deque<Character> stack = new ArrayDeque<>();
        char[] dataArr = data.toCharArray();
        
        for(int i = 0; i < dataArr.length; i++) {
            stack.addLast(dataArr[i]);
            
            if(stack.size() >= 4) {
                StringBuilder sb = new StringBuilder();
                
                for(int j = 0; j < 4; j++) {
                    sb.append(stack.pollLast());
                }
                
                String result = sb.toString();
                
                if("PAPP".equals(result)) {
                    stack.addLast('P');
                } else {
                    for(int j = 3; j >= 0; j--) {
                        stack.addLast(result.charAt(j));
                    }
                }
            }
        }
        
        if(stack.size() == 1) {
            System.out.println(PPAP);
        } else {
            System.out.println("NP");
        }
    }
}
