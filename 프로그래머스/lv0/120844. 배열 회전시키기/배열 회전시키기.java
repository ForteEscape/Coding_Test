class Solution {
    public int[] solution(int[] numbers, String direction) {
        int[] answer = new int[numbers.length];
        
        if(direction.equals("left")){
            int tmp = numbers[0];
            
            for(int i = 1, j = 0; i < numbers.length; i++, j++){
                answer[j] = numbers[i];
            }
            answer[numbers.length - 1] = tmp;
        } else{
            int tmp = numbers[numbers.length - 1];
            
            for(int i = 0, j = 1; i < numbers.length - 1; i++, j++){
                answer[j] = numbers[i];
            }
            
            answer[0] = tmp;
        }
        
        return answer;
    }
}