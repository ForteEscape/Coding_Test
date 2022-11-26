from collections import deque
import copy
import sys

R, C = map(int, sys.stdin.readline().split())
board = [list(map(int, sys.stdin.readline().split())) for _ in range(R)]
directions = [[1, 0], [-1, 0], [0, 1], [0, -1]]
ans = 0


def make_wall(wall_count):
    if wall_count == 3:
        board_bfs()
        return

    for y in range(R):
        for x in range(C):
            if board[y][x] == 0:
                board[y][x] = 1
                make_wall(wall_count + 1)
                board[y][x] = 0


def board_bfs():
    wall_board = copy.deepcopy(board)
    queue_virus = deque()

    # 바이러스 확산 시뮬레이팅
    for y in range(R):
        for x in range(C):
            if wall_board[y][x] == 2:
                queue_virus.append([y, x])

    while queue_virus:
        y, x = queue_virus.popleft()

        for direction in directions:
            ny = y + direction[0]
            nx = x + direction[1]

            if 0 <= nx < C and 0 <= ny < R and not wall_board[ny][nx]:
                wall_board[ny][nx] = 2
                queue_virus.append([ny, nx])

    # 안전 지역 카운트
    safe_area = 0
    global ans

    for index in range(R):
        safe_area += wall_board[index].count(0)
    ans = max(ans, safe_area)


make_wall(0)
print(ans)
