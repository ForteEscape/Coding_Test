N = int(input())
data = []

for _ in range(N):
    day, score = map(int, input().split())
    data.append([day, score])

data.sort()
can_process = []
ans = 0

for i in range(N, 0, -1):
    while data and data[-1][0] >= i:
        can_process.append(data.pop()[1])

    if can_process:
        can_process.sort()
        ans += can_process.pop()

print(ans)

