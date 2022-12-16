N, M, K = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
visited = [[False]*M for _ in range(N)]
ans = -1e9

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]


def solve(px, py, index, sum):
    if index == K:
        global ans
        ans = max(ans, sum)

        return

    for x in range(px, N):
        for y in range(py if x == px else 0, M):
            if visited[x][y]:
                continue

            flag = True

            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]

                if 0 <= nx < N and 0 <= ny < M:
                    if visited[nx][ny]:
                        flag = False
            if flag:
                visited[x][y] = True
                solve(x, y, index + 1, sum + board[x][y])
                visited[x][y] = False


solve(0, 0, 0, 0)
print(ans)