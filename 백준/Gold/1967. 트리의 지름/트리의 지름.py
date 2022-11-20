import sys
sys.setrecursionlimit(10 ** 6)

N = int(input())
graph = [[] for _ in range(N + 1)]
ans = 0


def dfs(x, weight):
    for i in graph[x]:
        # b는 가중치
        v, w = i

        if dist[v] == -1:
            dist[v] = weight + w
            dfs(v, weight + w)


for _ in range(N - 1):
    a, b, w = map(int, input().split())
    graph[a].append([b, w])
    graph[b].append([a, w])

dist = [-1] * (N + 1)
dist[1] = 0
dfs(1, 0)

start = dist.index(max(dist))
dist = [-1] * (N + 1)
dist[start] = 0
dfs(start, 0)

print(max(dist))