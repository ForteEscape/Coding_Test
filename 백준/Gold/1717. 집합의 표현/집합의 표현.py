import sys
sys.setrecursionlimit(10**9)

N, M = map(int, sys.stdin.readline().split())
parent = [x for x in range(N + 1)]


def find(data):
    if parent[data] != data:
        parent[data] = find(parent[data])
    return parent[data]


def union(a, b):
    a = find(a)
    b = find(b)

    if a < b:
        parent[b] = a
    else:
        parent[a] = b


for _ in range(M):
    oper, a, b = map(int, sys.stdin.readline().split())

    if oper == 0:
        union(a, b)
    else:
        if find(a) == find(b):
            print('YES')
        else:
            print('NO')