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

/**
 * 완탐
 * 모든 지점에서 다 dfs 돌리기
 */

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N,M;
    static ArrayList<Integer> G[];
    static boolean[] visit;
    
    public static void dfs(int nowNode) throws Exception {
    	visit[nowNode] = true;
    	bw.write(nowNode+" ");
    	for(int i=0;i<G[nowNode].size();i++) {
    		int nextNode = G[nowNode].get(i);
    		if(!visit[nextNode]) {
    			dfs(nextNode);
    		}
    	}
    }
    
    public static void bfs(int start) throws Exception {
    	Queue<Integer> q = new LinkedList<>();
    	q.offer(start);
    	visit[start] = true;
    	
    	while(!q.isEmpty()) {
    		int nowNode = q.poll();
    		bw.write(nowNode+" ");
    		
    		for(int i=0;i<G[nowNode].size();i++) {
    			int nextNode = G[nowNode].get(i);
        		if(!visit[nextNode]) {
        			visit[nextNode] = true;
        			q.offer(nextNode);
        		}
    		}
    	}
    	
    }

    public static void main(String[] args) throws Exception {
    	st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
         
        visit = new boolean[N+1];
        G = new ArrayList[N+1];
        for(int i=0;i<N+1;i++) {
        	G[i] = new ArrayList<>();
        }
        
        for(int i=0;i<M;i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	G[a].add(b);
        	G[b].add(a);
        }
        
        for(int i=0;i<=N;i++) {
        	Collections.sort(G[i]);
        }
        
        dfs(V);
        Arrays.fill(visit, false);
        bw.write("\n");	
        bfs(V);        

        bw.close();
    }    

}

/**
*/