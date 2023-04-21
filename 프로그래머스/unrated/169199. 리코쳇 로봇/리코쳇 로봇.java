import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int n, m;

    static class Location {
        int y;
        int x;
        int depth;

        public Location(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    public int solution(String[] board) {
        int answer = 0;

        n = board.length;
        m = board[0].length();

        Location robot = null;
        Location goal = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = board[i].charAt(j);

                if (ch == 'R') {
                    robot = new Location(i, j, 0);
                } else if (ch == 'G') {
                    goal = new Location(i, j, 0);
                }
            }
        }

        answer = bfs(board, robot, goal);

        return answer;
    }

    private int bfs(String[] board, Location robot, Location goal) {
        Deque<Location> queue = new ArrayDeque<>();
        queue.addLast(robot);
        boolean[][] visited = new boolean[n][m];
        visited[robot.x][robot.y] = true;

        while (!queue.isEmpty()) {
            Location cur = queue.pollFirst();

            if (cur.x == goal.x && cur.y == goal.y) {
                return cur.depth;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x;
                int ny = cur.y;

                // 범위를 벗어나거나 장애물을 만날 때 까지 반복
                while (inRange(nx, ny) && board[nx].charAt(ny) != 'D') {
                    nx += dx[i];
                    ny += dy[i];
                }

                // 범위를 벗어나거나 장애물 만나기 '직전'의 상태
                nx -= dx[i];
                ny -= dy[i];

                // 방문을 하거나 같은 위치일 경우 스킵
                if (visited[nx][ny] || (cur.x == nx && cur.y == ny)) continue;

                visited[nx][ny] = true;
                queue.add(new Location(nx, ny, cur.depth + 1));
            }
        }

        return -1;
    }

    private boolean inRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}