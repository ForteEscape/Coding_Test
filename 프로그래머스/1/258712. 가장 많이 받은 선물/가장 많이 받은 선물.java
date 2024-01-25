import java.util.*;

class Solution {
    
    public static Map<String, Map<String, Integer>> giftAmount;
    public static Map<String, Integer[]> giftRate;
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        giftAmount = new HashMap<>();
        giftRate = new HashMap<>();
        
        for(String friend : friends){
            giftAmount.put(friend, new HashMap<>());
            giftRate.put(friend, new Integer[]{0, 0}); // 준거, 받은거
        }
        
        for(String element: gifts){
            StringTokenizer st = new StringTokenizer(element);
            
            String sender = st.nextToken();
            String reciever = st.nextToken();
            
            giftAmount
                .get(sender)
                .put(reciever, giftAmount.get(sender).getOrDefault(reciever, 0) + 1);
            
            giftAmount
                .get(reciever)
                .put(sender, giftAmount.get(reciever).getOrDefault(sender, 0));
            
            giftRate.get(sender)[0]++;
            giftRate.get(reciever)[1]++;
        }
        
        Map<String, Integer> amount = new HashMap();
        
        for(int i = 0; i < friends.length; i++){
            String tgt = friends[i];
            
            for(int j = 0; j < friends.length; j++){
                if(i == j) continue;
                
                if(giftAmount.get(tgt).containsKey(friends[j])){ // 기록이 존재한다면
                    int senderAmount = giftAmount.get(tgt).get(friends[j]);
                    int recieverAmount = giftAmount.get(friends[j]).get(tgt);
                    
                    if(senderAmount < recieverAmount){
                        amount.put(friends[j], amount.getOrDefault(friends[j], 0) + 1);
                    } else if(senderAmount > recieverAmount){
                        amount.put(tgt, amount.getOrDefault(tgt, 0) + 1);
                    } else {
                        int result = getRate(tgt, friends[j]);
                        if(result == 1){ // tgt > friends[j]
                            amount.put(tgt, amount.getOrDefault(tgt, 0) + 1);
                        } else if (result == -1){ // tgt < friends[j]
                            amount.put(friends[j], amount.getOrDefault(friends[j], 0) + 1);
                        }
                    }
                } else {
                    int result = getRate(tgt, friends[j]);
                    if(result == 1){ // tgt > friends[j]
                        amount.put(tgt, amount.getOrDefault(tgt, 0) + 1);
                    } else if (result == -1){ // tgt < friends[j]
                        amount.put(friends[j], amount.getOrDefault(friends[j], 0) + 1);
                    }
                }
            }
        }
        
        for(String key : amount.keySet()){
            answer = Math.max(answer, amount.get(key));
        }
        
        return answer / 2;
    }
    
    public static int getRate(String tgt, String other){
        int tgtRate = giftRate.get(tgt)[0] - giftRate.get(tgt)[1];
        int otherRate = giftRate.get(other)[0] - giftRate.get(other)[1];
        
        if(tgtRate > otherRate){
            return 1;
        } else if(tgtRate < otherRate){
            return -1;
        }
        
        return 0;
    }
}