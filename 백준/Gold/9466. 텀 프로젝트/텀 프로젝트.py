import sys
sys.setrecursionlimit(10**6)
T = int(input())


def dfs(v):
    global cnt
    visited[v] = True

    nv = graph[v]

    if not visited[nv]:
        dfs(nv)
    elif not finished[nv]:
        temp_node = nv

        # 사이클에 포함된 인원 계산
        while True:
            cnt += 1
            temp_node = graph[temp_node]

            if temp_node == nv:
                break

    finished[v] = True


while T > 0:
    T -= 1

    N = int(input())
    data = [0] + list(map(int, input().split()))

    visited = [False for _ in range(N + 1)]
    finished = [False for _ in range(N + 1)]
    cnt = 0

    graph = {}

    for i in range(1, N + 1):
        graph[i] = data[i]

    for i in range(1, N + 1):
        if not visited[i]:
            dfs(i)

    print(N - cnt)