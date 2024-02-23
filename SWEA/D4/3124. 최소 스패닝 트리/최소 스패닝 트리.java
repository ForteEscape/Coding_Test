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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.print.StreamPrintService;

/**
 * 
 */

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int V,E;
    static ArrayList<Node> G[];
    static boolean[] visit;
    
    public static long prim(int start) {
    	long weightSum = 0L;
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	pq.add(new Node(start,0));
    	
    	while(!pq.isEmpty()) {
    		Node nowNode = pq.poll();
    		if(visit[nowNode.n]) continue;
    		weightSum += nowNode.weight;
    		visit[nowNode.n] = true;
    		//System.out.println("nowNode: "+nowNode.n);
    		
    		for(int i=0;i<G[nowNode.n].size();i++){
    			Node nextNode = G[nowNode.n].get(i);
    			if(!visit[nextNode.n]) {
    				pq.add(nextNode);
    			}
    		}
    	}
    	
    	return weightSum;
    }
  
    public static void main(String[] args) throws Exception {
    	
    	int T = Integer.parseInt(br.readLine());
    	
    	for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            long answer;
            
            visit = new boolean[V+1];
            G = new ArrayList[V+1];
            for(int i=0;i<=V;i++) {
            	G[i] = new ArrayList<Node>();
            }
            
            for(int i=0;i<E;i++) {
                st = new StringTokenizer(br.readLine(), " ");
            	int a = Integer.parseInt(st.nextToken());
            	int b = Integer.parseInt(st.nextToken());
            	int c = Integer.parseInt(st.nextToken());
            	G[a].add(new Node(b,c));
            	G[b].add(new Node(a,c));
            }
            
            answer = prim(1);
            
            bw.write("#"+t+" "+answer+"\n");	
    	}
        bw.close();
    }    
    
    static class Node implements Comparable<Node>{
    	int n;
    	int weight;    	    	
    	
		public Node(int n, int weight) {
			this.n = n;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [n=" + n + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Node o) {		
			return this.weight - o.weight;
		}
    }


}

/**
9
1 2 3 4 5 6 7 8 9
2 2 4
4 1 3 5 4
4 2 5 8 7
4 6 9 1 2
2 2 3
1 4
1 3
1 3
1 4
답: 1(3, 5, 7, 8 / 1, 2, 4, 6, 9)
*/