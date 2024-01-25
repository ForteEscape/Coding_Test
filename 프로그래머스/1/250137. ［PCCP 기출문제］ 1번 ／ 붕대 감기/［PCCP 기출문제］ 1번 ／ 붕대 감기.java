class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        int healTime = 0;
        int attackIdx = 0;
        int time = 0;
        while(attackIdx < attacks.length){
            time++;
            
            if(attacks[attackIdx][0] == time){
                healTime = 0;
                health -= attacks[attackIdx++][1];
                
                if(health <= 0){
                    return -1;
                }
            } else {
                healTime++;
                health += bandage[1];
                
                if(healTime == bandage[0]){
                    health += bandage[2];
                    healTime = 0;
                }
            }
            
            if(health > maxHealth){
                health = maxHealth;
            }
        }
        
        return health;
    }
}