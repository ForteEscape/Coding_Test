import sys
sys.setrecursionlimit(10 ** 6)


def dfs(key, data):
    data[key] = -2
    for i in range(len(data)):
        if key == data[i]:
            dfs(i, data)


N = int(input())
data = list(map(int, input().split()))
key = int(input())
dfs(key, data)

ans = 0
for i in range(len(data)):
    if data[i] != -2 and i not in data:
        ans += 1

print(ans)