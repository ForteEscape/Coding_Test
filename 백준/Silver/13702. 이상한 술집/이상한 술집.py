N, K = map(int, input().split())

data = [int(input()) for _ in range(N)]

start = 1
end = max(data)
ans = 0

while start <= end:
    mid = (start + end) // 2
    cnt = 0

    for element in data:
        cnt += element // mid

    if cnt >= K:
        ans = mid
        start = mid + 1
    else:
        end = mid - 1

print(ans)