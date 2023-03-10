class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String[] map1 = new String[n];
        String[] map2 = new String[n];
        
        for(int i = 0; i < n; i++){
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            
            String arr1BinaryString = Integer.toBinaryString(arr1[i]);
            String arr2BinaryString = Integer.toBinaryString(arr2[i]);
            
            for(int j = 0; j < n - arr1BinaryString.length(); j++){
                sb1.append("0");
            }
            sb1.append(arr1BinaryString);
            
            for(int j = 0; j < n - arr2BinaryString.length(); j++){
                sb2.append("0");
            }
            sb2.append(arr2BinaryString);
            
            
            map1[i] = sb1.toString();
            map2[i] = sb2.toString();
        }
        
        for(int i = 0; i < n; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < map1[i].length(); j++){
                if(map1[i].charAt(j) == '1' || map2[i].charAt(j) == '1'){
                    sb.append("#");
                } else if(map1[i].charAt(j) == '0' && map2[i].charAt(j) == '0'){
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}