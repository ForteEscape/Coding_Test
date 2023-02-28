class Solution {
    public String solution(String my_string, String letter) {
        StringBuilder answer = new StringBuilder();
        char letter_data = letter.charAt(0);
        
        for(int i = 0; i < my_string.length(); i++){
            char data = my_string.charAt(i);
            if(data != letter_data){
                answer.append(data);
            }
        }
        
        return answer.toString();
    }
}