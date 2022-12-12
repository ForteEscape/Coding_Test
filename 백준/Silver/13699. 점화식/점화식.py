N = int(input())

dp = [0 for _ in range(N + 1)]
dp[0] = 1

if N == 0:
    print(dp[0])
    exit()

dp[1] = 1

for i in range(2, N + 1):
    for j in range(i):
        dp[i] += dp[j] * dp[i - j - 1]

print(dp[N])