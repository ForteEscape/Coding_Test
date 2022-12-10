N = int(input())
data = [0] + list(map(int, input().split()))

dp = [0 for _ in range(N + 1)]
dp[1] = data[1]

for i in range(2, N + 1):
    for j in range(1, i + 1):
        dp[i] = max(dp[i], dp[i - j] + data[j])

print(dp[N])