class Solution {
    public int[] solution(String[] wallpaper) {
        int leftPos = Integer.MAX_VALUE;
        int upPos = Integer.MAX_VALUE;
        int rightPos = Integer.MIN_VALUE;
        int downPos = Integer.MIN_VALUE;
        
        for(int i = 0; i < wallpaper.length; i++){
            for(int j = 0; j < wallpaper[i].length(); j++){
                if(wallpaper[i].charAt(j) == '#'){
                    leftPos = Math.min(leftPos, j);
                    upPos = Math.min(upPos, i);
                    rightPos = Math.max(rightPos, j + 1);
                    downPos = Math.max(downPos, i + 1);
                }
            }
        }
        
        return new int[]{upPos, leftPos, downPos, rightPos};
    }
}