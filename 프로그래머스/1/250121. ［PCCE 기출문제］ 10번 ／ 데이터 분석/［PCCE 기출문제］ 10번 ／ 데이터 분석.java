import java.util.*;

class Solution {
    
    static class Data {
        int code;
        int date;
        int maximum;
        int remain;
        
        Data(int code, int date, int maximum, int remain) {
            this.code = code;
            this.date = date;
            this.maximum = maximum;
            this.remain = remain;
        }
    }
    
    List<Data> targetList;
    static Map<String, Integer> map;
    
    static {
        map = new HashMap<>();
        map.put("code", 0);
        map.put("date", 1);
        map.put("maximum", 2);
        map.put("remain", 3);
    }
    
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        targetList = new ArrayList<>();
        
        for(int i = 0; i < data.length; i++) {
            if(data[i][map.get(ext)] <= val_ext) {
                targetList.add(new Data(data[i][0], data[i][1], data[i][2], data[i][3]));
            }
        }
        
        if(map.get(sort_by) == 0) {
            Collections.sort(targetList, (o1, o2) -> (o1.code - o2.code));
        } else if(map.get(sort_by) == 1) {
            Collections.sort(targetList, (o1, o2) -> (o1.date - o2.date));
        } else if(map.get(sort_by) == 2) {
            Collections.sort(targetList, (o1, o2) -> (o1.maximum - o2.maximum));
        } else if(map.get(sort_by) == 3) {
            Collections.sort(targetList, (o1, o2) -> (o1.remain - o2.remain));
        }
        
        int[][] answer = new int[targetList.size()][4];
        for(int i = 0; i < targetList.size(); i++) {
            answer[i] = new int[]{targetList.get(i).code, targetList.get(i).date, targetList.get(i).maximum, targetList.get(i).remain};
        }
        
        return answer;
    }
}