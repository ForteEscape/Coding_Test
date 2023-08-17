class Solution {
    
    public static int[] usePickCount;
    public static int totalPick;
    public static int minAp = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        usePickCount = new int[picks.length];
        
        totalPick = 0;
        for(int i = 0; i < picks.length; i++){
            totalPick += picks[i];
        }
        
        for(int i = 0; i < picks.length; i++){
            if(picks[i] == usePickCount[i]){
                continue;
            }
            
            usePickCount[i]++;
            solve(picks, i, 0, 1, 0, minerals);
            usePickCount[i]--;
        }
        
        return minAp;
    }
    
    public void solve(int[] picks, int currentPick, int ap, int usedPick, int currentMineralIdx, String[] minerals){
        if(minAp <= ap || currentMineralIdx >= minerals.length){
            return;
        }
        
        int additionalAp = 0;
        
        for(int i = currentMineralIdx; i < currentMineralIdx + 5; i++){
            if(i >= minerals.length) break;
            
            if(currentPick == 1){
                additionalAp += minerals[i].equals("diamond") ? 5 : 1;
            } else if(currentPick == 2){
                if(minerals[i].equals("diamond")){
                    additionalAp += 25;
                } else if(minerals[i].equals("iron")){
                    additionalAp += 5;
                } else {
                    additionalAp += 1;
                }
            } else{
                additionalAp += 1;
            }
        }
        
        if(usedPick >= totalPick || currentMineralIdx + 5 >= minerals.length){
            minAp = Math.min(minAp, ap + additionalAp);
            return;
        }
        
        for(int i = 0; i < picks.length; i++){
            if(picks[i] == usePickCount[i]){
                continue;
            }
            
            usePickCount[i]++;
            solve(picks, i, ap + additionalAp, usedPick + 1, currentMineralIdx + 5, minerals);
            usePickCount[i]--;
        }
    }
}