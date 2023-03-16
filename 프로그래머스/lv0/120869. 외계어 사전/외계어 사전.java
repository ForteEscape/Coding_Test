class Solution {
    public int solution(String[] spell, String[] dic) {
        int answer = 0;
        
        for(String data: dic){
            boolean flag = true;
            
            if(data.length() == spell.length){
                for(String element: spell){
                    if(!data.contains(element)){
                        flag = false;
                        break;
                    }
                }
                if(flag) return 1;
            } 
        }
        return 2;
    }
}