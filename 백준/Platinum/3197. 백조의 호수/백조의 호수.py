from collections import deque

N, M = map(int, input().split())
board = [list(input()) for _ in range(N)]
bird_location = []
directions = [[1, 0], [0, -1], [-1, 0], [0, 1]]
day = 0

visited = [[False for _ in range(M)] for _ in range(N)]
visited2 = [[False for _ in range(M)] for _ in range(N)]
queue = deque()
queue_tmp = deque()
queue2 = deque()
queue2_tmp = deque()


def bfs():
    while queue2:
        y, x = queue2.popleft()
        if y == b2_y and x == b2_x:
            return 1

        for d in directions:
            ny = y + d[0]
            nx = x + d[1]

            if ny < 0 or ny >= N or nx < 0 or nx >= M:
                continue

            if board[ny][nx] == '.' and not visited2[ny][nx]:
                visited2[ny][nx] = True
                queue2.append([ny, nx])
            elif board[ny][nx] != '.' and not visited2[ny][nx]:
                visited2[ny][nx] = True
                queue2_tmp.append([ny, nx])

    return 0


def melt():
    while queue:
        y, x = queue.popleft()

        if board[y][x] == 'X':
            board[y][x] = '.'

        for d in directions:
            ny = y + d[0]
            nx = x + d[1]

            if ny < 0 or ny >= N or nx < 0 or nx >= M:
                continue
            if board[ny][nx] == 'X' and not visited[ny][nx]:
                visited[ny][nx] = True
                board[ny][nx] = '.'
                queue_tmp.append([ny, nx])
            elif board[ny][nx] != 'X' and not visited[ny][nx]:
                visited[ny][nx] = True
                queue.append([ny, nx])


for i in range(N):
    for j in range(M):
        if board[i][j] == 'L':
            queue.append([i, j])
            bird_location.append([i, j])
        elif board[i][j] == '.':
            visited[i][j] = True
            queue.append([i, j])

b1_y, b1_x = bird_location[0]
b2_y, b2_x = bird_location[1]
visited[b1_y][b1_x] = True
board[b1_y][b1_x], board[b2_y][b2_x] = '.', '.'
day = 0
queue2.append([b1_y, b1_x])

while True:
    melt()
    if bfs():
        print(day + 1)
        break

    queue, queue2 = queue_tmp, queue2_tmp
    queue_tmp, queue2_tmp = deque(), deque()
    day += 1
