import java.util.*;

class Solution {
    static class HandLocation{
        int y;
        int x;
        
        HandLocation(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    public String solution(int[] numbers, String hand) {
        HandLocation[] keyPadLocation = new HandLocation[12];
        
        for(int i = 0; i < 12; i++){
            keyPadLocation[i] = new HandLocation(i / 3, i % 3);
        }
        
        HandLocation leftHandLocation = new HandLocation(3, 0);
        HandLocation rightHandLocation = new HandLocation(3, 2);
        
        StringBuilder sb = new StringBuilder();
        for(int number: numbers){
            if(number == 1 || number == 4 || number == 7){
                sb.append("L");
                leftHandLocation = keyPadLocation[number - 1];
            } else if(number == 3 || number == 6 || number == 9){
                sb.append("R");
                rightHandLocation = keyPadLocation[number - 1];
            } else{
                HandLocation dest;
                
                if(number == 0){
                    dest = keyPadLocation[10];
                } else{
                    dest = keyPadLocation[number - 1];
                }
                
                int distFromLeftHand = Math.abs(dest.y - leftHandLocation.y) + Math.abs(dest.x - leftHandLocation.x);
                int distFromRightHand = Math.abs(dest.y - rightHandLocation.y) + Math.abs(dest.x - rightHandLocation.x);
                
                if(distFromLeftHand < distFromRightHand){
                    sb.append("L");
                    leftHandLocation = dest;
                } else if(distFromLeftHand == distFromRightHand){
                    if(hand.equals("left")){
                        sb.append("L");
                        leftHandLocation = dest;
                    } else{
                        sb.append("R");
                        rightHandLocation = dest;
                    }
                } else{
                    sb.append("R");
                    rightHandLocation = dest;
                }
            }
        }
        
        return sb.toString();
    }
}