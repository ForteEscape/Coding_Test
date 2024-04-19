import java.util.*;
import java.io.*;

public class Main {

    static class Circle {
        int y;
        int x;
        int rad;

        Circle(int y, int x, int rad) {
            this.y = y;
            this.x = x;
            this.rad = rad;
        }

        double getDistance(Circle o) {
            return Math.sqrt(((long)(this.y - o.y) * (this.y - o.y)) + ((long)(this.x - o.x) * (this.x - o.x)));
        }

        @Override
        public String toString() {
            return "Circle [y=" + y + ", x=" + x + ", rad=" + rad + "]";
        }

    }

    static int N, farestNode, maxDist;
    static Circle[] circles;
    static List<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        circles = new Circle[N + 1];
        circles[0] = new Circle(0, 0, Integer.MAX_VALUE);
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            circles[i] = new Circle(y, x, r);
        }

        Arrays.sort(circles, Comparator.comparingInt(e -> -e.rad));

        tree = new List[N + 1];
        visited = new boolean[N + 1];

        for(int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        treeConstruct(0);

//        for(int i = 0; i <= N; i++) {
//            System.out.println(tree[i]);
//        }

        farestNode = -1;
        maxDist = Integer.MIN_VALUE;
        visited = new boolean[N + 1];
        dfs(0, 0);

        maxDist = Integer.MIN_VALUE;

        visited = new boolean[N + 1];
        dfs(farestNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int startNode, int cnt) {
        visited[startNode] = true;

        if(maxDist < cnt) {
            maxDist = cnt;
            farestNode = startNode;
        }

        for(int element : tree[startNode]) {
            if(!visited[element]) {
                dfs(element, cnt + 1);
            }
        }
    }

    static void treeConstruct(int currentIdx) {
        visited[currentIdx] = true;

        Circle currentCircle = circles[currentIdx];
        //System.out.println(currentCircle);

        for(int i = currentIdx + 1; i <= N; i++) {
            double d = currentCircle.getDistance(circles[i]);
            double dist = Math.abs(currentCircle.rad - circles[i].rad);

            //System.out.println(i + " " + d + " " + dist);

            if(d < dist && !visited[i]) { // 내부인 경우
                tree[currentIdx].add(i);
                tree[i].add(currentIdx);
                treeConstruct(i);
            }
        }
    }

}