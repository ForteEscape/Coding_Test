import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> combination;
    
    public int solution(int[] number) {
        int answer = 0;
        combination = new ArrayList<>();
        boolean[] visited = new boolean[number.length];
        
        getCombination(number, 0, number.length, 3, visited);
        
        for(ArrayList<Integer> row: combination){
            int result = 0;
            for(int element: row){
                result += element;
            }
            
            if(result == 0){
                answer++;
            }
        }
        
        return answer;
    }
    
    public static void getCombination(int number[], int depth, int n, int r, boolean[] visited){
        if(r == 0){
            ArrayList<Integer> temp = new ArrayList<>();
            
            for(int i = 0; i < n; i++){
                if(visited[i]){
                    temp.add(number[i]);
                }
            }
            
            combination.add(temp);
            return;
        }
        
        if(depth == n){
            return;
        }
        
        visited[depth] = true;
        getCombination(number, depth + 1, n, r - 1, visited);
        
        visited[depth] = false;
        getCombination(number, depth + 1, n, r, visited);
    }
}