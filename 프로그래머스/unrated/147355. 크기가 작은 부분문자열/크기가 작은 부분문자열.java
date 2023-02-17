class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        long comp = Long.parseLong(p);
        
        for(int i = 0; i <= t.length() - p.length(); i++){
            String element = "";
            for(int j = i; j < (i + p.length()); j++){
                element += t.charAt(j);
            }
            
            long result = Long.parseLong(element);
            if(result <= comp){
                answer++;
            }
        }
        
        return answer;
    }
}