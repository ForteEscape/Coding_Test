import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        Map<String, Integer> reportedCount = new HashMap<>();
        Map<String, List<String>> reportMap = new HashMap<>();
        Map<String, Integer> indexMap = new HashMap<>();
        
        for(int i = 0; i < id_list.length; i++){
            reportedCount.put(id_list[i], 0);
            reportMap.put(id_list[i], new ArrayList<>());
            indexMap.put(id_list[i], i);
        }
        
        Set<String> reportSet = new HashSet<>();        
        for(String element: report){
            reportSet.add(element);
        }
        
        for(String element: reportSet){
            StringTokenizer st = new StringTokenizer(element);
            String reportUser = st.nextToken();
            String reportedUser = st.nextToken();
            
            reportMap.get(reportUser).add(reportedUser);
            reportedCount.put(reportedUser, reportedCount.get(reportedUser) + 1);
        }
        
        for(String id: id_list){
            if(reportedCount.get(id) >= k){
                for(String key: reportMap.keySet()){
                    if(reportMap.get(key).contains(id)){
                        answer[indexMap.get(key)]++;
                    }
                }
            }
        }
        
        return answer;
    }
}