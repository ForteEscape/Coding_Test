N, M = map(int, input().split())
board = [list(input()) for _ in range(N)]
visited = [[False] * M for _ in range(N)]
directions = [[1, 0], [-1, 0], [0, 1], [0, -1]]
ans = False


def dfs(y, x, key, cnt, sy, sx):
    global ans

    if ans:
        return

    for d in directions:
        ny = y + d[0]
        nx = x + d[1]

        if ny < 0 or ny >= N or nx < 0 or nx >= M:
            continue

        if cnt >= 4 and ny == sy and nx == sx:
            ans = True
            return

        if board[ny][nx] == key and not visited[ny][nx]:
            visited[ny][nx] = True
            dfs(ny, nx, key, cnt + 1, sy, sx)
            visited[ny][nx] = False


for i in range(N):
    for j in range(M):
        sy, sx = i, j
        visited[sy][sx] = True

        dfs(sy, sx, board[sy][sx], 1, sy, sx)

        if ans:
            print("Yes")
            exit(0)
else:
    print("No")
    exit(0)
