def dfs(v, start):
    for nv in graph[v]:
        if not visited[nv]:
            visited[nv] = True
            parent[nv] = start
            dfs(nv, start)


N = int(input())
M = int(input())

graph = [[] for _ in range(N + 1)]
parent = [x for x in range(N + 1)]
visited = [False for _ in range(N + 1)]

for i in range(N):
    edge = list(map(int, input().split()))

    for j in range(N):
        if edge[j] == 1:
            graph[j + 1].append(i + 1)
            graph[i + 1].append(j + 1)

plan = list(map(int, input().split()))

for i in range(1, N + 1):
    if not visited[i]:
        visited[i] = True
        dfs(i, i)

p = parent[plan[0]]
for i in range(1, len(plan)):
    if parent[plan[i]] != p:
        print("NO")
        break
else:
    print("YES")
