N, M = map(int, input().split())

first = [list(map(int, input().split())) for _ in range(N)]
second = [list(map(int, input().split())) for _ in range(N)]

for i in range(N):
    for j in range(M):
        first[i][j] += second[i][j]

for element in first:
    print(*element)