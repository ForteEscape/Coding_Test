import java.util.*;

class Solution {
    static class Edge{
        int to;
        int weight;
        
        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
    
    static int[] dist;
    static ArrayList<ArrayList<Edge>> graph;
    final static int INF = Integer.MAX_VALUE;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = new ArrayList<>();
        dist = new int[n + 1];
        
        for(int i = 0; i < n + 1; i++){
            graph.add(new ArrayList<>());
            dist[i] = INF;
        }
        
        // graph construction
        for(int i = 0; i < roads.length; i++){
            graph.get(roads[i][0]).add(new Edge(roads[i][1], 1));
            graph.get(roads[i][1]).add(new Edge(roads[i][0], 1));
        }
        
        dijkstra(destination);
        
        int[] answer = new int[sources.length];
        int idx = 0;
        for(int element: sources){
            if(dist[element] == INF){
                answer[idx++] = -1;
            } else{
                answer[idx++] = dist[element];
            }
        }
        
        return answer;
    }
    
    static void dijkstra(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> (x.weight - y.weight));
        pq.offer(new Edge(start, 0));
        dist[start] = 0;
        
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            
            if(dist[cur.to] < cur.weight){
                continue;
            }
            
            for(int i = 0; i < graph.get(cur.to).size(); i++){
                Edge adjEdge = graph.get(cur.to).get(i);
                
                if(dist[adjEdge.to] > adjEdge.weight + cur.weight){
                    dist[adjEdge.to] = adjEdge.weight + cur.weight;
                    pq.offer(new Edge(adjEdge.to, dist[adjEdge.to]));
                }
            }
        }
    }
}