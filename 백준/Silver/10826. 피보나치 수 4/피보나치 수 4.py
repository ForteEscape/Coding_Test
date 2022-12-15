N = int(input())

if N == 0:
    print(0)
    exit(0)
elif N == 1:
    print(1)
    exit(0)

dp = [0, 1]
for i in range(2, N + 1):
    dp.append(dp[i - 2] + dp[i - 1])

print(dp[N])