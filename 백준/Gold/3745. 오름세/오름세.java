import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = null;
        
        while((input = br.readLine()) != null) {
            int N = Integer.parseInt(input.trim());
            int [] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());   
            }
            
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(arr[0]);
            for (int i = 1; i < N; i++) {
                int comp = list.get(list.size()-1);
                if (comp < arr[i]) {
                    list.add(arr[i]);   
                }
                else {
                    int start = 0;
                    int end = list.size()-1;
                    int mid = 0;
                    while(start <= end) {
                        mid = (start + end) / 2;
                        int cur = list.get(mid);
                        if (arr[i] < cur) {
                            end = mid - 1;   
                        }
                        else if (arr[i] == cur) {
                            break;   
                        }
                        else {
                            start = mid + 1;   
                            mid++;
                        }
                    }
                    list.set(mid, arr[i]);
                }
            }
            sb.append(list.size()).append("\n");
        }
        System.out.print(sb);
    }
}