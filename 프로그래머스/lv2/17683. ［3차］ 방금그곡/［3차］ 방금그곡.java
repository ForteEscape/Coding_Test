import java.util.*;

class Solution {
    static class Song{
        String name;
        int playTime;
        
        Song(String name, int playTime){
            this.name = name;
            this.playTime = playTime;
        }
    }
    
    public String solution(String m, String[] musicinfos) {
        Song answer = new Song("(None)", -1);
        
        m = changeMelody(m);
        
        for(int i = 0; i < musicinfos.length; i++){
            StringTokenizer st = new StringTokenizer(musicinfos[i], ",");
            int startMin = 0;
            int endMin = 0;
            String name = "";
            String melody = "";
            
            StringTokenizer time = new StringTokenizer(st.nextToken(), ":");
            startMin += Integer.parseInt(time.nextToken()) * 60;
            startMin += Integer.parseInt(time.nextToken());
            
            time = new StringTokenizer(st.nextToken(), ":");
            endMin += Integer.parseInt(time.nextToken()) * 60;
            endMin += Integer.parseInt(time.nextToken());
            
            name = st.nextToken();
            melody = changeMelody(st.nextToken());
            
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < endMin - startMin; j++){
                sb.append(melody.charAt(j % melody.length()));
            }
            
            if(sb.toString().contains(m)){
                if(answer.playTime < endMin - startMin){
                    answer = new Song(name, endMin - startMin);
                }
            }
            
        }
        return answer.name;
    }
    
    public static String changeMelody(String melody){
        String newMelody;
        
        melody = melody.replace("C#", "c");
        melody = melody.replace("D#", "d");
        melody = melody.replace("F#", "f");
        melody = melody.replace("G#", "g");
        newMelody = melody.replace("A#", "a");
        
        return newMelody;
    }
}