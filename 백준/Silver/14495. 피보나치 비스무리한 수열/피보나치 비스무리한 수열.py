N = int(input())

if N <= 3:
    print(1)
    exit(0)

dp = [0 for _ in range(N + 1)]
dp[1] = dp[2] = dp[3] = 1

for i in range(4, N + 1):
    dp[i] = dp[i - 1] + dp[i - 3]

print(dp[N])
