from collections import deque

N = int(input())
board = [list(map(int, input().split())) for _ in range(N)]
shark_size = 2
visited = [[0]*N for _ in range(N)]
queue = deque()
is_feed = True
feed_cnt = 0
directions = [[1, 0], [-1, 0], [0, 1], [0, -1]]

for i in range(N):
    for j in range(N):
        if board[i][j] == 9:
            board[i][j] = 0
            visited[i][j] = 1
            queue.append([i, j])
            break
    if queue:
        break

ans = 0
while True:
    is_finded = False
    fish_location = []

    while queue:
        y, x = queue.popleft()

        if 0 < board[y][x] < shark_size:
            fish_location.append([visited[y][x], y, x])
            is_finded = True

        if is_finded:
            continue

        for d in directions:
            ny = y + d[0]
            nx = x + d[1]

            if ny < 0 or ny >= N or nx < 0 or nx >= N:
                continue
            if not visited[ny][nx] and board[ny][nx] <= shark_size:
                visited[ny][nx] = visited[y][x] + 1
                queue.append([ny, nx])

    if not is_finded:
        break

    fish_location.sort(key=lambda x: (x[0], x[1], x[2]))
    f_y, f_x = fish_location[0][1], fish_location[0][2]

    ans += visited[f_y][f_x] - 1
    """
    print(ans, shark_size)
    for element in board:
        print(*element)
    print("============")

    for element in visited:
        print(*element)
    print("==========")
    """
    board[f_y][f_x] = 0

    feed_cnt += 1
    if feed_cnt == shark_size:
        feed_cnt = 0
        shark_size += 1

    visited = [[0] * N for _ in range(N)]
    queue.clear()
    queue.append([f_y, f_x])
    visited[f_y][f_x] = 1

print(ans)