class Solution {
    public int solution(String[] spell, String[] dic) {
        int answer = 0;
        int[] result = new int[2];
        
        for(String data: dic){
            int[] alphabet = new int[26];
            boolean flag = true;
            
            if(data.length() > spell.length){
                result[1]++;
                continue;
            }
            
            for(int i = 0; i < data.length(); i++){
                alphabet[data.charAt(i) - 'a']++;
            }
            
            for(int i = 0; i < spell.length; i++){
                if(alphabet[spell[i].charAt(0) - 'a'] != 1){
                    flag = false;
                    break;
                }
            }
            
            if(flag){
                result[0]++;
            } else{
                result[1]++;
            }
        }
        
        answer = result[0] == 0 ? 2 : 1;
        
        return answer;
    }
}