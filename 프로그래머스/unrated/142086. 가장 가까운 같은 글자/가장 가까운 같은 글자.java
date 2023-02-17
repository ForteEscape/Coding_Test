class Solution {
    public int[] solution(String s) {
        int[] alphabet = new int[26];
        int len = s.length();
        int[] answer = new int[len];
        
        for(int i = 0; i < len; i++){
            int idx = s.charAt(i) - 97;
            
            if(alphabet[idx] == 0){
                answer[i] = -1;
                alphabet[idx]++;
            } else{
                for(int j = i - 1; j >= 0; j--){
                    if (s.charAt(i) == s.charAt(j)){
                        answer[i] = i - j;
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
}