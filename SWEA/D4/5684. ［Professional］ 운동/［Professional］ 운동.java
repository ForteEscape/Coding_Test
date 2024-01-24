import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    
	public static List<List<Integer[]>> graph;
    public static int ans;
    public static boolean[] visited;
    
	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(sc.nextLine());
            int V = sc.nextInt();
            int E = sc.nextInt();
            
            graph = new ArrayList<>();
            for(int i = 0; i <= V; i++){
                graph.add(new ArrayList<>());
            }
            
            for(int i = 0; i < E; i++){
                st = new StringTokenizer(sc.nextLine());
                
                int from = sc.nextInt();
                int to = sc.nextInt();
                int cost = sc.nextInt();
                
                graph.get(from).add(new Integer[]{to, cost});
            }
            
            ans = Integer.MAX_VALUE;
            for(int i = 1; i <= V; i++){
                visited = new boolean[V + 1];
                visited[i] = true;
                solve(i, i, 0);
            }
            
            System.out.println("#" + test_case + " " + ans);
		}
	}
    
    public static  void solve(int startNode, int currentNode, int currentCost){
        
        for(int i = 0; i < graph.get(currentNode).size(); i++){
            Integer[] adjNode = graph.get(currentNode).get(i);
            
            if(!visited[adjNode[0]]){
                if(currentCost + adjNode[1] < ans){
                	visited[adjNode[0]] = true;
                    solve(startNode, adjNode[0], currentCost + adjNode[1]);
                }
            } else {
                if(adjNode[0] == startNode){
                    ans = Math.min(ans, currentCost + adjNode[1]);
                }
            }
        }
    }
}