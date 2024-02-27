import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.print.StreamPrintService;


public class Main {
	
	/**
	 * dp[0] : 0
	 * dp[1] : 2
	 * dp[2] : 5
	 * dp[3] : dp[1] + dp[2]*2 = 2+10 = 12
	 */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int V,E,K;
    static int[] dis = new int[20002];
    static boolean[] visited = new boolean[20002];
    static ArrayList<Point>[] G = new ArrayList[20002];
    
    static void BFS() {        
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				return o1.x - o2.x;
			}
		}); //가중치, 정점 오름차순 정렬
        pq.offer(new Point(0, K)); //처음

        while (!pq.isEmpty()) {
            int w = pq.peek().x;
            int v = pq.peek().y;
            pq.poll();
            
            visited[v] = true;

            for (int i = 0; i < G[v].size(); i++) {
                int next = G[v].get(i).x;
                int nextW = G[v].get(i).y;

                if (w + nextW < dis[next]) { //최단경로 수정
                    dis[next] = w + nextW;
                    pq.offer(new Point(dis[next], next));
                }
            }
        }
    }
   
    public static void main(String[] args) throws Exception {
    	st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        
        Arrays.fill(dis, 987654321);

        for(int i=0;i<20002;i++) {
        	G[i] = new ArrayList<>();
        }
        
        int u,v,w;
        for(int i=0;i<E;i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	u = Integer.parseInt(st.nextToken());
        	v = Integer.parseInt(st.nextToken());
        	w = Integer.parseInt(st.nextToken());
        	
        	G[u].add(new Point(v,w));
        }
        
        dis[K] = 0;
        visited[K] = true;
        
        BFS();
        
        for(int i=1;i<=V;i++) {
        	if(dis[i] == 987654321) bw.write("INF\n");
        	else bw.write(dis[i]+"\n");
        }      

        bw.close();
    }    

}

/**
*/