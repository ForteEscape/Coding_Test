import sys
from itertools import combinations

n, k = map(int, sys.stdin.readline().split())
board = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
chicken_location = []
house_location = []

for i in range(n):
    for j in range(n):
        if board[i][j] == 1:
            house_location.append([i, j])
        elif board[i][j] == 2:
            chicken_location.append([i, j])

combs = list(combinations(chicken_location, k))
candidate = []

for comb in combs:
    ans = 0
    for house in house_location:
        y1, x1 = house
        dist = int(1e9)
        for c in comb:
            y2, x2 = c
            dist = min(dist, abs(x1-x2) + abs(y1-y2))

        ans += dist
    candidate.append(ans)

print(min(candidate))

