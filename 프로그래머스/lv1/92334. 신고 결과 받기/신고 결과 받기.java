import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Set<String>> map = new HashMap<>();
        Map<String, Integer> reportMap = new HashMap<>();
        Map<String, Integer> indexMap = new HashMap<>();
        Set<String> blockedUser = new HashSet<>();

        for(int i = 0; i < id_list.length; i++){
            indexMap.put(id_list[i], i);
            map.put(id_list[i], new HashSet<>());
        }

        for(String element: report){
            StringTokenizer st = new StringTokenizer(element);
            String user = st.nextToken();
            String reportedUser = st.nextToken();

            if(!map.get(user).contains(reportedUser)){
                map.get(user).add(reportedUser);
                reportMap.put(reportedUser, reportMap.getOrDefault(reportedUser, 0) + 1);
            }

            if(reportMap.get(reportedUser) >= k){
                blockedUser.add(reportedUser);
            }
        }

        for(String blockUser: blockedUser){
            for(String id: id_list){
                if(map.get(id).contains(blockUser)){
                    answer[indexMap.get(id)]++;
                }
            }
        }

        return answer;
    }
}