import java.util.*;

class Solution {
    static class Song{
        int uid;
        int playCount;
        
        Song(int uid, int playCount){
            this.uid = uid;
            this.playCount = playCount;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, PriorityQueue<Song>> album = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
            
            if(!album.containsKey(genres[i])){
                album.put(genres[i], new PriorityQueue<>((x, y) -> (y.playCount - x.playCount) == 0 ? x.uid - y.uid : y.playCount - x.playCount));
            }
            album.get(genres[i]).offer(new Song(i, plays[i]));
        }
        
        PriorityQueue<String[]> genreChart = new PriorityQueue<>((x, y) -> (Integer.parseInt(y[1]) - Integer.parseInt(x[1])));
        
        for(String key : map.keySet()){
            String[] data = {key, Integer.toString(map.get(key))};
            
            genreChart.offer(data);
        }
        
        // ArrayList 로 넣은 뒤  int[]로 porting
        ArrayList<Integer> result = new ArrayList<>();
        
        while(!genreChart.isEmpty()){
            String[] element = genreChart.poll();
            PriorityQueue<Song> rank = album.get(element[0]);
            
            for(int i = 0; i < 2; i++){
                if(rank.isEmpty()) break;
                
                result.add(rank.poll().uid);
            }
        }
        
        int[] answer = new int[result.size()];
        int idx = 0;
        
        for(int element : result){
            answer[idx++] = element;
        }
        
        return answer;
    }
}