import java.util.*;
import java.io.*;

public class Main {
	
	static class Node {
        int x, y;
        long w;

        public Node(int x, int y, long w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
	
    static long[][] map;

    static long[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Character type;
        int K, W, H;
        long w;
        int sx, sy;
        sx = sy = 0;
        Map<Character, Long> costMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new long[H][W];
            dist = new long[H][W];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                type = st.nextToken().charAt(0);
                w = Long.parseLong(st.nextToken());

                costMap.put(type, w);
            }

            String line;
            for (int y = 0; y < H; y++) {
                line = br.readLine();
                for (int x = 0; x < W; x++) {
                    Character key = line.charAt(x);

                    if (key.equals('E')) {
                        sx = x;
                        sy = y;
                        map[y][x] = 0;
                        continue;
                    }

                    map[y][x] = costMap.get(key);
                }
            }

            long result = dijkstra(sx, sy, W, H);
            sb.append(result).append("\n");
            costMap.clear();
        }

        System.out.print(sb);
        br.close();
    }

    static void initDist() {
        for (long[] longs : dist) Arrays.fill(longs, Long.MAX_VALUE);
    }


    static long dijkstra(int sx, int sy, int W, int H) {
        initDist();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.w));
        dist[sy][sx] = 0;
        pq.offer(new Node(sx, sy, dist[sy][sx]));

        long minCost = Long.MAX_VALUE;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int nx, ny;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.y][cur.x] < cur.w)
                continue;

            for (int i = 0; i < dx.length; i++) {
                nx = cur.x + dx[i];
                ny = cur.y + dy[i];

                if (nx < 0 || nx >= W || ny < 0 || ny >= H) {
                    minCost = Math.min(minCost, cur.w);
                    continue;
                }

                if (dist[ny][nx] > dist[cur.y][cur.x] + map[ny][nx]) {
                    dist[ny][nx] = dist[cur.y][cur.x] + map[ny][nx];
                    pq.offer(new Node(nx, ny, dist[ny][nx]));
                }
            }

        }

        return minCost;
    }
}