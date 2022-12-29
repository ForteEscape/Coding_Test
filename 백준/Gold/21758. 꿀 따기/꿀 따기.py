N = int(input())
data = list(map(int, input().split()))
prefix_sum = [data[0]]

for i in range(1, N):
    prefix_sum.append(prefix_sum[i - 1] + data[i])

# 벌 - 벌 - 꿀인 경우
ans = 0

for i in range(1, N - 1):
    ans = max(ans, prefix_sum[N - 1] - data[0] + prefix_sum[N - 1] - prefix_sum[i] - data[i])

# 꿀 - 벌 - 벌인 경우
for i in range(1, N - 1):
    ans = max(ans, prefix_sum[N - 2] + prefix_sum[i - 1] - data[i])

# 벌 - 꿀 - 벌인 경우
for i in range(1, N - 1):
    ans = max(ans, prefix_sum[N - 2] - data[0] + data[i])

print(ans)