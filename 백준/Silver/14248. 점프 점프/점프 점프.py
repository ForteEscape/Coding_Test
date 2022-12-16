from collections import deque

N = int(input())
data = list(map(int, input().split()))
start = int(input())
ans = 0

visited = [False]*N

visited[start - 1] = True
queue = deque()
queue.append(start - 1)

while queue:
    cur = queue.popleft()
    ans += 1

    if 0 <= cur + data[cur] < N and not visited[cur + data[cur]]:
        visited[cur + data[cur]] = True
        queue.append(cur + data[cur])

    if 0 <= cur - data[cur] < N and not visited[cur - data[cur]]:
        visited[cur - data[cur]] = True
        queue.append(cur - data[cur])

print(ans)